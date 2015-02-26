$(function() {
    $('#main-panel').datagrid({
        singleSelect : true,
        pagination : true,
        columns : [ [ {
            field : 'id',
            title : '编号',
            width : 100
        }, {
            field : 'username',
            title : '操作人',
            width : 200
        }, {
            field : 'className',
            title : '类名',
            width : 200,
        }, {
            field : 'method',
            title : '方法名',
            width : 200,
        }, {
            field : 'level',
            title : '级别',
            width : 200,
        }, {
            field : 'createTime',
            title : '发生时间',
            width : 200,
        } ] ]
    });
});