<%@ page import="top.faroz.dao.BookDao" %>
<%@ page import="top.faroz.bean.Reader" %>
<%@ page import="top.faroz.service.BookService" %>
<%@ page import="top.faroz.bean.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: faro_z
  Date: 2021/1/3
  Time: 下午10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书归还</title>
    <%@include file="include/taglib.jsp"%>

    <style>
        th {
            text-align: center;
        }
        .notice_head {
            text-align: center;
            font-size: 20px;
            color: blue;
            font-family: "Academy Engraved LET";
        }
    </style>

    <script>
        $(document).ready(function () {
            $("button[id='return']").click(function () {
                alert("确认要归还该书吗？")
                var bookISBN=$(this).val();
                $.ajax({
                    type:"post",//请求方式
                    url:"/returnbook",//请求传输的位置
                    data:{
                        "bookISBN":bookISBN
                    },
                    error:function () {
                        alert("提交错误");
                    },
                    success:function (data) {
                        alert("归还成功！")
                        //自动刷新页面
                        location.reload(true);
                    }
                });
            });
        });
    </script>
</head>
<body>

<%@include file="include/readerheader.jsp"%>

<%
    Reader reader = (Reader) request.getSession().getAttribute("user");
    List<Book> bookList = new BookService().getBorrowBook(reader.getId());
    //获取的是读者所借阅的所有图书
    request.setAttribute("bookList",bookList);
%>

<div style="    margin: auto;text-align: center" >
    <p class="notice_head">图书归还</p>
    <table  class="table table-striped" style="text-align: center;margin: auto;width: 80%">
        <tr style="text-align: center">
            <th>序号</th>
            <th>书籍名称</th>
            <th>ISBN</th>
            <th>作者</th>
            <th>价格</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${bookList}" var="book" varStatus="st">
            <tr>
                <td>${st.count}</td>
                <td>${book.name}</td>
                <td>${book.ISBN}</td>
                <td>${book.writer}</td>
                <td>${book.price}</td>
                <td>借阅中</td>
                <td >
                    <button class="btn btn-primary" value="${book.ISBN}" id="return">归还</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="include/footer.jsp"%>

</body>
</html>
