$(document).ready(function () {


    // Xử lý sự kiện click vào nút sửa, thêm, chi tiết
    $(document).on('click', "a.addRoleLink, a.addPermissionLink,\
         a.addRoleOperationLink, a.addUserLink",
        function (e) {
            e.preventDefault();
            var href = $(this).attr("href");
            var containerId;

            //hasClass kiểm tra xem thẻ a href vừa click có class ... không
            if ($(this).hasClass('addRoleOperationLink')) {
                containerId = '#addRoleOperationContainer';
            }
            if ($(this).hasClass('addRoleLink')) {
                containerId = '#addRoleContainer';
            }
            if ($(this).hasClass('addPermissionLink')) {
                containerId = '#addPermissionContainer';
            }
            if ($(this).hasClass('addUserLink')) {
                containerId = '#addUserContainer';
            }


            $.ajax({
                url: href,
                method: "GET",
                success: function (data) {
                    // var content = $(data).find(contentClass).html();
                    $(containerId).html(data).show();
                    $(".overlay").show();

                }
            });
        });


    $(document).on('click', function (e) {
        if (!$(e.target).closest('#addRoleContainer').length &&
            !$(e.target).closest('#addRoleOperationContainer').length &&
            !$(e.target).closest('#addPermissionContainer').length &&
            !$(e.target).closest('#addUserContainer').length
        ) {
            $('#addRoleContainer').hide();
            $('#addRoleOperationContainer').hide();
            $("#addPermissionContainer").hide();
            $("#addUserContainer").hide();
            $(".overlay").hide();
        }
    });


});
