/**
 * @author LD
 */
var MENUS = [ {
    title : '租赁管理',
    iconCls : 'icon-info',
    submenus : [ {
        title : '租车辆查询',
        url : 'customer.html'
    }, {
        title : '订单排队系统',
        url : 'manager.html'
    }, {
        title : '车辆预订',
        url : 'manager.html'
    }, {
        title : '订单管理',
        url : 'manager.html'
    }, {
        title : '出车登记及合同打印',
        url : 'manager.html'
    }, {
        title : '出车车辆变更管理',
        url : 'manager.html'
    }, {
        title : '车辆续租管理',
        url : 'manager.html'
    }, {
        title : '车辆结算及打印',
        url : 'manager.html'
    }, {
        title : '车辆违章管理',
        url : 'manager.html'
    }, {
        title : '历史结算管理',
        url : 'manager.html'
    } ]
}, {
    title : '车辆管理',
    iconCls : 'icon-info',
    submenus : [ {
        title : '车型管理',
        url : 'map.html'
    }, {
        title : '车辆基本信息维护',
        url : 'map.html'
    }, {
        title : '车船税支出管理',
        url : 'manager.html'
    }, {
        title : '保险费支出管理',
        url : 'map.html'
    }, {
        title : '维修保养费支出管理',
        url : 'manager.html'
    }, {
        title : '加油费支出管理',
        url : 'map.html'
    }, {
        title : '租赁费用标准设置',
        url : 'manager.html'
    }, {
        title : '司机信息管理',
        url : 'manager.html'
    }, {
        title : '车辆调拨管理',
        url : 'map.html'
    }, {
        title : '内部用车管理',
        url : 'manager.html'
    } ]
}, {
    title : '车辆监控',
    iconCls : 'icon-info',
    submenus : [ {
        title : '车辆监控定位',
        url : 'manager.html'
    }, {
        title : '车辆跟踪',
        url : 'map.html'
    }, {
        title : '轨迹回放',
        url : 'manager.html'
    }, {
        title : '车辆信息锁定',
        url : 'manager.html'
    }, {
        title : '车辆行驶距离统计',
        url : 'map.html'
    }, {
        title : '行驶时间统计',
        url : 'manager.html'
    }, {
        title : '停车查询',
        url : 'manager.html'
    }, {
        title : '车辆报警',
        url : 'map.html'
    }, {
        title : '车辆越界',
        url : 'manager.html'
    }, {
        title : '车辆超速报警',
        url : 'manager.html'
    } ]
}, {
    title : '客户管理',
    iconCls : 'icon-info',
    submenus : [ {
        title : '会员基本信息管理',
        url : 'map.html'
    }, {
        title : '会员消费及积分管理',
        url : 'manager.html'
    }, {
        title : '会员违章信息管理',
        url : 'map.html'
    }, {
        title : '事故信息管理',
        url : 'manager.html'
    }, {
        title : '会员当前订单',
        url : 'map.html'
    }, {
        title : '会员在租车辆',
        url : 'manager.html'
    }, {
        title : '会员历史租赁记录管理',
        url : 'map.html'
    }, {
        title : '客户咨询记录',
        url : 'manager.html'
    }, {
        title : '客户销售管理',
        url : 'map.html'
    }, {
        title : '优惠电子码管理',
        url : 'manager.html'
    }, {
        title : '短信发送接收管理',
        url : 'map.html'
    } ]
}, {
    title : '财务管理',
    iconCls : 'icon-info',
    submenus : [ {
        title : '车辆统计',
        url : 'map.html'
    }, {
        title : '每日经营报表',
        url : 'manager.html'
    }, {
        title : '月度经营报表',
        url : 'manager.html'
    }, {
        title : '车辆收入统计',
        url : 'manager.html'
    }, {
        title : '车辆收益统计',
        url : 'manager.html'
    }, {
        title : '会员充值统计',
        url : 'manager.html'
    }, {
        title : '公司管理成本维护',
        url : 'manager.html'
    } ]
}, {
    title : '维护管理',
    iconCls : 'icon-setting',
    submenus : [ {
        title : '系统用户管理',
        url : 'manager.html'
    }, {
        title : '分店组织管理',
        url : 'manager.html'
    }, {
        title : '角色权限管理',
        url : 'manager.html'
    }, {
        title : '系统日志管理',
        url : 'manager.html'
    }, {
        title : '数据备份管理',
        url : 'manager.html'
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
});
