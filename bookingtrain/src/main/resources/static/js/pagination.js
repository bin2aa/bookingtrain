document.addEventListener("DOMContentLoaded", function () {
        function setupPaginationEventListeners() {
            const paginationContainer = document.querySelector("#pagination");

            paginationContainer.addEventListener("click", function (e) {
                const target = e.target;

                // Chỉ xử lý nếu người dùng nhấn vào liên kết phân trang
                if (target.classList.contains("page-link")) {
                    e.preventDefault(); // Ngăn chặn hành vi mặc định tải lại trang

                    const url = target.getAttribute("href"); // Lấy URL của trang mới

                    // Gọi fetch đến URL của trang mới
                    fetch(url, {
                        method: "GET",
                        headers: {
                           "Accept": "application/json"  // Đảm bảo yêu cầu là AJAX
                        }
                    })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error("Network response was not ok");
                            }
                            return response.text();
                        })
                        .then(html => {
                            // Sử dụng DOMParser để phân tích cú pháp HTML
                            const parser = new DOMParser();
                            const doc = parser.parseFromString(html, 'text/html');

                            // Cập nhật nội dung của bảng (#tableContent) và phần phân trang (#pagination)
                            const newTableContent = doc.querySelector("#tableContent").innerHTML;
                            document.querySelector("#tableContent").innerHTML = newTableContent;
                            console.log(doc);

                            const newPagination = doc.querySelector("#pagination").innerHTML;
                            document.querySelector("#pagination").innerHTML = newPagination;
                            // Gán lại sự kiện click cho các liên kết phân trang
                            setupPaginationEventListeners();
                        })
                        .catch(error => {
                            console.error("Error loading page:", error);
                        });
                }
            });
        }
        setupPaginationEventListeners();
    });