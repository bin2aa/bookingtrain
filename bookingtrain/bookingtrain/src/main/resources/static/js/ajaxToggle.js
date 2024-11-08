$(document).ready(function () {
    $(".toggle").change(function (event) {
        // Ngăn chặn hành vi mặc định của form
        event.preventDefault();

        var $toggle = $(this);
        // Lấy giá trị từ data attributes
        var roleId = $toggle.data('role-id');
        var permissionId = $toggle.data('permission-id');
        var operationId = $toggle.data('operation-id');
        var status = $toggle.prop('checked') ? 1 : 2;
        // Lưu trạng thái toggle ban đầu
        var originalState = $toggle.prop('checked');
        // Disable toggle trong khi xử lý
        $toggle.prop('disabled', true);
        $.ajax({
            type: "POST",
            url: "/roleOperations/editJson",
            data: {
                roleId: roleId,
                permissionId: permissionId,
                operationId: operationId,
                statusId: status
            },
            success: function (response) {
                if (response === "susscess") {
                    $toggle.data('status', status);
                    console.log("Cập nhật thành công");
                } else {
                    $toggle.prop('checked', originalState);
                    alert("Lỗi khi cập nhật trạng thái");
                }
            },
            error: function (xhr, status, error) {
                $toggle.prop('checked', originalState);
                alert("Lỗi khi gửi yêu cầu");
            },
            complete: function () {
                $toggle.prop('disabled', false);
            }
        });
    });

    // Khôi phục trạng thái toggle khi load trang
    // $(".toggle").each(function () {
    //     var status = $(this).data('status');
    //     $(this).prop('checked', status == 1);
    // });



    


});