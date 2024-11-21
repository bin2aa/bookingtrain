$(document).ready(function () {
  $('#registerForm').on('submit', function (event) {
    event.preventDefault(); // Prevent default form submission

    $.ajax({
      type: 'POST',
      url: $(this).attr('action'), // Get the form action URL
      data: $(this).serialize(), // Serialize form data
      success: function (response) {
        if (response === 'Success') {
          $('#notificationMessage').text('Đăng ký thành công! Vui lòng đăng nhập.');
          $('#notificationModal').modal('show');
          setTimeout(function () {
            window.location.href = '/login';
          }, 3000); // Redirect after 3 seconds
        } else {
          let errorMessage = '';
          if (response === 'PasswordNotMatch') {
            errorMessage = 'Password Not Match';
          } else if (response === 'EmailExisted') {
            errorMessage = 'Email existed';
          } else {
            errorMessage = 'Có lỗi xảy ra. Vui lòng thử lại.';
          }
          $('#notificationMessage').text(errorMessage);
          $('#notificationModal').modal('show');
        }
      },
      error: function () {
        $('#notificationMessage').text('Có lỗi xảy ra trong quá trình gửi yêu cầu. Vui lòng thử lại.');
        $('#notificationModal').modal('show');
      }
    });
  });
});