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

    <script>
        $(document).ready(function () {
            $("#confirm").click(function () {
                let price = $("#price").val();
                let writer = $("#writer").val();
                let num = $("#num").val();
                let name = $("#name").val();
                $.ajax({//ajax传输json数据
                    type:"post",//请求方式
                    url:"/editbook",//请求传输的位置
                    data:{"userid":$("#userid").val(),
                        "password":$("#password").val(),
                        "identity":$("input[name=identity]:checked").val()},
                    error:function () {
                        alert("登录错误");
                    },
                    success:function (data) {
                        if (data == -1) {
                            alert("请输入完整的信息");
                        } else if (data == -2) {
                            alert("账号必须是数字");
                        } else if (data == -3) {
                            alert("查无此人！");
                        } else if (data == -4) {
                            alert("密码输入错误！");
                        } else {
                            // alert("登录成功！");成功就不提示了，直接进入
                            // <====重新定位到可交互界面
                            // 学生和老师的登录界面是不一样的
                            // 学生的操作和老师的操作有区别
                            // 两个的header要有区别
                            if (identity == "admin") {
                                window.location.href = "./index.jsp";
                            } else {
                                window.location.href = "./readerindex.jsp";
                            }
                        }
                    }
            });
        });
    </script>
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
                <input type="text" value="${book.name}" id="name">
            </td>
        </tr>
        <tr>
            <td>价格:</td>
            <td>
                <input type="text" value="${book.price}" id="price">
            </td>
        </tr>
        <tr>
            <td>作者:</td>
            <td>
                <input type="text" value="${book.writer}" id="writer">
            </td>
        </tr>
        <tr>
            <td>数量:</td>
            <td>
                <input type="text" value="${book.num}" id="num">
            </td>
        </tr>
    </table>
    <div style="text-align: center">
        <table style="margin: auto">
            <tr>
                <td>
                    <button class="btn btn-primary" id="confirm">确认</button>
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
