/**
 * Form user login validate
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
    LOGIN : '../user!login.action',
    CHECK_SYSTEM : '../system!checkSystem.action',
    INIT_SYSTEM : '../system!initSystem.action',
};

/**
 * Login
 */
function login() {
    var result = formLoginValidate();
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
 * Initialization system configuration
 */
function initSystem() {
    var result = formInitValidate();
    if (result) {
        new Message(result, '温馨提示').show(false);
        return;
    }
    var args = new ActionForm().toJson([ 'init-username', 'init-password' ]);
    args['user.password'] = Base64.encode(args['user.password']);

    $.post(URLS.INIT_SYSTEM, args, function(data) {
        if (data.success) {
            new Message('系统初始化成功', '温馨提示').show(true);
            $('#cover-panel').css('display', 'none');
            $('#system-config-panel').fadeOut("slow");
            keyEnterListener();
        } else {
            new Message('系统初始化失败', '温馨提示').show(false);
        }
    });
}

/**
 * Monitor user enter button press
 */
function keyEnterListener() {
    $(window).keydown(function(event) {
        if (event.keyCode == KEY_CODE.ENTER) {
            login();
        }
    });
}

/**
 * Check whether the initialization system configuration
 */
function checkSystem() {
    $.post(URLS.CHECK_SYSTEM, {}, function(data) {
        if (data.success) {
            $('#system-config-panel').fadeIn("slow");
            $('#cover-panel').css('display', 'block');
        } else {
            keyEnterListener();
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
     * Initialization system configuration
     */
    $('#init-btn').click(function() {
        initSystem();
    });
});