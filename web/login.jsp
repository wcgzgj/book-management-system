<%--
  Created by IntelliJ IDEA.
  User: faro_z
  Date: 2020/12/9
  Time: 上午8:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <%@include file="include/taglib.jsp"%>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script type="text/javascript" src="js/login.js"></script>
    <style>
        html,body{
            background-image:url("./img/bkground.jpg");
            background-size: 100% 100%;
            height: 100%;
        }
    </style>
    <script>
        $(document).ready(function () {
            $("#btn_login").click(function () {
                let identity = $("input[name=identity]:checked").val();
                $.ajax({//ajax传输json数据
                    type:"post",//请求方式
                    url:"/login",//请求传输的位置
                    data:{"userid":$("#userid").val(),
                        "password":$("#password").val(),
                        "identity":$("input[name=identity]:checked").val()},
                    error:function () {
                        alert("登录错误");
                    },
                    success:function (data) {
                        if (data==-1) {
                            alert("请输入完整的信息");
                        } else if (data==-2) {
                            alert("账号必须是数字");
                        } else if (data==-3) {
                            alert("查无此人！");
                        } else if(data==-4) {
                            alert("密码输入错误！");
                        } else {
                            // alert("登录成功！");成功就不提示了，直接进入
                            // <====重新定位到可交互界面
                            // 学生和老师的登录界面是不一样的
                            // 学生的操作和老师的操作有区别
                            // 两个的header要有区别
                            if (identity=="admin") {
                                window.location.href="./index.jsp";
                            } else {
                                window.location.href="./readerindex.jsp";
                            }
                        }
                    }
                });
            });
            // 点击注册按钮，跳转到注册界面
            $("#btn_register").click(function () {
               window.location.href="./register.jsp"
            });
        });
    </script>
<%--    登录界面的logo设计样式--%>
    <style>
        .logo{
            background: #EEE url(data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAIAAAAmkwkpAAAAHklEQVQImWNkYGBgYGD4//8/A5wF5SBYyAr+//8PAPOCFO0Q2zq7AAAAAElFTkSuQmCC) repeat;
            text-shadow: 5px -5px black, 4px -4px white;
            font-weight: bold;
            -webkit-text-fill-color: transparent;
            -webkit-background-clip: text;
            font-size: 30px;
        }
    </style>
</head>

<body>
<div id="login_frame">
    <p id="logo" class="logo">图书管理系统</p>
    <p><label class="label_input">账号</label><input type="text" id="userid" class="text_field" placeholder="请输入账号"/></p>
    <p><label class="label_input">密码</label><input type="password" id="password" class="text_field" placeholder="请输入密码"/></p>
    <div>
        <input type="radio" name="identity" value="reader" style="margin-left: 2px" checked="true">读者
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" name="identity" value="admin" style="margin-right: 2px">管理员
    </div>
    <br/>
    <div id="login_control">
        <input type="button" id="btn_login" class="btn btn-primary" value="登录"/>
        <input type="button" id="btn_register" class="btn btn-primary" value="注册"/>
    </div>
</div>


</body>
</html>
