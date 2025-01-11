let passengerCount = 1;

// Function to dynamically load available seats into the dropdown
// function loadSeats(passengerIndex) {
//     const trainId = localStorage.getItem('trainId');
//     if (trainId) {
//         $.get(`/seats/all?trainId=${trainId}`, function (data) {
//             $(`#seats_${passengerIndex}`).empty().append('<option value="">-- Choose a seat --</option>');
//             if (data && data.length > 0) {
//                 data.forEach(seat => {
//                     $(`#seats_${passengerIndex}`).append(
//                         `<option value="${seat.seatId}" data-price="${seat.price}" data-object-id="${seat.objectId}">SeatCode: ${seat.seatCode} - CoacheCode: ${seat.coacheCode} - Price: ${seat.price} VND</option>`
//                     );
//                 });
//             } else {
//                 $(`#seats_${passengerIndex}`).append('<option value="">No seats available</option>');
//             }
//         }).fail(function () {
//             alert('Error loading seat data. Please try again later.');
//         });
//     } else {
//         alert('Train ID not found. Please select a train first.');
//     }
// }

function loadSeats(passengerIndex) {
    const trainId = localStorage.getItem('trainId');
    const scheduleId = localStorage.getItem('scheduleId');

    if (trainId && scheduleId) {
        $.get(`/seats/all?trainId=${trainId}&scheduleId=${scheduleId}`, function (data) {
            $(`#seats_${passengerIndex}`).empty().append('<option value="">-- Chọn ghế --</option>');
            if (data && data.length > 0) {
                data.forEach(seat => {
                    $(`#seats_${passengerIndex}`).append(
                        `<option value="${seat.seatId}" data-price="${seat.price}" data-object-id="${seat.objectId}">Mã Ghế: ${seat.seatCode} - Mã Toa: ${seat.coacheCode} - Giá: ${seat.price} VND</option>`
                    );
                });
            } else {
                $(`#seats_${passengerIndex}`).append('<option value="">Không có ghế trống</option>');
            }
        }).fail(function () {
            alert('Lỗi khi tải dữ liệu ghế. Vui lòng thử lại sau.');
        });
    } else {
        alert('Không tìm thấy ID tàu hoặc ID lịch trình. Vui lòng chọn tàu trước.');
    }
}

function validateDuplicateSeats() {
    const selectedSeats = [];
    let isValid = true;

    for (let i = 1; i <= passengerCount; i++) {
        const seatId = $(`#seats_${i}`).val();
        if (seatId && selectedSeats.includes(seatId)) {
            isValid = false;
            alert(`Ghế đã được chọn cho hành khách ${i}. Vui lòng chọn ghế khác.`);
            break;
        }
        selectedSeats.push(seatId);
    }

    return isValid;
}

function calculateAge(dateOfBirth) {
    const today = new Date();
    const birthDate = new Date(dateOfBirth);
    let age = today.getFullYear() - birthDate.getFullYear();
    const m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return age;
}

// Function to fetch price based on age and set objectId
function fetchPriceByAge(age, passengerIndex) {
    let objectId;
    if (age < 18) {
        objectId = 1; // ID Trẻ Em
    } else if (age >= 60) {
        objectId = 3; // ID Người Già
    } else {
        objectId = 2; // ID Người Lớn
    }

    // Set objectId in the form
    $(`#objectId_${passengerIndex}`).val(objectId);

    $.get(`/objects/price/${objectId}`, function (price) {
        $(`#price_${passengerIndex}`).text(price + ' VND');
        calculateTotalPrice(); // Recalculate total price after fetching the price
    }).fail(function () {
        alert('Error fetching price. Please try again later.');
    });
}

// Function to calculate total price
function calculateTotalPrice() {
    let totalPrice = 0;
    for (let i = 1; i <= passengerCount; i++) {
        const selectedSeat = $(`#seats_${i}`).find(':selected');
        const seatPrice = parseFloat(selectedSeat.data('price')) || 0;
        const agePrice = parseFloat($(`#price_${i}`).text()) || 0;
        totalPrice += seatPrice + agePrice;
    }
    $('#totalPrice').text(totalPrice.toFixed(2));
    $('#totalPriceInput').val(totalPrice.toFixed(2));
}

// Event listener for date of birth change
$(document).on('change', 'input[id^="dateOfBirth_"]', function () {
    const passengerIndex = $(this).attr('id').split('_')[1];
    const dateOfBirth = $(this).val();
    const age = calculateAge(dateOfBirth);
    fetchPriceByAge(age, passengerIndex);
});

