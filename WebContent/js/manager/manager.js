var startPage = 1;

/**
 * Action urls
 */
var URLS = {
    GET_PAGE_RECORDS : '../manager!getPageRecords.action',
};

/**
 * Get page manager
 * 
 * @param pageNumber
 */
function getPageManager() {
    var args = getAdvancedSearchParams();
    args['page.currentPage'] = getDatagridPaginationPageNum(MANAGER_PANEL);
    args['page.pageSize'] = getDatagridPaginationPageSize(MANAGER_PANEL);

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
            }
        }, '-', {
            id : 'show-manager-btn',
            text : '查看',
            iconCls : 'icon-save',
            disabled : true,
            handler : function() {
            }
        }, '-', {
            id : 'update-manager-btn',
            text : '修改',
            iconCls : 'icon-edit',
            disabled : true,
            handler : function() {
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
            }
        }, '-', {
            id : 'enable-manager-btn',
            text : '启用',
            iconCls : 'icon-ok',
            disabled : true,
            handler : function() {
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