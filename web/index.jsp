<%--
  Created by IntelliJ IDEA.
  User: faro_z
  Date: 2021/1/3
  Time: 下午8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>欢迎使用图书管理系统</title>
    <%@include file="include/taglib.jsp"%>
    <style>
      * {
        box-sizing: border-box;
      }

      /* body 样式 */
      body {
        font-family: Arial;
        margin: 0;
      }

      /* 列容器 */
      .row {
        display: -ms-flexbox; /* IE10 */
        display: flex;
        -ms-flex-wrap: wrap; /* IE10 */
        flex-wrap: wrap;
      }

      /* 创建两个列 */
      /* 边栏 */
      .side {
        -ms-flex: 30%; /* IE10 */
        flex: 30%;
        background-color: #f1f1f1;
        padding: 20px;
      }

      /* 主要的内容区域 */
      .main {
        -ms-flex: 70%; /* IE10 */
        flex: 70%;
        background-color: white;
        padding: 20px;
      }

      /* 测试图片 */
      .text_area {
        background-color: #aaa;
        width: 100%;
        padding: 20px;
      }

      /* 响应式布局 - 在屏幕设备宽度尺寸小于 700px 时, 让两栏上下堆叠显示 */
      @media screen and (max-width: 700px) {
        .row {
          flex-direction: column;
        }
      }

      /* 响应式布局 - 在屏幕设备宽度尺寸小于 400px 时, 让导航栏目上下堆叠显示 */
      @media screen and (max-width: 400px) {
        .navbar a {
          float: none;
          width: 100%;
        }
      }

      .welcome_head {
        color: blue;
        font: 0.875em/1.5em"微软雅黑", "PTSans", "Arial", sans-serif;
        font-size: 30px;
        text-align: center;
        /*background-color: #aaaaaa;*/
        width: 100%;
      }

      #links p:hover {
        cursor: pointer;
        text-decoration: none;
        background-color: #3BD9FF;
        color: #f1f1f1;
        font: 20px/1 Tahoma,Helvetica,Arial,"\5b8b\4f53",sans-serif;
        line-height: 30px;

        margin-top: 15px;
      }
    </style>
  </head>
  <body>
  <%@include file="include/adminheader.jsp"%>

  <h1 class="welcome_head">欢迎使用图书管理系统</h1>
  <div class="row">
    <div class="side">
      <h3>更多内容</h3>
      <div id="links">
        <p><a target="_blank" href="https://github.com/wcgzgj/FAROInterpreter.git">MyLisp解释器</a></p>
        <br/>
        <p><a target="_blank" href="https://github.com/wcgzgj/gobang-ver4.0-JavaSwing.git">人工智能五子棋</a></p>
        <br/>
        <p><a target="_blank" href="https://github.com/wcgzgj/school-qq.git">校园qq</a></p>
        <br/>
        <p><a target="_blank" href="https://github.com/wcgzgj/java-net-shell.git">远程控制程序</a></p>
        <br/>
      </div>
    </div>
    <div class="main">
      <h2>项目介绍</h2>
      <h5>校园管理系统</h5>
      <div class="text_area">
        <pre>
本系虽然是简单的CRUD，但也是一个从无到有的过程
系统包含管理员和读者
读者端包含的主要功能有：
  * 借阅：
      可以借阅库存中还有的书籍
  * 查找：
      可以通过关键词，查找对应的书籍
  * 通知：
      可以查看自己的图书借阅情况

管理员包含的主要功能如下：
  * 处理借阅：
      处理读者的借阅申请
  * 管理读者：
      管理读者的信息
  * 通知：
      显示上级下达的重要通知

其他部分：
  系统包含了完整的登录注册判断
        </pre>
      </div>
      <br>
      <h2>联系我们</h2>
      <h5>FAROZ's Github</h5>
      <div class="text_area">
        <pre>
想获得更多作者的作品？欢迎持续关注作者的Github地址：
https://github.com/wcgzgj/school-management-system.git
目前已经完成的项目有：
    * MyLisp解释器：
        <a target="_blank" href="https://github.com/wcgzgj/FAROInterpreter.git">https://github.com/wcgzgj/FAROInterpreter.git</a>
    * 人工智能五子棋：
        <a target="_blank" href="https://github.com/wcgzgj/gobang-ver4.0-JavaSwing.git">https://github.com/wcgzgj/gobang-ver4.0-JavaSwing.git</a>
    * 校园qq：
        <a target="_blank" href="https://github.com/wcgzgj/school-qq.git">https://github.com/wcgzgj/school-qq.git</a>
    * 远程控制程序：
        <a target="_blank" href="https://github.com/wcgzgj/java-net-shell.git">https://github.com/wcgzgj/java-net-shell.git</a>
        </pre>
      </div>
    </div>
  </div>


  <%@include file="include/footer.jsp"%>

  </body>
</html>
