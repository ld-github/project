/**
 * Init dateBox
 */
function initDateBox() {
    var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
    buttons.splice(1, 0, {
        text : '清空',
        handler : function(target) {
            $('#send-datetime').datetimebox('setValue', '');
        }
    });

    $('#send-datetime').datetimebox({
        editable : false,
        panelWidth : 180,
        buttons : buttons
    });
}

var MESSAGE_PANEL = "#message-panel";

/**
 * Init datagrid
 */
function initDatagrid() {
    $(MESSAGE_PANEL).datagrid({
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        fitColumns : true,
        striped : true,
        height : 340,
        columns : [ [ {
            field : 'title',
            title : '标题',
            width : 100,
        }, {
            field : 'createDatetime',
            title : '创建时间',
            width : 100,
            align : 'center'
        } ] ],
        toolbar : [ {
            id : 'add-message-btn',
            text : '添加',
            iconCls : 'icon-add',
            handler : function() {
                new MessageEditor().init(EDITOR_STATUS.NEW);
            }
        }, '-', {
            id : 'show-message-btn',
            text : '查看',
            iconCls : 'icon-print',
            disabled : true,
            handler : function() {
                new MessageEditor().init(EDITOR_STATUS.VIEW);
                showCustomer();
            }
        }, '-', {
            id : 'update-message-btn',
            text : '编辑',
            iconCls : 'icon-edit',
            disabled : true,
            handler : function() {
                new MessageEditor().init(EDITOR_STATUS.EDIT);
            }
        }, '-', {
            id : 'delete-message-btn',
            text : '删除',
            iconCls : 'icon-remove',
            disabled : true,
            handler : function() {
            }
        } ]
    });
}

/**
 * Editor status
 */
var EDITOR_STATUS = {
    NEW : 1,
    EDIT : 2,
    VIEW : 3
};

var MESSAGE_EDITOR_PANEL = '#message-editor-panel';
var MESSAGE_EDITOR_FORM = 'message-editor-form';

/**
 * customer editor
 */
var MessageEditor = function() {
    this.title = '';
    this.iconCls = '';
    this.buttons = [];

    this.init = function(editorStatus) {
        document.getElementById(MESSAGE_EDITOR_FORM).reset();
        $('#' + MESSAGE_EDITOR_FORM).show();

        if (editorStatus == EDITOR_STATUS.NEW) {
            this.title = '添加消息信息';
            this.iconCls = 'icon-add';
            var saveBtn = {
                text : '保存',
                iconCls : 'icon-save',
                handler : function() {
                    console.log($(MESSAGE_EDITOR_FORM).serializeJson());
                }
            };
            this.buttons.push(saveBtn);
        }
        if (editorStatus == EDITOR_STATUS.EDIT) {
            this.title = '编辑消息信息';
            this.iconCls = 'icon-edit';
            var updateBtn = {
                text : '保存',
                iconCls : 'icon-save',
                handler : function() {
                }
            };
            this.buttons.push(updateBtn);
        }
        if (editorStatus == EDITOR_STATUS.VIEW) {
            this.title = '查看消息信息';
            this.iconCls = 'icon-print';
        }

        var cancelBtn = {
            text : '关闭',
            iconCls : 'icon-cancel',
            handler : function() {
                $(MESSAGE_EDITOR_PANEL).dialog('close');
            }
        };
        this.buttons.push(cancelBtn);

        $(MESSAGE_EDITOR_PANEL).dialog({
            title : this.title,
            iconCls : this.iconCls,
            width : 1005,
            height : 450,
            cache : false,
            modal : true,
            collapsible : true,
            buttons : this.buttons
        });
    };
};

/**
 * 初始化上传控件
 */
function initUploader() {
    var uploader = new Uploader().init('#uploader-box');

    uploader.onUploadBeforeSend = function(object, data, headers) {
    };
    uploader.onUploadSuccess = function(file, response) {
    };

    $('#uploader-panel').show();

    $('#uploader-panel').dialog({
        width : 680,
        height : 460,
        modal : true,
        title : '文件上传',
        iconCls : 'icon-save',
    });
}

$(function() {
    initDateBox();
    initDatagrid();

    $('#upload-title-image').click(initUploader);
});