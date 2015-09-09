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
function getPageLogs(pageNumber) {
    var args = {
        'page.currentPage' : pageNumber,
        'page.pageSize' : getDatagridPaginationPageSize('#manager-panel')
    };
    $.post(URLS.GET_PAGE_RECORDS, args, function(data) {
        if (data) {
            loadDatagridData('#manager-panel', data);
        }
    });
}

$(function() {
    $('#manager-panel').datagrid({
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        fitColumns : true,
        striped : true,
        height : 310,
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
        } ] ]
    });

    $('#manager-panel').datagrid('getPager').pagination({
        onSelectPage : function(pageNumber, size) {
            getPageLogs(pageNumber);
        },
    });

    getPageLogs(startPage);

    $(window).resize(function() {
        $('#manager-panel').datagrid('resize');
    });
});