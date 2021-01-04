<%--
  Created by IntelliJ IDEA.
  User: faro_z
  Date: 2020/12/13
  Time: 下午3:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--这里不包含taglib，因为重复包含，会导致500的错误--%>
<html>
<head>
    <title>首栏</title>
    <script>
        $(document).ready(function () {
            $("#resign_link").click(function () {
                ${sessionScope.clear()}
                window.location.href="./login.jsp";
            });
        });
    </script>
    <style>
        #resign_link:hover {
            cursor: pointer;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">图书管理</a>
        </div>

        <div class="collapse navbar-collapse header" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="./readerindex.jsp">首页 <span class="sr-only">(current)</span></a></li>
                <li><a href="./borrowbook.jsp">图书借阅</a></li>
                <li><a href="./returnbook.jsp">图书归还</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎 ${sessionScope.username}</a></li>
                <li><a id="resign_link">注销</a></li>
            </ul>
        </div>
    </div>
</nav>

</body>
</html>
