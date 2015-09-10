var startPage = 1;

/**
 * Action urls
 */
var URLS = {
    GET_PAGE_RECORDS : '../exceptionLog!getPageRecords.action',
};

/**
 * 
 * @param pageNumber
 */
function getPageLogs(pageNumber) {
    var args = {
        'page.currentPage' : pageNumber,
        'page.pageSize' : getDatagridPaginationPageSize('#logs-panel')
    };
    $.post(URLS.GET_PAGE_RECORDS, args, function(data) {
        if (data) {
            loadDatagridData('#logs-panel', data);
        }
    });
}

function initDatagrid() {
    $('#logs-panel').datagrid({
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        fitColumns : true,
        striped : true,
        height : 310,
        columns : [ [ {
            field : 'id',
            title : '编号',
            width : 80,
            align : 'center'
        }, {
            field : 'username',
            title : '操作人',
            width : 100,
        }, {
            field : 'className',
            title : '类名',
            width : 200,
        }, {
            field : 'method',
            title : '方法名',
            width : 100,
        }, {
            field : 'message',
            title : '异常信息',
            width : 200,
        }, {
            field : 'createTime',
            title : '发生时间',
            width : 100,
            align : 'center'
        } ] ]
    });
}

$(function() {
    initDatagrid();

    $('#logs-panel').datagrid('getPager').pagination({
        onSelectPage : function(pageNumber, size) {
            getPageLogs(pageNumber);
        },
    });

    getPageLogs(startPage);

    $(window).resize(function() {
        $('#logs-panel').datagrid('resize');
    });
});