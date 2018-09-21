<#assign ctx=Request.contextPath/>

<html>
<head>
    <title>登录页</title>
</head>

<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>

<body>

<form id="loginForm">
    用户名：<input name="username" value="admin"/>
    密码：<input name="password" value="123"/>
    <input type="button" id="btnLogin" value="登录"/>
</form>


<script>
    $(function () {
        $("#btnLogin").click(function (e) {
            e.preventDefault();
            var username = $("input[name='username']").val();
            var password = $("input[name='password']").val();
            var loginParam = {
                "username": username,
                "password": password
            };

            $.post("${ctx}/ajaxLogin", loginParam, function (data) {
                if (data && data.status === "SUCCESS") {
                    window.location.href='${ctx}/index';
                } else {
                    alert(data.msg || "登录失败！");
                    window.location.href='${ctx}/login';
                }
            });
        });
    });
</script>
</body>
</html>