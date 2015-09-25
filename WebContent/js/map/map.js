var g_map = null;
var g_geo = null;

/**
 * Set g_geo
 * 
 * @param geo
 */
function setGeo(geo) {
    g_geo = geo;
}

/**
 * Get g_geo
 * 
 * @returns {___anonymous_g_geo}
 */
function getGeo() {
    return g_geo;
}

var IMG_URLS = {
    WIFI : '../images/wifi.png',
    CODE : '../images/code.png',
};

var DEVICE_TYPES = {
    WIFI : 1,
    CODE : 2,
};

/**
 * Show menu when click map
 * 
 * @param e
 * @param geo
 */
function showMenu(e, geo) {
    $("#cover-panel").show();
    var menu = $('#menu-panel');
    menu.empty();

    $('<li>').html('添加二维码信息').click(function() {
        $('#cover-panel').hide();
        menu.hide();
        initEditorPanel(EDITOR_STATUS.NEW, DEVICE_TYPES.CODE, null);
        setGeo(geo);
    }).appendTo(menu);

    $('<li>').html('添加wifi设备信息').click(function() {
        $('#cover-panel').hide();
        menu.hide();
        initEditorPanel(EDITOR_STATUS.NEW, DEVICE_TYPES.WIFI, null);
        setGeo(geo);
    }).appendTo(menu);

    menu.css({
        top : e.pageY + 'px',
        left : e.pageX + 'px',
        display : 'block'
    });
}

var KEY_WHICH = {
    LEFT : 1,
    RIGHT : 3,
};

/**
 * Show device info
 * 
 * @param id
 * @param type
 */
function showDeviceInfo(id, type) {
    console.log('id:' + id + '\ttype:' + type);
    if (type == DEVICE_TYPES.WIFI) {
        initEditorPanel(EDITOR_STATUS.VIEW, DEVICE_TYPES.WIFI, {
            id : id
        });
    }
    if (type == DEVICE_TYPES.CODE) {
        initEditorPanel(EDITOR_STATUS.VIEW, DEVICE_TYPES.CODE, {
            id : id
        });
    }
}

/**
 * Operate device when user mousedown the point
 * 
 * @param e
 * @param obj
 * @param id
 * @param type
 * @param longitude
 * @param latitude
 */
