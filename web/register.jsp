<%--
  Created by IntelliJ IDEA.
  User: faro_z
  Date: 2020/12/10
  Time: 下午5:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <%@include file="include/taglib.jsp"%>®
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script type="text/javascript" src="js/login.js"></script>
    <style>
        html,body {
            background-color: antiquewhite;
            background-image:url("./img/bkground.jpg");
            background-size: 100% 100%;
            height: 100%;
        }
    </style>
    <script>
        $(document).ready(function () {
            $("#btn_register").click(function () {
                $.ajax({
                    type:"post",//请求方式
                    url:"/register",//请求传输的位置
                    data:{"username":$("#username").val(),
                        "userid":$("#userid").val(),
                        "password":$("#password").val(),
                        "identity":$("input[name=identity]:checked").val()},
                    error:function () {
                        alert("注册失败");
                    },
                    success:function (data) {
                        if (data==0) {
                            alert("请输入完整的信息！");
                        } else if (data==-1) {
                            alert("账号只能是数字！")
                        } else if (data==-2) {
                            alert("当前用户已经存在！")
                        } else {
                            alert($("input[name=identity]:checked").val()+"注册成功！");
                            window.location.href="./login.jsp";
                        }
                    }
                });
            });
            //返回登录界面的按钮
            $("#btn_login").click(function () {
                window.location.href="./login.jsp";
            });
        });

    </script>
</head>
<body>
<div id="login_frame">

    <p><label class="label_input" >账号</label><input type="text" id="userid" class="text_field" placeholder="账号只能为数字"/></p>
    <p><label class="label_input">密码</label><input type="text" id="password" class="text_field" placeholder="请输入密码"/></p>
    <p><label class="label_input">真实姓名</label><input type="text" id="username" class="text_field" placeholder="请输入您的真实姓名"/></p>
    <div>
        <input type="radio" name="identity" value="reader" style="margin-left: 2px" checked="true">读者
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" name="identity" value="admin" style="margin-right: 2px">管理员
    </div>
    <br/>
    <div id="login_control">
        <input type="button" id="btn_register" class="btn btn-primary" value="注册"/>
        <input type="button" id="btn_login" class="btn btn-primary" value="返回登录界面"/>
    </div>
</div>
</body>
</html>
