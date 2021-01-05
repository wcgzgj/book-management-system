<%@ page import="top.faroz.dao.ReaderDao" %>
<%@ page import="top.faroz.bean.Reader" %>
<%@ page import="java.util.List" %>
<%@ page import="top.faroz.dao.ReaderBookDao" %><%--
  Created by IntelliJ IDEA.
  User: faro_z
  Date: 2021/1/3
  Time: 下午10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>读者管理</title>
    <%@include file="include/taglib.jsp"%>
</head>
<body>
<%@include file="include/adminheader.jsp"%>

<%
    ReaderDao readerDao = new ReaderDao();
    List<Reader> list = readerDao.list();
    //返回所有读者的信息
    request.setAttribute("readerList",list);
%>

<div style="margin: auto;text-align: center" >
    <table  class="table table-striped" style="text-align: center;margin: auto;width: 80%">
        <tr>
            <td>序号</td>
            <td>读者id</td>
            <td>读者姓名</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${readerList}" var="reader" varStatus="st">
            <tr>
                <td>${st.count}</td>
                <td>${reader.id}</td>
                <td>${reader.name}</td>
                <td>
<%--                    管理员对读者只有一个删除权限--%>
                    <a href="/deleteReader?readerID=${reader.id}" class="glyphicon glyphicon-trash"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


<%@include file="include/footer.jsp"%>
</body>
</html>
