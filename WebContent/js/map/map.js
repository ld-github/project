var g_geo = null;
var g_map = null;

var setGeo = function(geo) {
    g_geo = geo;
};

var getGeo = function() {
    return g_geo;
};

var IMG_URLS = {
    WIFI : '../images/wifi.png',
    CODE : '../images/code.png',
};

var TYPES = {
    WIFI : 1,
    CODE : 2,
};

/**
 * Show menu when click map
 */
var showMenu = function(e, geo) {
    $("#cover-panel").css({
        'display' : 'block',
    });
    var menu = $('#menu-panel');
    menu.empty();

    $('<li>').html('添加二维码').click(function() {
        $('#cover-panel').css('display', 'none');
        menu.css('display', 'none');
        $('#code-panel').dialog('open');
        setGeo(geo);
    }).appendTo(menu);

    $('<li>').html('添加wifi设备').click(function() {
        $('#cover-panel').css('display', 'none');
        menu.css('display', 'none');
        $('#wifi-panel').dialog('open');
        setGeo(geo);
    }).appendTo(menu);

    menu.css({
        top : e.pageY + 'px',
        left : e.pageX + 'px',
        'display' : 'block'
    });
};

var KEY_WHICH = {
    LEFT : 1,
    RIGHT : 3,
};

/**
 * Operate device when user mousedown the point
 */
var deviceOperate = function(e, obj, id, type, longitude, latitude) {
    if (e.which == KEY_WHICH.RIGHT) {
        console.log('show device info by id and type!');
        console.log('e.which:' + e.which + '\tid:' + id + '\ttype:' + type + "\tlongitude:" + longitude + "\tlatitude:" + latitude);
        e.stopPropagation();
    }
    if (e.which == KEY_WHICH.LEFT) {
        var isDraging = true;
        var isMove = false;
        var dragObj = $(obj);
        var iX = e.pageX - dragObj.offset().left;
        var iY = e.pageY - dragObj.offset().top;
        e.stopPropagation();
        e.preventDefault();

        document.onmousemove = function(e) {
            if (isDraging) {
                isMove = true;
                var oX = e.pageX - iX;
                var oY = e.pageY - iY;
                dragObj.offset({
                    top : oY,
                    left : oX
                });
                return false;
            }
        };
        document.onmouseup = function(e) {
            isDraging = false;
            if (isMove) {
                isMove = false;
                console.log('Point position changed');
            }
        };
    } else {
        if (e.stopPropagation()) {
            e.stopPropagation();
        }
    }
};

/**
 * Add point to map
 */
var appendPoint = function(id, type, title, longitude, latitude) {
    var src = type == TYPES.WIFI ? IMG_URLS.WIFI : IMG_URLS.CODE;
    var point = {
        type : 'Point',
        coordinates : [ longitude, latitude ],
    };

    var img = '<img src="' + src + '" class="device-item" onmousedown="deviceOperate(event, this, ' + id + ', ' + type + ', ' + longitude + ', ' + latitude + ')">';
    g_map.geomap("append", point, img);
};

/**
 * Init wifi and code edit panel
 */
var initEditPanel = function() {
    $('#wifi-panel').dialog({
        title : '添加wifi设备',
        iconCls : 'icon-save',
        width : 400,
        height : 500,
        closed : true,
        cache : false,
        modal : true,
        buttons : [ {
            text : '保存',
            iconCls : 'icon-ok',
            handler : function() {
                var geo = getGeo();
                if (null == geo) {
                    return;
                }
                var longitude = geo.coordinates[0];
                var latitude = geo.coordinates[1];
                console.log('longitude: ' + longitude + '\t latitude: ' + latitude);
                $('#wifi-panel').dialog('close');
                // Here, as a test of the id is saved to the database id
                var id = Math.floor(Math.random() * 1000 + 1);
                appendPoint(id, TYPES.WIFI, 'title', longitude, latitude);
            }
        }, {
            text : '取消',
            iconCls : 'icon-cancel',
            handler : function() {
                $('#wifi-panel').dialog('close');
            }
        } ]
    });

    $('#code-panel').dialog({
        title : '添加二维码',
        iconCls : 'icon-save',
        width : 400,
        height : 500,
        closed : true,
        cache : false,
        modal : true,
        buttons : [ {
            text : '保存',
            iconCls : 'icon-ok',
            handler : function() {
                alert('ok');
            }
        }, {
            text : '取消',
            iconCls : 'icon-cancel',
            handler : function() {
                $('#code-panel').dialog('close');
            }
        } ]
    });
};

$(function() {
    /**
     * Shielding right-click menu
     */
    document.oncontextmenu = function() {
        return false;
    };

    /**
     * Init wifi and code edit panel
     */
    initEditPanel();

    /**
     * Create map begin
     */
    var config = new Config();
    var container = '#main-panel';
    var zoom = config.ZOOM_DEFAULT;
    var maxLevelPixelWidth = config.MAX_LEVEL_PIXEL_WIDTH_DEFAULT;
    var maxLevelPixelHeight = config.MAX_LEVEL_PIXEL_HEIGHT_DEFAULT;
    var mapFactor = config.MAP_FACTOR_DEFAULT;
    var tilePixelWidth = config.TILE_PIXEL_WIDTH_DEFAULT;
    var tilePixelHeight = config.TILE_PIXEL_HEIGHT_DEFAULT;
    var mapLevels = config.MAP_LEVELS_DEFAULT;
    var mapFolderName = config.MAP_FOLDER_NAME_DEFAULT;

    var shapeFunction = function(e, geo) {
        type: 'LineString', g_map.geomap('option', 'mode', 'drawPoint');
        showMenu(e, geo);
    };

    var positionEventHandler = function positionEventHandler(e, geo) {
        // console.log('longitude: ' + geo.coordinates[0] + '\t latitude: ' +
        // geo.coordinates[1]);
    };

    g_map = new Map().createMap(container, zoom, maxLevelPixelWidth, maxLevelPixelHeight, mapFactor, tilePixelWidth,
            tilePixelHeight, mapLevels, mapFolderName, shapeFunction, positionEventHandler);
    /**
     * Create map end
     */

    $('#cover-panel').click(function() {
        $(this).css('display', 'none');
        $('#menu-panel').css('display', 'none');
    });

});