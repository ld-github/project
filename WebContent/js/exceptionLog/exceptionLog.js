/**
 * 
 * @param pageNumber
 * @param pageSize
 */
function setPager(pageNumber, pageSize) {
    $('#logs-panel').datagrid("getPager").pagination({
        "pageNumber" : pageNumber,
        "pageSize" : pageSize
    });
}

/**
 * 
 * @param data
 */
function loadData(data) {
    $('#logs-panel').datagrid('loadData', {
        rows : data.page == null ? [] : data.page.records,
        total : data.page == null ? 0 : data.page.total
    });
}

var startPage = 1, pageSize = 10;

/**
 * Action urls
 */
var URLS = {
    GETPAGERECORDS : '../exceptionLog!getPageRecords.action',
};

/**
 * 
 * @param pageNumber
 */
function queryLogs(pageNumber) {
    var args = {
        'page.currentPage' : pageNumber,
        'page.pageSize' : pageSize
    };
    $.post(URLS.GETPAGERECORDS, args, function(data) {
        loadData(data);
    });
}

$(function() {
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
            width : 50
        }, {
            field : 'username',
            title : '操作人',
            width : 100
        }, {
            field : 'className',
            title : '类名',
            width : 200,
        }, {
            field : 'method',
            title : '方法名',
            width : 100,
        }, {
            field : 'level',
            title : '级别',
            width : 100,
        }, {
            field : 'createTime',
            title : '发生时间',
            width : 100,
        }, {
            field : 'message',
            title : '异常信息',
            width : 200,
        } ] ]
    });

    $('#logs-panel').datagrid('getPager').pagination({
        onSelectPage : function(pageNumber, size) {
            pageSize = size;
            queryLogs(pageNumber);
        },
    });

    queryLogs(startPage);

    $(window).resize(function() {
        $('#logs-panel').datagrid('resize');
    });
});