/**
 * @author LD
 */
var ActionForm = function() {
    this.toJson = function(ids) {
        if (!(ids instanceof Array)) {
            console.log('Parameter must be an array!');
            return null;
        }
        try {
            var json = {};
            for (var i = 0; i < ids.length; i++) {
                var obj = $('#' + ids[i]);
                var key = obj.attr('name');
                var val = obj.val();
                json[key] = val == '' || isNaN(val) ? val : parseInt(val);
            }
            return json;
        } catch (e) {
            console.log('String convert to json failed!');
            return null;
        }
    };
};

var MSSAGE_ICOS = {
    ERROR : 'error',
    QUESTION : 'question',
    INFO : 'info',
    WARNING : 'warning'
};

/**
 * Show message
 */
var Message = function(msg, title) {
    this.msg = msg == undefined ? '确定当前操作?' : msg;
    this.title = title == undefined ? '提示消息' : title;

    this.show = function(flag, timeout) {
        var color = flag == undefined ? 'green' : flag ? 'green' : 'red';
        this.msg = '<span style="color:' + color + '">' + this.msg + '</span>';
        timeout = (timeout != undefined && !isNaN(timeout)) ? timeout : 3000;
        $.messager.show({
            title : this.title,
            msg : this.msg,
            timeout : timeout,
            style : {
                top : document.body.scrollTop + document.documentElement.scrollTop,
            }
        });
    };

    this.alert = function(icon) {
        icon = icon == undefined ? MSSAGE_ICOS.INFO : icon;
        $.messager.alert(this.title, this.msg, icon);
    };

    this.confirm = function(callback, param) {
        $.messager.confirm(this.title, this.msg, function(r) {
            if (r && callback && typeof (callback) === 'function') {
                callback(param);
            }
        });
    };
};

/**
 * CHECK_LOGIN_USER
 */
var CHECK_LOGIN_USER = '../user!checkLoginUser.action';

/**
 * check login user info
 */
function checkLoginUser() {
    $.post(CHECK_LOGIN_USER, {}, function(data) {
        if (data.success) {
            window.location.href = 'login.html';
        }
    });
}

$(function() {
    /**
     * Ajax Start
     */
    $(document).ajaxStart(function() {
        if (window.location.pathname.indexOf('login.html') == -1) {
            checkLoginUser();
        }
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
        new Message('系统错误', '温馨提示').show(false);
    });
});