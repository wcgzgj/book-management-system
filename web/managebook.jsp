<%@ page import="top.faroz.dao.BookDao" %>
<%@ page import="top.faroz.bean.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: faro_z
  Date: 2021/1/3
  Time: 下午10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理</title>
    <%@include file="include/taglib.jsp"%>

    <style>
        th {
            text-align: center;
        }
    </style>
</head>
<body>

<%@include file="include/adminheader.jsp"%>

<%
    BookDao bookDao = new BookDao();
    List<Book> list = bookDao.list();
    request.setAttribute("bookList",list);
%>

<%--private String name;--%>
<%--private float price;--%>
<%--private int num;--%>
<%--private String writer;--%>
<%--private int ISBN;--%>
<div style="margin: auto;text-align: center">
    <table class="table table-striped" style="text-align: center;margin: auto;width: 80%" >
        <tr>
            <th>编号</th>
            <th>ISBN</th>
            <th>名称</th>
            <th>作者</th>
            <th>价格</th>
            <th>数量</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        <c:forEach items="${bookList}" var="book" varStatus="st">
            <tr>
                <td>${st.count}</td>
                <td>${book.ISBN}</td>
                <td>${book.name}</td>
                <td>${book.writer}</td>
                <td>${book.price}</td>
                <td>${book.num}</td>
                <td>
                    <a href="/editbook.jsp?bookISBN=${book.ISBN}" class="glyphicon glyphicon-edit"></a>
                </td>
                <td>
                    <a href="/deletebook?bookISBN=${book.ISBN}" class="glyphicon glyphicon-trash"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div style="text-align: center">
    <a href="/addbook.jsp" class="btn btn-primary">添加图书</a>
</div>


<%@include file="include/footer.jsp"%>

</body>
</html>
