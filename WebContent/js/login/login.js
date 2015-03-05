/**
 * Form validate
 * 
 * @returns
 */
function formValidate() {
    if ($('#username').val() == '') {
        return '请输入用户名';
    }
    if ($('#password').val() == '') {
        return '请输入密码';
    }
    if ($('#kaptcha').val() == '') {
        return '请输入验证码';
    }
    return false;
}

/**
 * Change kaptcha img
 */
function changeKaptcha() {
    $('#kaptcha-img').slideUp().attr('src', '../kaptcha.jpg?' + Math.floor(Math.random() * 100)).slideDown();
}

/**
 * Action urls
 */
var URLS = {
    LOGIN : '../user!login.action',
    CHECK_SYSTEM : '../system!checkSystem.action',
};

/**
 * Login
 */
function login() {
    var result = formValidate();
    if (result) {
        new Message(result, '温馨提示').show(false);
        return;
    }
    var args = new ActionForm().toJson([ 'username', 'password', 'kaptcha' ]);
    args['user.password'] = Base64.encode(args['user.password']);

    $.post(URLS.LOGIN, args, function(data) {
        if (data.success) {
            location.href = "main.html";
            return;
        }
        if (data.message) {
            new Message(data.message, '温馨提示').show(false);
        }
        changeKaptcha();
    });
}
/**
 * Check whether the initialization system configuration
 */
function checkSystem() {
    $.post(URLS.CHECK_SYSTEM, {}, function(data) {
        if (data.success) {
            $('#cover-panel').css('display', 'block');
            $('#system-config-panel').fadeIn("slow");
        }
    });
}

var KEY_CODE = {
    ENTER : 13
};

$(function() {
    checkSystem();
    /**
     * Change kaptcha img
     */
    $('#kaptcha-img').click(function() {
        changeKaptcha();
    });

    /**
     * Login
     */
    $('#login-btn').click(function() {
        login();
    });

    /**
     * Monitor user enter button press
     */
    $(window).keydown(function(event) {
        if (event.keyCode == KEY_CODE.ENTER) {
            login();
        }
    });
});