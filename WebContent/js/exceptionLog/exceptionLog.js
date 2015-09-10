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
function getPageLogs() {
    var args = setPaginationPageParams(LOGS_PANEL);
    $.post(URLS.GET_PAGE_RECORDS, args, function(data) {
        if (data) {
            loadDatagridData(LOGS_PANEL, data);
        }
    });
}

var LOGS_PANEL = '#logs-panel';

function initDatagrid() {
    $(LOGS_PANEL).datagrid({
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

    $(LOGS_PANEL).datagrid('getPager').pagination({
        onSelectPage : function(pageNumber, size) {
            getPageLogs();
        },
    });

    getPageLogs(startPage);

    $(window).resize(function() {
        $(LOGS_PANEL).datagrid('resize');
    });
});