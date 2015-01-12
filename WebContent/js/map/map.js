var g_geo = null;
var g_map = null;

var setGeo = function(geo) {
    g_geo = geo;
};

var getGeo = function() {
    return g_geo;
};

/**
 * show menu when click map
 */
var showMenu = function(e, geo) {
    $("#cover-panel").css({
        'display' : 'block',
    });
    var menu = $('#menu-panel');
    menu.empty();

    $('<li>').html('添加二维码').click(function() {
        $('#cover-panel').css('display', 'block');
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

/**
 * init wifi and code edit panel
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
     * init wifi and code edit panel
     */
    initEditPanel();

    /**
     * create map begin
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
     * create map end
     */

    $('#cover-panel').click(function() {
        $(this).css('display', 'none');
        $('#menu-panel').css('display', 'none');
    });

});