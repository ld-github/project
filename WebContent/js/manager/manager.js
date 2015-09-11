var startPage = 1;

/**
 * Action urls
 */
var URLS = {
    GET_PAGE_RECORDS : '../manager!getPageRecords.action',
    CHANGE_AVAILABLE : '../manager!changeAvailable.action'
};

/**
 * Get page manager
 * 
 * @param pageNumber
 */
function getPageManager() {
    var args = getAdvancedSearchParams();
    setPaginationPageParams(MANAGER_PANEL, args);

    $.post(URLS.GET_PAGE_RECORDS, args, function(data) {
        if (data) {
            loadDatagridData(MANAGER_PANEL, data);
            initLinkBtns();
        }
    });
}

var MANAGER_PANEL = '#manager-panel';

function initDatagrid() {
    $(MANAGER_PANEL).datagrid({
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        fitColumns : true,
        striped : true,
        height : 340,
        columns : [ [ {
            field : 'username',
            title : '账号',
            width : 100,
        }, {
            field : 'administrator',
            title : '超级管理员',
            width : 60,
            align : 'center',
            formatter : function(value, row, index) {
                return value ? '是' : '否';
            },
            styler : function(value, row, index) {
                if (!value) {
                    return 'color:red;';
                }
            }
        }, {
            field : 'available',
            title : '是否可用',
            width : 60,
            align : 'center',
            formatter : function(value, row, index) {
                return value ? '是' : '否';
            },
            styler : function(value, row, index) {
                if (!value) {
                    return 'color:red;';
                }
            }
        }, {
            field : 'createTime',
            title : '创建时间',
            width : 100,
            align : 'center'
        } ] ],
        toolbar : [ {
            id : 'add-manager-btn',
            text : '添加',
            iconCls : 'icon-add',
            handler : function() {
                new ManagerEditor().init(EDITOR_STATUS.NEW);
            }
        }, '-', {
            id : 'show-manager-btn',
            text : '查看',
            iconCls : 'icon-print',
            disabled : true,
            handler : function() {
                new ManagerEditor().init(EDITOR_STATUS.VIEW);
            }
        }, '-', {
            id : 'update-manager-btn',
            text : '编辑',
            iconCls : 'icon-edit',
            disabled : true,
            handler : function() {
                new ManagerEditor().init(EDITOR_STATUS.EDIT);
            }
        }, '-', {
            id : 'delete-manager-btn',
            text : '删除',
            iconCls : 'icon-remove',
            disabled : true,
            handler : function() {
            }
        }, '-', {
            id : 'disable-manager-btn',
            text : '禁用',
            iconCls : 'icon-no',
            disabled : true,
            handler : function() {
                var msg = '确定禁用选中管理员?';
                new Message(msg).confirm(changeAvailable, false);
            }
        }, '-', {
            id : 'enable-manager-btn',
            text : '启用',
            iconCls : 'icon-ok',
            disabled : true,
            handler : function() {
                var msg = '确定启用选中管理员?';
                new Message(msg).confirm(changeAvailable, true);
            }
        } ]
    });
}

var searchParams = {};

/**
 * Set advanced search params
 * 
 * @param json
 */
function setAdvancedSearchParams(json) {
    searchParams = json;
}

/**
 * Get advanced search params
 * 
 * @returns {___anonymous_searchParams}
 */
function getAdvancedSearchParams() {
    return searchParams;
}

/**
 * Init link bottons
 */
function initLinkBtns() {
    $('a[id$="-manager-btn"]').linkbutton('disable');
    $('#add-manager-btn').linkbutton('enable');
}

/**
 * Change buttons status when datagrid select row
 * 
 * @param available
 */
function changeLinkBtnsOnSelectRow(available) {
    $('#show-manager-btn').linkbutton('enable');
    $('#update-manager-btn').linkbutton('enable');
    $('#delete-manager-btn').linkbutton('enable');
    if (available) {
        $('#disable-manager-btn').linkbutton('enable');
        $('#enable-manager-btn').linkbutton('disable');
    } else {
        $('#disable-manager-btn').linkbutton('disable');
        $('#enable-manager-btn').linkbutton('enable');
    }
}

/**
 * Advanced search
 */
function advancedSearch() {
    setAdvancedSearchParams($('#search-form').serializeJson());
    setDatagridPager(MANAGER_PANEL, startPage, getDatagridPaginationPageSize(MANAGER_PANEL));
    getPageManager();
}

/**
 * Change manager available
 * 
 * @param available
 */
function changeAvailable(available) {
    var m = getDatagridSelectRow(MANAGER_PANEL);
    var index = getDatagridSelectRowIndex(MANAGER_PANEL, m);
    var args = {
        'manager.id' : m.id,
        'manager.available' : available
    };
    $.post(URLS.CHANGE_AVAILABLE, args, function(data) {
        if (data && data.status) {
            var manager = data.manager;
            updateDatagridRow(MANAGER_PANEL, index, manager);
            changeLinkBtnsOnSelectRow(manager.available);
        } else {
            new Message(data.message).show(false);
        }
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

var MANAGER_EDITOR_PANEL = "#manager-editor-panel";

/**
 * Manager editor
 */
var ManagerEditor = function() {
    this.title = '';
    this.iconCls = '';
    this.buttons = [];

    this.init = function(editorStatus) {
        document.getElementById("manager-editor-form").reset();
        $('#manager-editor-form').show();

        if (editorStatus == EDITOR_STATUS.NEW) {
            this.title = '添加管理员信息';
            this.iconCls = "icon-add";
            var saveBtn = {
                text : '保存',
                iconCls : 'icon-save',
                handler : function() {
                    new ManagerEditor().init(EDITOR_STATUS.NEW);
                }
            };
            this.buttons.push(saveBtn);
        }
        if (editorStatus == EDITOR_STATUS.EDIT) {
            this.title = '编辑管理员信息';
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
            this.title = '查看管理员信息';
            this.iconCls = 'icon-print';
        }

        var cancelBtn = {
            text : '关闭',
            iconCls : 'icon-cancel',
            handler : function() {
                $(MANAGER_EDITOR_PANEL).dialog('close');
            }
        };
        this.buttons.push(cancelBtn);

        $(MANAGER_EDITOR_PANEL).dialog({
            title : this.title,
            iconCls : this.iconCls,
            width : 300,
            height : 240,
            cache : false,
            modal : true,
            buttons : this.buttons
        });
    };
};

$(function() {
    initDatagrid();

    $(MANAGER_PANEL).datagrid({
        onSelect : function(index, row) {
            if (undefined == row.id) {
                return;
            }
            changeLinkBtnsOnSelectRow(row.available);
        },
    });

    $(MANAGER_PANEL).datagrid('getPager').pagination({
        onSelectPage : function(pageNumber, size) {
            getPageManager();
        },
    });

    getPageManager(startPage);

    $(window).resize(function() {
        $(MANAGER_PANEL).datagrid('resize');
    });

    $('#advanced-search-btn').click(advancedSearch);
});