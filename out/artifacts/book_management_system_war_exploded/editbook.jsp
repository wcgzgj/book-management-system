<%@ page import="top.faroz.dao.BookDao" %>
<%@ page import="top.faroz.bean.Book" %>
<%@ page import="javax.xml.transform.Source" %><%--
  Created by IntelliJ IDEA.
  User: faro_z
  Date: 2021/1/4
  Time: 下午4:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑图书</title>
    <%@include file="include/taglib.jsp"%>

    <style>
        .notice_head {
            text-align: center;
            font-size: 20px;
            color: blue;
            font-family: "Academy Engraved LET";
            margin-top: 40px;
        }
    </style>
</head>
<body>

<%
    String bookISBN = request.getParameter("bookISBN");
    System.out.println(bookISBN);
    BookDao bookDao = new BookDao();
    Book book = bookDao.get(Integer.parseInt(bookISBN));
    request.setAttribute("book",book);
%>

<div style="margin: auto;text-align: center">
    <p class="notice_head">修改书本信息</p>
    <table class="table table-striped" style="text-align: center;margin: auto;width: 80%" >
        <tr>
            <td>ISBN:</td>
            <td>${book.ISBN}</td>
        </tr>
        <tr>
            <td>书名:</td>
            <td>
                <input type="text" value="${book.name}">
            </td>
        </tr>
        <tr>
            <td>价格:</td>
            <td>
                <input type="text" value="${book.price}">
            </td>
        </tr>
        <tr>
            <td>作者:</td>
            <td>
                <input type="text" value="${book.writer}">
            </td>
        </tr>
        <tr>
            <td>数量:</td>
            <td>
                <input type="text" value="${book.num}">
            </td>
        </tr>
    </table>
    <div style="text-align: center">
        <table style="margin: auto">
            <tr>
                <td>
                    <a class="btn btn-primary" style="margin: 30px">确认</a>
                </td>
                <td>
                    <a class="btn btn-primary" href="managebook.jsp">取消</a>
                </td>
            </tr>
        </table>
    </div>
</div>

<%@include file="include/footer.jsp"%>
</body>
</html>