// Function to save form data to local storage
function saveFormData() {
    const passengerData = [];
    for (let i = 1; i <= passengerCount; i++) {
        passengerData.push({
            passengerName: $(`#passengerName_${i}`).val(),
            dateOfBirth: $(`#dateOfBirth_${i}`).val(),
            phone: $(`#phone_${i}`).val(),
            seatId: $(`#seats_${i}`).val(),
            objectId: $(`#objectId_${i}`).val()
        });
    }
    localStorage.setItem('passengerData', JSON.stringify(passengerData));
}

// Function to load form data from local storage
function loadFormData() {
    const savedData = JSON.parse(localStorage.getItem('passengerData'));
    if (savedData) {
        savedData.forEach((data, index) => {
            if (index > 0) {
                addPassengerForm();
            }
            $(`#passengerName_${index + 1}`).val(data.passengerName);
            $(`#dateOfBirth_${index + 1}`).val(data.dateOfBirth);
            $(`#phone_${index + 1}`).val(data.phone);
            $(`#seats_${index + 1}`).val(data.seatId);
            $(`#objectId_${index + 1}`).val(data.objectId);
        });
    }
}

// Function to add a new passenger form and load seats
function addPassengerForm() {
    passengerCount++;
    const newPassengerForm = `
    <div class="mb-4" id="passengerForm_${passengerCount}">
        <h5 class="text-center-custom">Passenger ${passengerCount}</h5>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="passengerName_${passengerCount}">Full Name:</label>
                    <input type="text" id="passengerName_${passengerCount}" name="passengers[${passengerCount - 1}].passengerName" class="form-control" required>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="dateOfBirth_${passengerCount}">Date of Birth:</label>
                    <input type="date" id="dateOfBirth_${passengerCount}" name="passengers[${passengerCount - 1}].dateOfBirth" class="form-control" required>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="phone_${passengerCount}">Phone:</label>
                    <input type="text" id="phone_${passengerCount}" name="passengers[${passengerCount - 1}].phone" class="form-control" required>
                </div>
            </div>
            <div class="col-md-6">
                <label for="seats_${passengerCount}">Seat:</label>
                <select id="seats_${passengerCount}" name="passengers[${passengerCount - 1}].seatId" class="form-control" required>
                    <option value="">-- Choose a seat --</option>
                </select>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <label>Price:</label>
                <span id="price_${passengerCount}">0 VND</span>
            </div>
        </div>
        <input type="hidden" id="objectId_${passengerCount}" name="passengers[${passengerCount - 1}].objectId">
        <button type="button" class="btn btn-danger btn-delete-passenger" onclick="deletePassengerForm(${passengerCount})">Delete</button>
        <hr>
    </div>
`;
    $('#passengerForms').append(newPassengerForm);
    loadSeats(passengerCount); // Load seats for the new passenger form

    // Add change event to newly created form fields to save data to local storage and calculate total price
    $(`#passengerForm_${passengerCount} input, #passengerForm_${passengerCount} select`).on('change', function () {
        saveFormData();
        calculateTotalPrice();
    });
}

// Function to delete a passenger form
function deletePassengerForm(index) {
    $(`#passengerForm_${index}`).remove();
    passengerCount--;
    saveFormData(); // Save the updated form data to local storage
    calculateTotalPrice(); // Recalculate total price

    // Re-index remaining forms
    for (let i = index + 1; i <= passengerCount + 1; i++) {
        $(`#passengerForm_${i}`).attr('id', `passengerForm_${i - 1}`);
        $(`#passengerForm_${i - 1} .passenger-header h5`).text(`Passenger ${i - 1}`);
        $(`#passengerName_${i}`).attr('id', `passengerName_${i - 1}`).attr('name', `passengers[${i - 2}].passengerName`);
        $(`#dateOfBirth_${i}`).attr('id', `dateOfBirth_${i - 1}`).attr('name', `passengers[${i - 2}].dateOfBirth`);
        $(`#phone_${i}`).attr('id', `phone_${i - 1}`).attr('name', `passengers[${i - 2}].phone`);
        $(`#seats_${i}`).attr('id', `seats_${i - 1}`).attr('name', `passengers[${i - 2}].seatId`);
        $(`#objectId_${i}`).attr('id', `objectId_${i - 1}`).attr('name', `passengers[${i - 2}].objectId`);
    }
}

// Function to save seat ID to session
function saveSeatIdToSession(passengerIndex, seatId) {
    $.post('/payment/session/saveSeatId', { passengerIndex: passengerIndex, seatId: seatId });
}

