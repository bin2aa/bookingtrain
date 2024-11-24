$(document).ready(function () {

    // Xử lý sự kiện click vào nút sửa, thêm, chi tiết
    $(document).on('click', "a.addRoleLink, a.addPermissionLink,\
         a.addRoleOperationLink, a.addUserLink, a.addTrainLink, \
         a.addStationLink, a.addScheduleLink, a.addObjectLink, \
         a.addEmployeeLink, a.addRouteLink",
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
            if ($(this).hasClass('addTrainLink')) {
                containerId = '#addTrainContainer';
            }
            if ($(this).hasClass('addStationLink')) {
                containerId = '#addStationContainer';
            }
            if ($(this).hasClass('addScheduleLink')) {
                containerId = '#addScheduleContainer';
            }
            if ($(this).hasClass('addObjectLink')) {
                containerId = '#addObjectContainer';
            }
            if ($(this).hasClass('addEmployeeLink')) {
                containerId = '#addEmployeeContainer';
            }
            if ($(this).hasClass('addRouteLink')) {
                containerId = '#addRouteContainer';
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
            !$(e.target).closest('#addUserContainer').length &&
            !$(e.target).closest('#addTrainContainer').length &&
            !$(e.target).closest('#addStationContainer').length &&
            !$(e.target).closest('#addScheduleContainer').length &&
            !$(e.target).closest('#addObjectContainer').length &&
            !$(e.target).closest('#addEmployeeContainer').length &&
            !$(e.target).closest('#addRouteContainer').length
        ) {
            $('#addRoleContainer').hide();
            $('#addRoleOperationContainer').hide();
            $("#addPermissionContainer").hide();
            $("#addUserContainer").hide();
            $("#addTrainContainer").hide();
            $("#addStationContainer").hide();
            $("#addScheduleContainer").hide();
            $("#addObjectContainer").hide();
            $("#addEmployeeContainer").hide();
            $("#addRouteContainer").hide();
            $(".overlay").hide();
        }
    });


});
