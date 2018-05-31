<#assign ctx=Request.contextPath/>
<html>
<head>
    <meta charset="UTF-8">
    <title>测试动态表格</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/static/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx!}/static/themes/icon.css">
    <script type="text/javascript" src="${ctx}/js/easy/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/easy/jquery.easyui.min.js"></script>
</head>
<body>
<table id="dg"></table>

<!-- <div id="tb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"></a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true"></a>
</div> -->
<script>
    $(function () {
        $('#dg').datagrid({
            fitColumns: true,
            url: '${ctx}/easyui/getUser.json',
            columns: [[
                {field: 'studentNo', title: '学生ID'},
                {field: 'studentName', title: '姓名'},
            ]],

            //toolbar: '#tb',
            toolbar: [{
                iconCls: 'icon-edit',
                handler: function () {
                    alert('edit')
                }
            }, '-', {
                iconCls: 'icon-help',
                handler: function () {
                    alert('help')
                }
            }],
            pagination:true,
            rownumbers:true
        });



        var p = $('#dg').datagrid('getPager');
        $(p).pagination({
            /*
                页数文本框前显示的汉字 修改每页默认条数
                搜索pageList在jquery.easyui.min.js中修改，
                分页区下拉分页数量集合和默认每页分页条数
                striped属性 交替行换色
            */
            beforePageText: '第',
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from}-{to} 条记录,共 {total} 条记录'
        });
    });

</script>

</body>
</html>