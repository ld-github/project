/**
 * Form validate
 * 
 * @returns
 */
function formValidate() {
    if ($('#username').val() == '') {
        return '请输入账号';
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
 * Change Kaptcha
 */
function changeKaptcha() {
    $('#kaptcha-img').slideUp().attr('src', '../kaptcha.jpg?' + Math.floor(Math.random() * 100)).slideDown();
}

/**
 * Action urls
 */
var URLS = {
    LOGIN : '../user!login.action',
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
    $.post(URLS.LOGIN, {}, function(data) {
    });
}

$(function() {
    /**
     * Kaptcha img change
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
});