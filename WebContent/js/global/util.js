/**
 * @author LD
 */
var ActionForm = function() {
    this.toJson = function(ids) {
        var json = {};
        if (!(ids instanceof Array)) {
            console.log('Parameter must be an array!');
            return json;
        }
        try {
            for (var i = 0; i < ids.length; i++) {
                var obj = $('#' + ids[i]);
                json[obj.attr('name')] = obj.val();
            }
        } catch (e) {
            console.log('String convert to json failed!');
        }
        return json;
    };
};

/**
 * Jquery serializeArray to serializeJson
 */
(function($) {
    $.fn.serializeJson = function() {
        var serializeObj = {};
        var array = this.serializeArray();
        $(array).each(function() {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [ serializeObj[this.name], this.value ];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

/**
 * Message Type
 */
var MESSAGE_TYPES = {
    ERROR : 'error',
    QUESTION : 'question',
    INFO : 'info',
    WARNING : 'warning'
};

var TIMEROUT_DEFEAT = 3000;

/**
 * Show message
 */
var Message = function(msg, title) {
    this.msg = msg == undefined ? '确定当前操作?' : msg;
    this.title = title == undefined ? '提示消息' : title;

    this.show = function(flag, timeout) {
        var color = flag == undefined ? 'green' : flag ? 'green' : 'red';
        this.msg = '<span style="color:' + color + '">' + this.msg + '</span>';
        timeout = (timeout != undefined && !isNaN(timeout)) ? timeout : TIMEROUT_DEFEAT;
        $.messager.show({
            title : this.title,
            msg : this.msg,
            timeout : timeout,
            style : {
                top : document.body.scrollTop + document.documentElement.scrollTop,
            }
        });
    };

    this.alert = function(icon) {
        icon = icon == undefined ? MESSAGE_TYPES.INFO : icon;
        $.messager.alert(this.title, this.msg, icon);
    };

    this.confirm = function(callback, param) {
        $.messager.confirm(this.title, this.msg, function(r) {
            if (r && callback && typeof (callback) === 'function') {
                callback(param);
            }
        });
    };
};

var DEFAULT_DATAGRID_PAGENUM = 1;

/**
 * Get Datagrid Pagination pageSize
 * 
 * @param id
 * @returns
 */
function getDatagridPaginationPageSize(id) {
    return $(id).datagrid('getPager').pagination('options').pageSize;
}

/**
 * Get Datagrid Pagination PageNum
 * 
 * @param id
 * @returns
 */
function getDatagridPaginationPageNum(id) {
    var pageNum = $(id).datagrid('getPager').pagination('options').pageNumber;
    return pageNum == 0 ? DEFAULT_DATAGRID_PAGENUM : pageNum;
}

/**
 * Set Datagrid pager
 * 
 * @param pageNumber
 * @param pageSize
 */
function setDatagridPager(id, pageNum, pageSize) {
    $(id).datagrid("getPager").pagination({
        "pageNumber" : pageNum,
        "pageSize" : pageSize
    });
}

/**
 * Update Datagrid row
 * 
 * @param id
 * @param index
 * @param data
 */
function updateDatagridRow(id, index, data) {
    $(id).datagrid('updateRow', {
        index : index,
        row : data
    });
}

/**
 * Load Datagrid Data
 * 
 * @param data
 */
function loadDatagridData(id, data) {
    $(id).datagrid('loadData', {
        rows : data.page == null ? [] : data.page.records,
        total : data.page == null ? 0 : data.page.total
    });
}

/**
 * Get datagrid select row
 * 
 * @param id
 * @returns
 */
function getDatagridSelectRow(id) {
    return $(id).datagrid('getSelected');
}

/**
 * Remove toolbar menu
 * 
 * @param id
 * @param removeNext
 */
function removeToolBarMenu(id, removeNext) {
    if (removeNext) {
        $(id).parent().next().remove();
    }
    $(id).parent().remove();
}