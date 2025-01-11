$(document).ready(function () {

    // Xử lý sự kiện click vào nút sửa, thêm, chi tiết
    $(document).on('click', "a.addRoleLink, a.addPermissionLink,\
         a.addRoleOperationLink, a.addUserLink, a.addTrainLink, \
         a.addStationLink, a.addScheduleLink, a.addObjectLink, \
         a.addEmployeeLink, a.addRouteLink, a.addSeatLink, a.addSeatTypeLink,\
         \
         a.editRoleLink, a.editPermissionLink,\
         a.editRoleOperationLink, a.editUserLink, a.editTrainLink, \
         a.editStationLink, a.editScheduleLink, a.editObjectLink, \
         a.editEmployeeLink, a.editRouteLink, a.editSeatLink, a.editSeatTypeLink",
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
            if ($(this).hasClass('addSeatLink')) {
                containerId = '#addSeatContainer';
            }
            if ($(this).hasClass('addSeatTypeLink')) {
                containerId = '#addSeatTypeContainer';
            }

            // ===============================

            if ($(this).hasClass('editRoleOperationLink')) {
                containerId = '#editRoleOperationContainer';
            }
            if ($(this).hasClass('editRoleLink')) {
                containerId = '#editRoleContainer';
            }
            if ($(this).hasClass('editPermissionLink')) {
                containerId = '#editPermissionContainer';
            }
            if ($(this).hasClass('editUserLink')) {
                containerId = '#editUserContainer';
            }
            if ($(this).hasClass('editTrainLink')) {
                containerId = '#editTrainContainer';
            }
            if ($(this).hasClass('editStationLink')) {
                containerId = '#editStationContainer';
            }
            if ($(this).hasClass('editScheduleLink')) {
                containerId = '#editScheduleContainer';
            }
            if ($(this).hasClass('editObjectLink')) {
                containerId = '#editObjectContainer';
            }
            if ($(this).hasClass('editEmployeeLink')) {
                containerId = '#editEmployeeContainer';
            }
            if ($(this).hasClass('editRouteLink')) {
                containerId = '#editRouteContainer';
            }
            if ($(this).hasClass('editSeatLink')) {
                containerId = '#editSeatContainer';
            }
            if ($(this).hasClass('editSeatTypeLink')) {
                containerId = '#editSeatTypeContainer';
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
            !$(e.target).closest('#addPermissionContainer').length &&
            !$(e.target).closest('#addRoleOperationContainer').length &&
            !$(e.target).closest('#addUserContainer').length &&
            !$(e.target).closest('#addTrainContainer').length &&
            !$(e.target).closest('#addStationContainer').length &&
            !$(e.target).closest('#addScheduleContainer').length &&
            !$(e.target).closest('#addObjectContainer').length &&
            !$(e.target).closest('#addEmployeeContainer').length &&
            !$(e.target).closest('#addRouteContainer').length &&
            !$(e.target).closest('#addSeatContainer').length &&
            !$(e.target).closest('#addSeatTypeContainer').length &&
            // ===============================
            !$(e.target).closest('#editRoleOperationContainer').length &&
            !$(e.target).closest('#editRoleContainer').length &&
            !$(e.target).closest('#editPermissionContainer').length &&
            !$(e.target).closest('#editUserContainer').length &&
            !$(e.target).closest('#editTrainContainer').length &&
            !$(e.target).closest('#editStationContainer').length &&
            !$(e.target).closest('#editScheduleContainer').length &&
            !$(e.target).closest('#editObjectContainer').length &&
            !$(e.target).closest('#editEmployeeContainer').length &&
            !$(e.target).closest('#editRouteContainer').length &&
            !$(e.target).closest('#editSeatContainer').length &&
            !$(e.target).closest('#editSeatTypeContainer').length) {
            $(".overlay").hide();
            $("#addRoleContainer").hide();
            $("#addPermissionContainer").hide();
            $("#addRoleOperationContainer").hide();
            $("#addUserContainer").hide();
            $("#addTrainContainer").hide();
            $("#addStationContainer").hide();
            $("#addScheduleContainer").hide();
            $("#addObjectContainer").hide();
            $("#addEmployeeContainer").hide();
            $("#addRouteContainer").hide();
            $("#addSeatContainer").hide();
            $("#addSeatTypeContainer").hide();
            // ===============================
            $("#editRoleOperationContainer").hide();
            $("#editRoleContainer").hide();
            $("#editPermissionContainer").hide();
            $("#editUserContainer").hide();
            $("#editTrainContainer").hide();
            $("#editStationContainer").hide();
            $("#editScheduleContainer").hide();
            $("#editObjectContainer").hide();
            $("#editEmployeeContainer").hide();
            $("#editRouteContainer").hide();
            $("#editSeatContainer").hide();
            $("#editSeatTypeContainer").hide();
        }
    });
});