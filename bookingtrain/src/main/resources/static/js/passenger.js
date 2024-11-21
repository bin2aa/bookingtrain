let passengerCount = 1;

// Function to dynamically load available seats into the dropdown
function loadSeats(passengerIndex) {
    const trainId = localStorage.getItem('trainId');
    if (trainId) {
        $.get(`/seats/all?trainId=${trainId}`, function (data) {
            $(`#seats_${passengerIndex}`).empty().append('<option value="">-- Choose a seat --</option>');
            if (data && data.length > 0) {
                data.forEach(seat => {
                    $(`#seats_${passengerIndex}`).append(
                        `<option value="${seat.seatId}" data-price="${seat.price}" data-object-id="${seat.objectId}">SeatCode: ${seat.seatCode} - CoacheCode: ${seat.coacheCode} - Price: ${seat.price} VND</option>`
                    );
                });
            } else {
                $(`#seats_${passengerIndex}`).append('<option value="">No seats available</option>');
            }
        }).fail(function () {
            alert('Error loading seat data. Please try again later.');
        });
    } else {
        alert('Train ID not found. Please select a train first.');
    }
}

// Function to calculate total price
function calculateTotalPrice() {
    let totalPrice = 0;
    for (let i = 1; i <= passengerCount; i++) {
        const selectedSeat = $(`#seats_${i}`).find(':selected');
        const seatPrice = parseFloat(selectedSeat.data('price')) || 0;
        totalPrice += seatPrice;
    }
    $('#totalPrice').text(totalPrice.toFixed(2));
}

// Function to save form data to local storage
function saveFormData() {
    const passengerData = [];
    for (let i = 1; i <= passengerCount; i++) {
        passengerData.push({
            passengerName: $(`#passengerName_${i}`).val(),
            dateOfBirth: $(`#dateOfBirth_${i}`).val(),
            phone: $(`#phone_${i}`).val(),
            seatId: $(`#seats_${i}`).val(),
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
});

// Add event listener for the "New Passenger" button
$('#addPassengerBtn').on('click', addPassengerForm);

$('#buyTicketBtn').on('click', function () {
    // Kiểm tra đăng nhập trước
    $.get('/checkLoginStatus', function (isLoggedIn) {
        // Nếu chưa đăng nhập, hiển thị thông báo ngay lập tức
        if (!isLoggedIn) {
            $('#notificationMessage').text('Please log in to purchase tickets.');
            $('#notificationModal').modal('show');
            return; // Dừng xử lý tiếp
        }

        // Nếu đã đăng nhập, mới kiểm tra validate form
        if (validatePassengerForm()) {
            const totalPrice = parseFloat($('#totalPrice').text());
            $('#totalPriceInput').val(totalPrice);

            // Collect passenger data
            const passengerData = [];
            for (let i = 1; i <= passengerCount; i++) {
                passengerData.push({
                    passengerName: $(`#passengerName_${i}`).val(),
                    dateOfBirth: $(`#dateOfBirth_${i}`).val(),
                    phone: $(`#phone_${i}`).val(),
                    seatId: $(`#seats_${i}`).val()
                });
            }
            $('#passengerData').val(JSON.stringify(passengerData));

            // Show payment modal
            $('#paymentModal').modal('show');
        } else {
            // Chỉ hiển thị thông báo validate nếu đã đăng nhập
            $('#notificationMessage').text('Please fill in all required fields.');
            $('#notificationModal').modal('show');
        }
    });
});

// Validate payment method before submitting the payment form
$('#paymentForm').on('submit', function (e) {
    const paymentMethod = $('#paymentMethod').val();
    if (!paymentMethod) {
        e.preventDefault();
        alert('Please select a payment method.');
    }
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
    localStorage.removeItem('passengerData'); // Clear saved passenger data on page load
    loadSeats(1);
    loadFormData();
    calculateTotalPrice(); // Calculate total price on page load
    checkFormValidity();
});