function operateDevice(e, obj, id, type, longitude, latitude) {
    if (e.which == KEY_WHICH.RIGHT) {
        console.log('show device info by id and type!');
        showDeviceInfo(id, type);
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
}

/**
 * Add point to map
 * 
 * @param id
 * @param type
 * @param title
 * @param longitude
 * @param latitude
 */
function appendPoint(id, type, title, longitude, latitude) {
    var src = type == DEVICE_TYPES.WIFI ? IMG_URLS.WIFI : IMG_URLS.CODE;
    var point = {
        type : 'Point',
        coordinates : [ longitude, latitude ],
    };

    var img = '<img src="' + src + '" title="' + title
            + '" class="device-item" onmousedown="operateDevice(event, this, ' + id + ', ' + type + ', ' + longitude
            + ', ' + latitude + ')">';
    g_map.geomap("append", point, img);
}

/**
 * Editor status
 */
var EDITOR_STATUS = {
    NEW : 1,
    EDIT : 2,
    VIEW : 3
};

/**
 * Delete Devices by id and type
 * 
 * @param :{id :id,type : type }
 */
function deleteDevice(params) {
    var id = params.id;
    var type = params.type;

    console.log('delete id:' + id);
    new Message('删除成功!', '温馨提示').show(true);
    if (type == DEVICE_TYPES.WIFI) {
        $(EDITOR_CONTAINER.WIFI).dialog('close');
    }
    if (type == DEVICE_TYPES.CODE) {
        $(EDITOR_CONTAINER.CODE).dialog('close');
    }
}

/**
 * Init wifi editor
 * 
 * @param editorStatus
 * @param wifi
 */
var WifiEditor = function(container) {
    this.title = '';
    this.iconCls = '';
    this.buttons = [];

    this.init = function(editorStatus, wifi) {
        if (editorStatus == EDITOR_STATUS.NEW) {
            this.title = '添加wifi设备信息';
            this.iconCls = "icon-add";
            var saveBtn = {
                text : '保存',
                iconCls : 'icon-save',
                handler : function() {
                    var geo = getGeo();
                    if (null == geo) {
                        return;
                    }
                    var longitude = geo.coordinates[0];
                    var latitude = geo.coordinates[1];
                    console.log('longitude: ' + longitude + '\t latitude: ' + latitude);
                    $(container).dialog('close');
                    // Here, as a test of the id is saved to the database id
                    var id = Math.floor(Math.random() * 1000 + 1);
                    appendPoint(id, DEVICE_TYPES.WIFI, 'title', longitude, latitude);
                }
            };
            this.buttons.push(saveBtn);
        }
        if (editorStatus == EDITOR_STATUS.EDIT) {
            this.title = '编辑wifi设备信息';
            this.iconCls = 'icon-edit';
            var updateBtn = {
                text : '保存',
                iconCls : 'icon-save',
                handler : function() {
                    $(container).dialog('close');
                }
            };
            var deleteBtn = {
                text : '删除',
                iconCls : 'icon-remove',
                handler : function() {
                    new Message('确定删除当前wifi设备信息?', '温馨提示').confirm(deleteDevice, {
                        id : wifi.id,
                        type : DEVICE_TYPES.WIFI
                    });
                }
            };
            this.buttons.push(updateBtn);
            this.buttons.push(deleteBtn);
        }
        if (editorStatus == EDITOR_STATUS.VIEW) {
            this.title = '查看wifi设备信息';
            this.iconCls = 'icon-print';
            var editBtn = {
                text : '编辑',
                iconCls : 'icon-edit',
                handler : function() {
                    initEditorPanel(EDITOR_STATUS.EDIT, DEVICE_TYPES.WIFI, wifi);
                }
            };
            this.buttons.push(editBtn);
        }

        var cancelBtn = {
            text : '取消',
            iconCls : 'icon-cancel',
            handler : function() {
                $(container).dialog('close');
            }
        };
        this.buttons.push(cancelBtn);

        $(container).dialog({
            title : this.title,
            iconCls : this.iconCls,
            width : 300,
            height : 400,
            cache : false,
            modal : true,
            buttons : this.buttons
        });
    };
}

/**
 * Init Code editor
 * 
 * @param editorStatus
 * @param code
 */
var CodeEditor = function(container) {
    this.title = '';
    this.iconCls = '';
    this.buttons = [];

    this.init = function(editorStatus, code) {
        if (editorStatus == EDITOR_STATUS.NEW) {
            this.title = '添加二维码信息';
            this.iconCls = "icon-add";
            var saveBtn = {
                text : '保存',
                iconCls : 'icon-save',
                handler : function() {
                    var geo = getGeo();
                    if (null == geo) {
                        return;
                    }
                    var longitude = geo.coordinates[0];
                    var latitude = geo.coordinates[1];
                    console.log('longitude: ' + longitude + '\t latitude: ' + latitude);
                    $(container).dialog('close');
                    // Here, as a test of the id is saved to the database id
                    var id = Math.floor(Math.random() * 1000 + 1);
                    appendPoint(id, DEVICE_TYPES.CODE, 'title', longitude, latitude);
                }
            };
            this.buttons.push(saveBtn);
        }
        if (editorStatus == EDITOR_STATUS.EDIT) {
            this.title = '编辑二维码信息';
            this.iconCls = 'icon-edit';
            var updateBtn = {
                text : '保存',
                iconCls : 'icon-save',
                handler : function() {
                    $(container).dialog('close');
                }
            };
            var deleteBtn = {
                text : '删除',
                iconCls : 'icon-remove',
                handler : function() {
                    new Message('确定删除当前二维码信息?', '温馨提示').confirm(deleteDevice, {
                        id : code.id,
                        type : DEVICE_TYPES.CODE
                    });
                }
            };
            this.buttons.push(updateBtn);
            this.buttons.push(deleteBtn);
        }
        if (editorStatus == EDITOR_STATUS.VIEW) {
            this.title = '查看二维码信息';
            this.iconCls = 'icon-print';
            var editBtn = {
                text : '编辑',
                iconCls : 'icon-edit',
                handler : function() {
                    initEditorPanel(EDITOR_STATUS.EDIT, DEVICE_TYPES.CODE, code);
                }
            };
            this.buttons.push(editBtn);
        }

        var cancelBtn = {
            text : '取消',
            iconCls : 'icon-cancel',
            handler : function() {
                $(container).dialog('close');
            }
        };
        this.buttons.push(cancelBtn);

        $(container).dialog({
            title : this.title,
            iconCls : this.iconCls,
            width : 300,
            height : 400,
            cache : false,
            modal : true,
            buttons : this.buttons
        });
    };
};

var EDITOR_CONTAINER = {
    WIFI : '#wifi-panel',
    CODE : '#code-panel',
};

/**
 * Init wifi or code editor panel
 * 
 * @param editorStatus
 * @param type
 * @param data
 */
function initEditorPanel(editorStatus, type, data) {
    if (type == DEVICE_TYPES.WIFI) {
        new WifiEditor(EDITOR_CONTAINER.WIFI).init(editorStatus, data);
    }
    if (type == DEVICE_TYPES.CODE) {
        new CodeEditor(EDITOR_CONTAINER.CODE).init(editorStatus, data);
    }
};

$(function() {
    /**
     * Shielding right-click menu
     */
    document.oncontextmenu = function() {
        return false;
    };

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

    function shapeFunction(e, geo) {
        type: 'LineString', g_map.geomap('option', 'mode', 'drawPoint');
        showMenu(e, geo);
    }

    function positionEventHandler(e, geo) {
        // console.log('longitude: ' + geo.coordinates[0] + '\t latitude: ' +
        // geo.coordinates[1]);
    }

    g_map = new Map().createMap(container, zoom, maxLevelPixelWidth, maxLevelPixelHeight, mapFactor, tilePixelWidth,
            tilePixelHeight, mapLevels, mapFolderName, shapeFunction, positionEventHandler);
    /**
     * Create map end
     */

    $('#cover-panel').click(function() {
        $(this).hide();
        $('#menu-panel').hide();
    });

});