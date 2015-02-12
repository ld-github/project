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
        new Message(data.message, '温馨提示').show(false);
        changeKaptcha();
    });
}

var KEY_CODE = {
    ENTER : 13
};

$(function() {
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