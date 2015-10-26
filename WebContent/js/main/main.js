/**
 * @author LD
 */
var MENUS = [ {
    title : '系统管理',
    iconCls : 'icon-setting',
    submenus : [ {
        title : '用户管理',
        url : 'manager.html'
    }, {
        title : '地图管理',
        url : 'map.html'
    }, {
        title : '消息推送',
        url : 'message.html'
    } ]
}, {
    title : '日志记录',
    iconCls : 'icon-print',
    submenus : [ {
        title : '异常信息',
        url : 'exceptionLog.html'
    } ]
} ];

/**
 * Init menu to menu-panel
 */
function initMenu() {
    $('#menu-panel').accordion({
        fit : true
    });
    for (var i = 0; i < MENUS.length; i++) {
        var title = MENUS[i].title;
        var iconCls = MENUS[i].iconCls;

        var submenus = $('<UL>').addClass('submenus-panel');
        for (var j = 0; j < MENUS[i].submenus.length; j++) {
            var menu = MENUS[i].submenus[j];
            $('<LI>').addClass('submenu-item-panel').appendTo(submenus).html(menu.title).data('menu', menu);
        }
        $('#menu-panel').accordion('add', {
            title : title,
            iconCls : iconCls,
            selected : false,
            content : submenus,
        });
    }
    $('.submenu-item-panel').click(function() {
        $('.submenu-item-panel').removeClass('selected');
        $(this).addClass('selected');
        var menu = $(this).data('menu');
        if (menu.url) {
            addTab(menu.title, menu.url);
        }
    });
}

/**
 * Init Tabs
 */
function initTabs() {
    $('#content-panel').tabs({
        fit : true,
        tabHeight : 25,
        border : false
    });
}

/**
 * Load dateTime
 * 
 * @param id
 */
function loadDatetime(id) {
    var date = new Date();
    var week = date.getDay();
    var days = [ "日 ", "一 ", "二 ", "三 ", "四 ", "五 ", "六 " ];

    var dateTime = date.toLocaleString() + " 星期" + days[week];
    $(id).text(dateTime);

    setTimeout(function() {
        loadDatetime(id);
    }, 1000);
}

/**
 * Add page to content-panel
 * 
 * @param title
 * @param url
 * @param closable
 */
function addTab(title, url, closable) {
    closable = typeof (closable) == 'boolean' ? closable : true;
    var tab = $('#content-panel');
    if (tab.tabs('exists', title)) {
        tab.tabs('select', title);
    } else {
        var content = $('<IFRAME>').attr({
            frameborder : 0,
            src : url,
        });
        tab.tabs('add', {
            title : title,
            closable : closable,
            content : content,
        });
    }
}

/**
 * Init Calendar
 */
function initCalendar() {
    $('#calendar-panel').calendar({
        current : new Date(),
        width : 190,
        height : 190
    });
}
/**
 * Action urls
 */
var URLS = {
    TAKE_LOGIN_MANAGER : '../manager!takeLoginManager.action',
};

/**
 * Get login manager info from session
 */
function getLoginManager() {
    $.post(URLS.TAKE_LOGIN_MANAGER, function(data) {
        console.log(data);
    });
}

$(function() {
    initMenu();
    initTabs();
    initCalendar();

    loadDatetime('#datetime-panel');

    getLoginManager();

    $('#left-panel').panel({
        onCollapse : function() {
            $('#content-panel').tabs('resize');
        }
    });

    $('#right-box').show();
});
