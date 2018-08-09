<#assign ctx=Request.contextPath/>
<html>
<head>
    <title>首页</title>
</head>
<body>
this is index.ftl <br>
<ul>
    <li><a href="${ctx}/userList">用户管理</a></li>
</ul>
<a href="${ctx}/logout">登出</a>
</body>
</html>