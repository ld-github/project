$(function() {
    /**
     * kaptcha img change
     */
    $('#kaptcha').click(function() {
        $(this).slideUp().attr('src', '../kaptcha.jpg?' + Math.floor(Math.random() * 100)).slideDown();
    });
});