$.holdReady(true);
$.getScript('../plugins/jquery-geo-map/jquery.geo-1.0.0-b2.min.js', function() {
    $.holdReady(false);
});

var Map = function() {
    this.createMap = function(container, zoom, maxLevelPixelWidth, maxLevelPixelHeight, mapFactor, tilePixelWidth,
            tilePixelHeight, mapLevels, mapFolderName, shapeFunction, positionEventHandler) {
        function zeroPadding3(str) {
            str = str + '';
            return [ '000', '00', '0', '' ][str.length] + str;
        }
        function mapService(view) {
            if (view.tile.column >= 0 && view.tile.row >= 0) {
                var l = view.zoom + 1;
                var r = zeroPadding3(view.tile.row);
                var c = zeroPadding3(view.tile.column);
                return '../images/map/' + mapFolderName + '/L' + l + '/R' + r + 'C' + c + '.jpg';
            } else {
                return '';
            }
        }

        $.geo.proj = null;
        var map = $(container).geomap({
            zoom : zoom,
            zoomMin : 0,
            center : [ maxLevelPixelWidth * mapFactor / 2, maxLevelPixelHeight * mapFactor / 2 ],
            axisLayout : 'image',
            move : positionEventHandler,
            mode : 'drawPoint',
            shape : shapeFunction,
            services : [ {
                type : 'tiled',
                src : mapService,
                attr : '<div class=\"copy-right\">@copyRight 2015 LD </div>'
            } ],
            tilingScheme : {
                tileWidth : tilePixelWidth,
                tileHeight : tilePixelHeight,
                levels : mapLevels,
                basePixelSize : mapFactor * (1 << (mapLevels - 1)),
                origin : [ 0, 0 ]
            },
        });

        // change geo style
        var shapeOption = {
            width : 0,
            height : 0,
            borderRadius : 0
        };

        map.geomap('option', 'shapeStyle', shapeOption);
        return map;
    };
};
