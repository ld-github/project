var TIMEROUT_TO_LOGIN = 2000;

$(function() {
    /**
     * Ajax Start
     */
    $(document).ajaxStart(function() {
        $('#loading').show();
    });

    /**
     * Ajax Stop
     */
    $(document).ajaxStop(function() {
        $('#loading').hide();
    });

    /**
     * Ajax Error
     */
    $(document).ajaxError(function(event, request, settings) {
        $('#loading').hide();
        new Message('加载错误...').show(false);
        if (request.status == 403) {
            new Message('用户登录已过期，请重新登录...').show(false);
            setTimeout(function() {
                window.top.location.href = 'login.html';
            }, TIMEROUT_TO_LOGIN);
        }
    });
});