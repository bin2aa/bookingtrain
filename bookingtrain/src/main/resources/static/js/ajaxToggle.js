document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.toggle').forEach(function (toggle) {
        toggle.addEventListener('change', function (event) {
            event.preventDefault();
            var roleId = toggle.dataset.roleId;
            var permissionId = toggle.dataset.permissionId;
            var operationId = toggle.dataset.operationId;
            var status = toggle.checked ? 1 : 2;
            var originalState = toggle.checked;
            toggle.disabled = true;

            fetch('/roleOperations/editJson', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    roleId: roleId,
                    permissionId: permissionId,
                    operationId: operationId,
                    statusId: status
                })
            })
                .then(response => response.text())
                .then(response => {
                    if (response === "susscess") {
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
        });
    });
});