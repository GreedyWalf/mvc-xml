<#assign ctx=Request.contextPath/>
<html lang="en">
<head>

<#-- 加载layui样式-->
    <link rel="stylesheet" href="${ctx}/static/js/mudules/layui/css/layui.css" media="all">
</head>
<body>
<div>
    <input id="name"/>
    <button id="closeIframe">关闭iframe</button>

</div>

<script src="${ctx}/static/js/mudules/layui/layui.js"></script>

<script>
    /* 直接使用use方式 */
    layui.use(['form', 'layer','jquery'], function () {
        var layer = layui.layer;
        var $ = layui.$;
        var parent$ = window.parent.layui.jquery;


        //关闭iframe
        $('#closeIframe').click(function () {
            var val = $('#name').val();
            if (val === '') {
                parent.layer.msg('请填写标记');
                return;
            }
            parent.layer.msg('您将标记 [ ' + val + ' ] 成功传送给了父窗口');
            parent$('#parentIframe').text('我被改变了');
            parent.layer.close(parent.layer.getFrameIndex(window.name));
        });
    });
</script>
</body>
</html>