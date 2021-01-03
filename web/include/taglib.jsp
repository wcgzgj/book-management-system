<%--
  Created by IntelliJ IDEA.
  User: faro_z
  Date: 2020/12/9
  Time: 上午8:15
  所有需要使用的第三方插件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--使用jstl标签--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--启用el表达式--%>
<%@ page isELIgnored="false" %>
<%--启用jquery--%>
<script src="./jquery/jQuery.js" type="text/javascript"></script>

<%--新 Bootstrap 核心 CSS 文件--%>
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<%--jQuery文件。务必在bootstrap.min.js 之前引入--%>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

<%--最新的 Bootstrap 核心 JavaScript 文件--%>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html>
<head>

    <title>包含文件</title>
</head>
<body>
    <%
        // request是jsp中的隐式成员
        String path = request.getContextPath();
        int port = request.getServerPort();
        String basePath  = null;
        if(port==80){
            basePath = request.getScheme()+"://"+request.getServerName()+path;
        }else{
            basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
        }
        //在request中设置一个变量为basePath，后面在jsp中，就可以通过el表达式获取 ${basePath}
        request.setAttribute("basePath", basePath);
    %>
</body>
</html>
