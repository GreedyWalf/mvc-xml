<#assign ctx=Request.contextPath/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>首页</title>

<#-- 加载layui样式-->
    <link rel="stylesheet" href="${ctx}/static/js/mudules/layui/css/layui.css" media="all">
</head>
<body>

<form class="layui-form" action="">

    <div class="layui-form-item">
        <label class="layui-form-label">选择框</label>
        <div class="layui-input-block">
            <select name="city" lay-verify="required">
                <option value=""></option>
                <option value="0">北京</option>
                <option value="1">上海</option>
                <option value="2">广州</option>
                <option value="3">深圳</option>
                <option value="4">杭州</option>
            </select>
        </div>
    </div>
</form>

<body>
<button id="parentIframe">小小提示层</button>
<input id="shuzhi"/>


<div class="col-xs-4 text-left" style="padding-left: 50px;">
    <button type="button" class="btn btn-success" onclick="addCategory();">添加</button>
    <input id="handle_status" value="" hidden="hidden">
</div>


</body>

<#-- 引入layui全局js -->
<script src="${ctx}/static/js/mudules/layui/layui.all.js"></script>

<script>

    function addCategory() {
        layer.open({
            title: '添加分类',
            type: 2,
            area: ['700px', '530px'],
            fix: false, //不固定
            maxmin: true,
            content: '${ctx}/test',
            success: function (layero, index) {
            },
            end: function () {
                var handle_status = $("#handle_status").val();
                if (handle_status == '1') {
                    layer.msg('添加成功！', {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        history.go(0);
                    });
                } else if (handle_status == '2') {
                    layer.msg('添加失败！', {
                        icon: 2,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        history.go(0);
                    });
                }
            }
        });
    }


    /* 直接使用use方式 */
    layui.use(['form', 'layer', 'jquery'], function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });

        form.render();


        var layer = layui.layer;
        var $ = layui.$;

        $("#parentIframe").click(function () {
            layer.open({
                type: 2,
                area: [700, 500],
                content: '${ctx}/test',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    alert($("#name").val());
                    alert(iframeIndex);
                },
                btn2: function (index, layero) {
                    console.log(layero);
                    alert(index);
                }, cancel: function () {
                    alert("取消了");
                }
            });
        });
    });

    <#--/* 定义加载自定义模块方式（自定义js文件） */-->
    <#--layui.config({-->
    <#--base:'${ctx}/assets/js/user/'-->
    <#--}).use('index');-->
</script>

</body>
</html>