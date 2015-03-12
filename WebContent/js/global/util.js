/**
 * @author LD
 */
var ActionForm = function() {
    this.toJson = function(ids) {
        var json = {};
        if (!(ids instanceof Array)) {
            console.log('Parameter must be an array!');
            return json;
        }
        try {
            for ( var i = 0; i < ids.length; i++) {
                var obj = $('#' + ids[i]);
                json[obj.attr('name')] = obj.val();
            }
        } catch (e) {
            console.log('String convert to json failed!');
        }
        return json;
    };
};

/**
 * Message Type
 */
var MESSAGE_TYPES = {
    ERROR : 'error',
    QUESTION : 'question',
    INFO : 'info',
    WARNING : 'warning'
};

var TIMEROUT_DEFEAT = 3000;

/**
 * Show message
 */
var Message = function(msg, title) {
    this.msg = msg == undefined ? '确定当前操作?' : msg;
    this.title = title == undefined ? '提示消息' : title;

    this.show = function(flag, timeout) {
        var color = flag == undefined ? 'green' : flag ? 'green' : 'red';
        this.msg = '<span style="color:' + color + '">' + this.msg + '</span>';
        timeout = (timeout != undefined && !isNaN(timeout)) ? timeout : TIMEROUT_DEFEAT;
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
        icon = icon == undefined ? MESSAGE_TYPES.INFO : icon;
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