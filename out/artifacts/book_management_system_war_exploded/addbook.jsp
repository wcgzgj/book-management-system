<%--
  Created by IntelliJ IDEA.
  User: faro_z
  Date: 2021/1/6
  Time: 上午2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加图书</title>

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
                let ISBN = $("#ISBN").val();
                let price = $("#price").val();
                let writer = $("#writer").val();
                let num = $("#num").val();
                let name = $("#name").val();
                $.ajax({//ajax传输json数据
                    type: "post",//请求方式
                    url: "/addbook",//请求传输的位置
                    data: {
                        "price": price,
                        "writer": writer,
                        "num": num,
                        "name":name,
                        "ISBN":ISBN
                    },
                    error: function () {
                        alert("登录错误");
                    },
                    success: function (data) {
                        alert("添加成功！");
                        window.location.href="/managebook.jsp";

                    }
                });
            });
        });
    </script>
</head>
<body>

<div style="margin: auto;text-align: center">
    <p class="notice_head">添加图书</p>
    <table class="table table-striped" style="text-align: center;margin: auto;width: 80%" >
        <tr>
            <td>ISBN:</td>
            <td>
                <input type="text" id="ISBN">
            </td>
        </tr>
        <tr>
            <td>书名:</td>
            <td>
                <input type="text" id="name">
            </td>
        </tr>
        <tr>
            <td>价格:</td>
            <td>
                <input type="text" id="price">
            </td>
        </tr>
        <tr>
            <td>作者:</td>
            <td>
                <input type="text" id="writer">
            </td>
        </tr>
        <tr>
            <td>数量:</td>
            <td>
                <input type="text" id="num">
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
