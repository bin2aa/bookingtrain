$(document).ready(function () {
    $('#registerForm').on('submit', function (event) {
      event.preventDefault(); // Prevent default form submission

      $.ajax({
        type: 'POST',
        url: $(this).attr('action'), // Get the form action URL
        data: $(this).serialize(), // Serialize form data
        success: function (response) {
          if (response === 'Success') {
            window.location.href = '/login';
          } else {
            let errorMessage = '';
            if (response === 'PasswordNotMatch') {
              errorMessage = 'Password Not Match';
            } else if (response === 'EmailExisted') {
              errorMessage = 'Email existed';
            } else {
              errorMessage = 'Có lỗi xảy ra. Vui lòng thử lại.';
            }
            $('#errorRegisterMessage').text(errorMessage);
            $('#errorRegister').show();
          }
        },
        error: function () {
          $('#errorRegisterMessage').text('Có lỗi xảy ra trong quá trình gửi yêu cầu. Vui lòng thử lại.');
          $('#errorRegister').show();
        }
      });
    });
  });