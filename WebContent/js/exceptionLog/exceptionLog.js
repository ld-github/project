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
function getPage() {
    var args = getSearchParams();
    setPaginationPageParams(LOGS_PANEL, args);
    $.post(URLS.GET_PAGE_RECORDS, args, function(data) {
        if (data) {
            loadDatagridData(LOGS_PANEL, data);
        }
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
    searchParams.beginDate = $('#begin-date').datebox('getValue');
    searchParams.endDate = $('#end-date').datebox('getValue');
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
 * Search by search form params
 */
function search() {
    setSearchParams($('#search-form').serializeJson());
    setDatagridPager(LOGS_PANEL, startPage, getDatagridPaginationPageSize(LOGS_PANEL));
    getPage();
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
            formatter : function(value, row, index) {
                return $('<SPAN>').append($('<SPAN>').attr('title', value).html(value)).html();
            }
        }, {
            field : 'createDatetime',
            title : '发生时间',
            width : 100,
            align : 'center'
        } ] ]
    });
}

/**
 * Init dateBox
 */
function initDateBox() {
    var beginDateButtons = $.extend([], $.fn.datebox.defaults.buttons);
    beginDateButtons.splice(1, 0, {
        text : '清空',
        handler : function(target) {
            $('#begin-date').datebox('setValue', '');
        }
    });

    $('#begin-date').datebox({
        editable : false,
        panelWidth : 180,
        buttons : beginDateButtons
    });

    var endDateButtons = $.extend([], $.fn.datebox.defaults.buttons);
    endDateButtons.splice(1, 0, {
        text : '清空',
        handler : function(target) {
            $('#end-date').datebox('setValue', '');
        }
    });
    $('#end-date').datebox({
        editable : false,
        panelWidth : 180,
        buttons : endDateButtons
    });
}

$(function() {
    initDateBox();
    initDatagrid();

    $(LOGS_PANEL).datagrid('getPager').pagination({
        onSelectPage : function(pageNumber, size) {
            getPage();
        },
    });

    getPage();

    $('#search-btn').click(search);

    $(window).resize(function() {
        $(LOGS_PANEL).datagrid('resize');
    });
});