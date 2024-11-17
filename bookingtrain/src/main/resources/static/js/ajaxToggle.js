document.addEventListener('DOMContentLoaded', function () {
    document.addEventListener('change', function (event) {
        const toggle = event.target.closest('.toggle');
        if (toggle) {
            event.preventDefault();
            var url, bodyData;

            if (toggle.dataset.roleId) {
                // Xử lý toggle cho roleOperations
                var roleId = toggle.dataset.roleId;
                var permissionId = toggle.dataset.permissionId;
                var operationId = toggle.dataset.operationId;
                var status = toggle.checked ? 1 : 2;
                bodyData = JSON.stringify({
                    roleId: roleId,
                    permissionId: permissionId,
                    operationId: operationId,
                    statusId: status
                });
                url = '/roleOperations/editJson';
            } else if (toggle.dataset.trainId) {
                // Xử lý toggle cho trains
                var trainId = toggle.dataset.trainId;
                var status = toggle.checked ? 1 : 0;
                bodyData = JSON.stringify({
                    trainId: trainId,
                    statusTrain: status
                });
                url = '/trains/editJson';
            } else if (toggle.dataset.stationId) {
                // Xử lý toggle cho stations
                var stationId = toggle.dataset.stationId;
                var status = toggle.checked ? 1 : 0;
                bodyData = JSON.stringify({
                    stationId: stationId,
                    statusStation: status
                });
                url = '/stations/editJson';
            } else if (toggle.dataset.scheduleId) {
                // Xử lý toggle cho schedules
                var scheduleId = toggle.dataset.scheduleId;
                var status = toggle.checked ? 1 : 0;
                bodyData = JSON.stringify({
                    scheduleId: scheduleId,
                    statusSchedule: status
                });
                url = '/schedules/editJson';
            } else if (toggle.dataset.routeId) {
                // Xử lý toggle cho routes
                var routeId = toggle.dataset.routeId;
                var status = toggle.checked ? 1 : 0;
                bodyData = JSON.stringify({
                    routeId: routeId,
                    statusRoute: status
                });
                url = '/routes/editJson';
            }

            var originalState = toggle.checked;
            toggle.disabled = true;

            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: bodyData
            })
                .then(response => response.text())
                .then(response => {
                    if (response === "success") {
                        toggle.dataset.status = status;
                        console.log("Cập nhật thành công");
                    } else {
                        toggle.checked = originalState;
                        alert("Lỗi khi cập nhật trạng thái");
                    }
                })
                .catch(error => {
                    toggle.checked = originalState;
                    alert("Lỗi khi gửi yêu cầu");
                })
                .finally(() => {
                    toggle.disabled = false;
                });
        }
    });
});