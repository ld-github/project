var startPage = 1;

/**
 * Action urls
 */
var URLS = {
    GET_PAGE_RECORDS : '../manager!getPageRecords.action',
};

/**
 * 
 * @param pageNumber
 */
function getPageManagers() {
    var args = getAdvancedSearchParams();
    args['page.currentPage'] = getDatagridPaginationPageNum('#manager-panel');
    args['page.pageSize'] = getDatagridPaginationPageSize('#manager-panel');

    $.post(URLS.GET_PAGE_RECORDS, args, function(data) {
        if (data) {
            loadDatagridData('#manager-panel', data);
        }
    });
}

function initDatagrid() {
    $('#manager-panel').datagrid({
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
            id : 'show-manager',
            text : '查看',
            iconCls : 'icon-save',
            handler : function() {
            }
        }, '-', {
            id : 'update-manager',
            text : '修改',
            iconCls : 'icon-edit',
            handler : function() {
            }
        }, '-', {
            id : 'add-manager',
            text : '添加',
            iconCls : 'icon-add',
            handler : function() {
            }
        }, '-', {
            id : 'delete-manager',
            text : '删除',
            iconCls : 'icon-remove',
            handler : function() {
            }
        }, '-', {
            id : 'disable-manager',
            text : '禁用',
            iconCls : 'icon-no',
            handler : function() {
            }
        }, '-', {
            id : 'enable-manager',
            text : '启用',
            iconCls : 'icon-ok',
            handler : function() {
            }
        } ]
    });
}

var searchParams = {};

function setAdvancedSearchParams(json) {
    searchParams = json;
}

function getAdvancedSearchParams() {
    return searchParams;
}

/**
 * Advanced search
 */
function advancedSearch() {
    setAdvancedSearchParams($('#search-form').serializeJson());
    setDatagridPager('#manager-panel', startPage, getDatagridPaginationPageSize('#manager-panel'));
    getPageManagers();
}

$(function() {
    initDatagrid();

    $('#manager-panel').datagrid('getPager').pagination({
        onSelectPage : function(pageNumber, size) {
            getPageManagers();
        },
    });

    getPageManagers(startPage);

    $(window).resize(function() {
        $('#manager-panel').datagrid('resize');
    });

    $('#advanced-search-btn').click(advancedSearch);
});