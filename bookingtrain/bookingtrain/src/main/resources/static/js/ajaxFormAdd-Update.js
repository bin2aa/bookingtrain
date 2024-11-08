$(document).ready(function() {
    $('.addRoleLink').click(function(e) {
        e.preventDefault();
        var href = $(this).attr('href');
        
        $.ajax({
            url: href,
            method: "GET",
            success: function(data) {
                $('#addRoleContainer').html(data).show();
                $('.overlay').show();
            },
            error: function(xhr, status, error) {
                console.error('Lỗi khi tải form:', error);
            }
        });
    });

    $('.overlay').click(function() {
        $('#addRoleContainer').hide();
        $('.overlay').hide();
    });
});