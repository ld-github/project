var startPage = 1;

/**
 * Action urls
 */
var URLS = {
    GET_PAGE_RECORDS : '../customer!getPageRecords.action',
    GET_customer : '../customer!getCustomerInfo.action',
};

/**
 * Get page customer
 * 
 * @param pageNumber
 */
function getPageCustomer() {
    var args = getSearchParams();
    setPaginationPageParams(CUSTOMER_PANEL, args);

    $.post(URLS.GET_PAGE_RECORDS, args, function(data) {
        if (data) {
            loadDatagridData(CUSTOMER_PANEL, data);
            initLinkBtns();
        }
    });
}

var CUSTOMER_PANEL = '#customer-panel';

function initDatagrid() {
    $(CUSTOMER_PANEL).datagrid({
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        fitColumns : true,
        striped : true,
        height : 340,
        columns : [ [ {
            field : 'username',
            title : '姓名',
            width : 100,
        }, {
            field : 'telNo',
            title : '手机号码',
            width : 100,
            align : 'center'
        }, {
            field : 'gender',
            title : '性别',
            width : 100,
            align : 'center'
        }, {
            field : 'createDatetime',
            title : '创建时间',
            width : 100,
            align : 'center'
        } ] ],
        toolbar : [ {
            id : 'add-customer-btn',
            text : '添加',
            iconCls : 'icon-add',
            handler : function() {
                new CustomerEditor().init(EDITOR_STATUS.NEW);
            }
        }, '-', {
            id : 'show-customer-btn',
            text : '查看',
            iconCls : 'icon-print',
            disabled : true,
            handler : function() {
                new CustomerEditor().init(EDITOR_STATUS.VIEW);
                showCustomer();
            }
        }, '-', {
            id : 'update-customer-btn',
            text : '编辑',
            iconCls : 'icon-edit',
            disabled : true,
            handler : function() {
                new CustomerEditor().init(EDITOR_STATUS.EDIT);
            }
        }, '-', {
            id : 'delete-customer-btn',
            text : '删除',
            iconCls : 'icon-remove',
            disabled : true,
            handler : function() {
            }
        } ]
    });
}

var searchParams = {};

/**
 * Set search params
 * 
 * @param json
 */
function setSearchParams(json) {
    searchParams = json;
}

/**
 * Get search params
 * 
 * @returns {___anonymous_searchParams}
 */
function getSearchParams() {
    return searchParams;
}

/**
 * Init link bottons
 */
function initLinkBtns() {
    $('a[id$="-customer-btn"]').linkbutton('disable');
    $('#add-customer-btn').linkbutton('enable');
}

/**
 * Change buttons status when datagrid select row
 * 
 */
function changeLinkBtnsOnSelectRow() {
    $('#show-customer-btn').linkbutton('enable');
    $('#update-customer-btn').linkbutton('enable');
    $('#delete-customer-btn').linkbutton('enable');
}

/**
 * search by search form params
 */
function search() {
    setSearchParams($('#search-form').serializeJson());
    setDatagridPager(CUSTOMER_PANEL, startPage, getDatagridPaginationPageSize(CUSTOMER_PANEL));
    getPageCustomer();
}

/**
 * Show datagrid select row customer info
 */
function showCustomer() {
    var args = {
        'customer.id' : getDatagridSelectRow(CUSTOMER_PANEL).id,
    };
    console.log(args);
}

/**
 * Editor status
 */
var EDITOR_STATUS = {
    NEW : 1,
    EDIT : 2,
    VIEW : 3
};

var CUSTOMER_EDITOR_PANEL = '#customer-editor-panel';
var CUSTOMER_EDITOR_FORM = '#customer-editor-form';

/**
 * customer editor
 */
var CustomerEditor = function() {
    this.title = '';
    this.iconCls = '';
    this.buttons = [];

    this.init = function(editorStatus) {
        document.getElementById('customer-editor-form').reset();
        $(CUSTOMER_EDITOR_FORM).show();

        if (editorStatus == EDITOR_STATUS.NEW) {
            this.title = '添加客户信息';
            this.iconCls = 'icon-add';
            var saveBtn = {
                text : '保存',
                iconCls : 'icon-save',
                handler : function() {
                    console.log($(CUSTOMER_EDITOR_FORM).serializeJson());
                }
            };
            this.buttons.push(saveBtn);
        }
        if (editorStatus == EDITOR_STATUS.EDIT) {
            this.title = '编辑客户信息';
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
            this.title = '查看客户信息';
            this.iconCls = 'icon-print';
        }

        var cancelBtn = {
            text : '关闭',
            iconCls : 'icon-cancel',
            handler : function() {
                $(CUSTOMER_EDITOR_PANEL).dialog('close');
            }
        };
        this.buttons.push(cancelBtn);

        $(CUSTOMER_EDITOR_PANEL).dialog({
            title : this.title,
            iconCls : this.iconCls,
            width : 1125,
            height : 450,
            cache : false,
            modal : true,
            buttons : this.buttons
        });
    };
};

$(function() {
    initDatagrid();

    $(CUSTOMER_PANEL).datagrid({
        onSelect : function(index, row) {
            if (undefined == row.id) {
                return;
            }
            changeLinkBtnsOnSelectRow();
        },
    });

    $(CUSTOMER_PANEL).datagrid('getPager').pagination({
        onSelectPage : function(pageNumber, size) {
            getPageCustomer();
        },
    });

    getPageCustomer(startPage);

    $(window).resize(function() {
        $(CUSTOMER_PANEL).datagrid('resize');
    });

    $('#search-btn').click(search);
});