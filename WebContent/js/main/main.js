/**
 * @author LD
 */
var MENU = [ {
    title : '系统设置',
    submenus : [ {
        title : '地图',
        url : 'map.html'
    } ]
}, {
    title : '系统记录',
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
    for (var i = 0; i < MENU.length; i++) {
        var title = MENU[i].title;
        var submenus = $('<UL>').addClass('submenus-panel');
        for (var j = 0; j < MENU[i].submenus.length; j++) {
            var menu = MENU[i].submenus[j];
            $('<LI>').addClass('submenu-item-panel').appendTo(submenus).html(menu.title).data('menu', menu);
        }
        $('#menu-panel').accordion('add', {
            title : title,
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
    var hh = date.getHours();
    var mm = date.getMinutes();
    var ss = date.getSeconds();

    var yy = date.getFullYear();
    var MM = date.getMonth() + 1;
    var dd = date.getDate();

    var week = date.getDay();
    var days = [ "日 ", "一 ", "二 ", "三 ", "四 ", "五 ", "六 " ];

    var dateTime = yy + "年" + MM + "月" + dd + "日 " + hh + ":" + mm + ":" + ss + " 星期" + days[week];
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
        current : new Date()
    });
}
/**
 * Action urls
 */
var URLS = {
    TAKE_LOGIN_USER : '../user!takeLoginUser.action',
};

/**
 * Get login user info from session
 */
function getLoginUser() {
    $.post(URLS.TAKE_LOGIN_USER, function(data) {
        console.log(data);
    });
}

$(function() {
    initMenu();
    initTabs();
    initCalendar();

    loadDatetime('#datetime-panel');

    getLoginUser();

    $('#left-panel').panel({
        onCollapse : function() {
            $('#content-panel').tabs('resize');
        }
    });
});
