/**
 * show menu when click map
 */
var showMenu = function(e, geo, map) {
    var menu = $('#menu-panel');
    menu.empty();

    $('<li>').html('添加二维码').click(function() {

    }).appendTo(menu);
    $('<li>').html('添加wifi设备').click(function() {

    }).appendTo(menu);

    menu.css({
        top : e.pageY + 'px',
        left : e.pageX + 'px',
        display : 'block'
    });
};

$(function() {
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
        type: 'LineString', map.geomap('option', 'mode', 'drawPoint');
        showMenu(e, geo, map);
    };

    var positionEventHandler = function positionEventHandler(e, geo) {
        // console.log('longitude: ' + geo.coordinates[0] + '\t latitude: ' +
        // geo.coordinates[1]);
    };

    var map = new Map().createMap(container, zoom, maxLevelPixelWidth, maxLevelPixelHeight, mapFactor, tilePixelWidth,
            tilePixelHeight, mapLevels, mapFolderName, shapeFunction, positionEventHandler);
});