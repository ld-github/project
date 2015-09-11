/**
 * Form manager login validate
 * 
 * @returns
 */
function formLoginValidate() {
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
 * Form system initialization validate
 */
function formInitValidate() {
    if ($('#init-username').val() == '') {
        return '请输入用户名';
    }
    if ($('#init-password').val() == '') {
        return '请输入密码';
    }
    if ($('#repeat-password').val() == '') {
        return '请输入重复密码';
    }
    if ($('#init-password').val() != $('#repeat-password').val()) {
        return '两次密码不正确';
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
    LOGIN : '../manager!login.action',
    CHECK_SYSTEM : '../system!checkSystem.action',
    INIT_SYSTEM : '../system!initSystem.action',
};

/**
 * Login
 */
function login() {
    var result = formLoginValidate();
    if (result) {
        new Message(result).show(false);
        return;
    }
    var args = $('#login-form').serializeJson();
    args['manager.password'] = Base64.encode(args['manager.password']);

    $.post(URLS.LOGIN, args, function(data) {
        if (data.status) {
            location.href = "main.html";
            return;
        } else {
            new Message(data.message).show(false);
        }
        changeKaptcha();
    });
}

/**
 * Initialization system configuration
 */
function initSystem() {
    var result = formInitValidate();
    if (result) {
        new Message(result).show(false);
        return;
    }
    var args = $('#system-config-form').serializeJson();
    args['manager.password'] = Base64.encode(args['manager.password']);

    $.post(URLS.INIT_SYSTEM, args, function(data) {
        if (data.status) {
            new Message('系统初始化成功').show(true);
            $('#cover-panel').css('display', 'none');
            $('#system-config-form').fadeOut("slow");
            keyEnterListener();
        } else {
            new Message('系统初始化失败').show(false);
        }
    });
}

/**
 * Monitor user enter button press
 */
function keyEnterListener() {
    $(document).keydown(function(event) {
        if (event.keyCode == KEY_CODE.ENTER) {
            login();
        }
    });
}

/**
 * Check whether the initialization system configuration
 */
function checkSystem() {
    $.post(URLS.CHECK_SYSTEM, function(data) {
        if (data.status) {
            $('#system-config-form').fadeIn("slow");
            $('#cover-panel').css('display', 'block');
            /**
             * Initialization system configuration
             */
            $('#init-btn').click(initSystem);
        } else {
            keyEnterListener();
        }
    });
}

var KEY_CODE = {
    ENTER : 13
};

/**
 * Init show placeholder when input is not support attr placeholder
 */
function initPlaceholder() {
    $('input').placeholder();
}

$(function() {
    checkSystem();
    initPlaceholder();

    /**
     * Change kaptcha img
     */
    $('#kaptcha-img').click(changeKaptcha);

    /**
     * Login
     */
    $('#login-btn').click(login);
});