// Event listener for form changes to trigger save to local storage and calculate total price
$(document).on('change', '#passengerForms input, #passengerForms select', function () {
    saveFormData();
    calculateTotalPrice();
});

// Event listener for seat selection to save seat ID to session
$(document).on('change', 'select[id^="seats_"]', function () {
    const passengerIndex = $(this).attr('id').split('_')[1];
    const seatId = $(this).val();
    saveSeatIdToSession(passengerIndex, seatId);
    validateDuplicateSeats();
});

// Add event listener for the "New Passenger" button
$('#addPassengerBtn').on('click', addPassengerForm);

// Initialize payment modal
// $('#buyTicketBtn').on('click', function () {
//     if (validatePassengerForm()) {
//         const totalPrice = parseFloat($('#totalPrice').text().replace(' VND', ''));
//         $('#totalPriceInput').val(totalPrice);
//         $('#paymentTotalPrice').text(totalPrice);
//         // $('#paymentModal').modal('show');
//     } else {
//         $('#notificationMessage').text('Please fill in all required fields.');
//         $('#notificationModal').modal('show');
//     }
// });

// document.getElementById('vnpayBtn').addEventListener('click', function () {
//     // Validate form before proceeding to payment
//     if (validatePassengerForm()) {
//         const totalPrice = parseFloat(document.getElementById('totalPrice').textContent);
//         document.getElementById('vnpayAmount').value = totalPrice * 100; // Convert to smallest currency unit
//         document.getElementById('vnpayForm').submit();
//     } else {
//         $('#notificationMessage').text('Please fill in all required fields.');
//         $('#notificationModal').modal('show');
//     }
// });
// Validate payment method before submitting the payment form
// Update payment form submission handler
$('#paymentForm').on('submit', function (e) {
    e.preventDefault();

    // Get the current total price
    const totalPrice = parseFloat($('#totalPrice').text().replace(' VND', ''));

    // Set the total price in hidden input
    $('#totalPriceInput').val(totalPrice);

    // Set payment modal total price display
    $('#paymentTotalPrice').text(totalPrice);

    // Collect passenger data
    const passengerData = [];
    for (let i = 1; i <= passengerCount; i++) {
        passengerData.push({
            passengerName: $(`#passengerName_${i}`).val(),
            dateOfBirth: $(`#dateOfBirth_${i}`).val(),
            phone: $(`#phone_${i}`).val(),
            seatId: $(`#seats_${i}`).val(),
            objectId: $(`#objectId_${i}`).val()
        });
    }

    // Set passenger data in hidden input
    $('#passengerData').val(JSON.stringify(passengerData));

    // Validate payment method
    if (!paymentMethod) {
        alert('Please select a payment method.');
        return false;
    }

    // If everything is valid, submit the form
    this.submit();
});

function validatePassengerForm() {
    let isValid = true;
    for (let i = 1; i <= passengerCount; i++) {
        const passengerName = $(`#passengerName_${i}`).val();
        const dateOfBirth = $(`#dateOfBirth_${i}`).val();
        const phone = $(`#phone_${i}`).val();
        const seatId = $(`#seats_${i}`).val();

        if (!passengerName || !dateOfBirth || !phone || !seatId) {
            isValid = false;
            break;
        }
    }
    return isValid;
}

// Initially load available seats for the first passenger and populate form with saved data
$(document).ready(function () {

    const trainId = localStorage.getItem('trainId');
    const scheduleId = localStorage.getItem('scheduleId');
    if (!trainId || !scheduleId) {
        alert('Train ID or Schedule ID not found. Please select a train first.');
        window.location.href = '/trains';
    }




    localStorage.removeItem('passengerData'); // Clear saved passenger data on page load
    loadSeats(1); // Tải ghế cho hành khách đầu tiên

    // Xóa dữ liệu khi người dùng rời trang
    $(window).on('unload', function () {
        localStorage.removeItem('trainId');
        localStorage.removeItem('scheduleId');
    });

    // Thêm sự kiện 'change' cho hành khách đầu tiên
    $('#passengerForm_1 input, #passengerForm_1 select').on('change', function () {
        saveFormData();          // Lưu dữ liệu vào localStorage
        calculateTotalPrice();   // Tính lại tổng giá
    });

    $('#dateOfBirth_1').on('change', function () {
        const dateOfBirth = $(this).val();
        const age = calculateAge(dateOfBirth);
        fetchPriceByAge(age, 1); // Lấy giá cho hành khách đầu tiên
    });

    calculateTotalPrice(); // Tính tổng giá khi trang tải
});