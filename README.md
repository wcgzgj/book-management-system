# book-management-system
javaweb，图书管理系统，软件设计与分析，期末大作业

## 前言

本项目是一个基于javaWeb的图书管理系统，实现了登录、注册和基本的CRUD操作

使用的技术是Servlet，jsp，ajax，bootstrap等

项目没有进行数据库调优（主要还是自己太菜了），所以应该无法撑起大规模访问

之所以我能够在短时间内完成这个javaWeb项目，是因为之前的小组大作业，都是我一个人完成的，所以我有权利对之前的项目进行重构，进而改变成我的个人大作业。也正是因为是前一个项目重构完成的，所以，这个项目的界面设计，与我之前完成的学生管理系统（第一次大作业）比较相似。



## 项目介绍

### 数据库设计

> 因为项目比较轻量级，功能不是太复杂，所以设计的数据库表也不多

![image-20210106031052204](https://gitee.com/faro/images/raw/master/img/20210106044920.png)

* book：

> 存储书本的信息，分别是书本ISBN编号，书本名称，书本价格，书本数目以及作者

* reader：

> 存储读者信息，包含读者的id，密码，以及真实姓名

* admin：

> 存储管理员信息，包含管理员的id，密码，以及真实姓名

* reader_book：

> 存储读者的借阅信息，包含自增长id，读者id，和图书的ISBN编号



### 前端界面一览

**登录界面：**

![image-20210106050331561](https://gitee.com/faro/images/raw/master/img/20210106050331.png)

**注册界面：**

![image-20210106050350058](https://gitee.com/faro/images/raw/master/img/20210106050350.png)

**读者端首页：**

![image-20210106050404265](https://gitee.com/faro/images/raw/master/img/20210106050404.png)

**借阅界面：**

![image-20210106050416168](https://gitee.com/faro/images/raw/master/img/20210106050416.png)

**归还界面：**

![image-20210106050428844](https://gitee.com/faro/images/raw/master/img/20210106050428.png)

**管理员首页：**

![image-20210106050444581](https://gitee.com/faro/images/raw/master/img/20210106050444.png)

**图书管理界面：**

![image-20210106050459501](https://gitee.com/faro/images/raw/master/img/20210106050459.png)

**用户管理界面：**

![image-20210106050509209](https://gitee.com/faro/images/raw/master/img/20210106050509.png)

## 项目内容描述

> 项目内容，我会分成前端和后端进行介绍，因为是CRUD项目，所以难免会出现很多重复的地方，我只会挑选重点来讲解

### 前端

> 前端的总体设计布局，是一个header（导航栏），一个footer（尾栏），和中间的内容
>
> 导航栏用来显示当前用户对象可以进行的操作
>
> 尾栏包含这个项目的版权信息（就是我的个人信息）

#### 导航栏

> 因为读者的导航栏和管理员的导航栏布局类似，所以只介绍一个读者的

![image-20210106050528150](https://gitee.com/faro/images/raw/master/img/20210106050528.png)

**代码如下：**

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--这里不包含taglib，因为重复包含，会导致500的错误--%>
<html>
<head>
    <title>首栏</title>
    <script>
        $(document).ready(function () {
            $("#resign_link").click(function () {
                ${sessionScope.clear()}
                window.location.href="./login.jsp";
            });
        });
    </script>
    <style>
        #resign_link:hover {
            cursor: pointer;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">图书管理</a>
        </div>

        <div class="collapse navbar-collapse header" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="./readerindex.jsp">首页 <span class="sr-only">(current)</span></a></li>
                <li><a href="./borrowbook.jsp">图书借阅</a></li>
                <li><a href="./returnbook.jsp">图书归还</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎 ${sessionScope.username}</a></li>
                <li><a id="resign_link">注销</a></li>
            </ul>
        </div>
    </div>
</nav>

</body>
</html>
```



#### 登录界面

![image-20210106050544295](https://gitee.com/faro/images/raw/master/img/20210106050544.png)

**代码如下：**

```jsp
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
```

在界面设计中，为了加快开发进度，我使用了bootstrap库，其中的登录、注册按钮，就是bootstrap中的样式

```jsp
<input type="button" id="btn_login" class="btn btn-primary" value="登录"/>
<input type="button" id="btn_register" class="btn btn-primary" value="注册"/>
```

![image-20210106050609065](https://gitee.com/faro/images/raw/master/img/20210106050609.png)



为了图省事，我没有为js代码额外开一个文件，而是直接和前端界面放在了一起（这不是一个好习惯），里面的主要功能，是获取输入框中的内容，然后使用ajax异步访问，让服务端去访问持久层，然后再向登录端传回响应

```jsp
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
```

注册界面的设计思路和登录界面的差不多，这里就不再额外介绍了



#### 图书借阅

![image-20210106050627814](https://gitee.com/faro/images/raw/master/img/20210106050627.png)

**代码如下：**

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书借阅</title>
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
           $("button[id='borrow']").click(function () {
                var bookISBN=$(this).val();
               $.ajax({
                   type:"post",//请求方式
                   url:"/borrowbook",//请求传输的位置
                   data:{
                       "bookISBN":bookISBN
                   },
                   error:function () {
                       alert("提交错误");
                   },
                   success:function (data) {
                       if (data==-1) {
                           alert("没有该图书")
                       } else if (data==-2) {
                           alert("图书不足");
                       } else if (data==-3) {
                           alert("当前正在借阅该书");
                       } else {
                           alert("借阅成功！")
                           //自动刷新页面
                           location.reload(true);
                       }
                   }
               });
           });
        });
    </script>
</head>
<body>

<%@include file="include/readerheader.jsp"%>

<%
    BookDao bookDao = new BookDao();
    List<Book> list = bookDao.list();
    request.setAttribute("bookList",list);

%>

<div style="    margin: auto;text-align: center" >
    <p class="notice_head">所有书籍</p>
    <table  class="table table-striped" style="text-align: center;margin: auto;width: 80%">
        <tr style="text-align: center">
            <th>序号</th>
            <th>书籍名称</th>
            <th>ISBN</th>
            <th>作者</th>
            <th>价格</th>
            <th>剩余数量</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${bookList}" var="book" varStatus="st">
            <tr>
                <td>${st.count}</td>
                <td>${book.name}</td>
                <td>${book.ISBN}</td>
                <td>${book.writer}</td>
                <td>${book.price}</td>
                <td>${book.num}</td>
                <td >
                    <button class="btn btn-primary" value="${book.ISBN}" id="borrow">借阅</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="include/footer.jsp"%>

</body>
</html>

```



这里，我使用了EL表达式和JSTL标签库，为的是更方便我的开发

**EL表达式：**

```jsp
<td>${st.count}</td>
```

**JSTL标签库：**

```jsp
<c:forEach items="${bookList}" var="book" varStatus="st">
```



当读者点选借阅按钮后，ajax会将对应的图书的ISBN传回后台，并在后台进行数量、读者是否正在借阅等判断，如果都判断有效，会将该书的数目减1，并在reader_book表中添加借阅数据（具体的后端代码，会在后面进行详细描述）

```jsp
 <script>
        $(document).ready(function () {
           $("button[id='borrow']").click(function () {
                var bookISBN=$(this).val();
               $.ajax({
                   type:"post",//请求方式
                   url:"/borrowbook",//请求传输的位置
                   data:{
                       "bookISBN":bookISBN
                   },
                   error:function () {
                       alert("提交错误");
                   },
                   success:function (data) {
                       if (data==-1) {
                           alert("没有该图书")
                       } else if (data==-2) {
                           alert("图书不足");
                       } else if (data==-3) {
                           alert("当前正在借阅该书");
                       } else {
                           alert("借阅成功！")
                           //自动刷新页面
                           location.reload(true);
                       }
                   }
               });
           });
        });
    </script>
```



表格，是借助JSP中的JAVA代码，和JSTL标签库实现的

**JSP中的JAVA代码：**

```jsp
<%
    BookDao bookDao = new BookDao();
    List<Book> list = bookDao.list();
    request.setAttribute("bookList",list);

%>
```

**JSTL表达式，实现循环：**

```jsp
        <c:forEach items="${bookList}" var="book" varStatus="st">
            <tr>
                <td>${st.count}</td>
                <td>${book.name}</td>
                <td>${book.ISBN}</td>
                <td>${book.writer}</td>
                <td>${book.price}</td>
                <td>${book.num}</td>
                <td >
                    <button class="btn btn-primary" value="${book.ISBN}" id="borrow">借阅</button>
                </td>
            </tr>
        </c:forEach>
```



#### Bootstrap

> Bootstrap的使用，为我的开发带来了极大的便利，使得我一个人在两天内开发一个JavaWeb项目成为了可能

**在使用bootstrap前，要导入对应的库：**

> 这里为了图方便，我直接使用了bootstrap的在线库

```jsp
<%--新 Bootstrap 核心 CSS 文件--%>
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<%--jQuery文件。务必在bootstrap.min.js 之前引入--%>
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

<%--最新的 Bootstrap 核心 JavaScript 文件--%>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
```

下面，我会展示bootstrap的便利



**定制按钮：**

> 这个之前在讲登录、注册的时候已经提过了

```jsp
<input type="button" id="btn_login" class="btn btn-primary" value="登录"/>
<input type="button" id="btn_register" class="btn btn-primary" value="注册"/>
```

![image-20210106050655220](https://gitee.com/faro/images/raw/master/img/20210106050655.png)



**图形按钮：**

> 比如编辑的笔的形状，删除的小垃圾桶形状

![image-20210106050711858](https://gitee.com/faro/images/raw/master/img/20210106050711.png)

```jsp
<a href="/editbook.jsp?bookISBN=${book.ISBN}" class="glyphicon glyphicon-edit"></a>
<a href="/deletebook?bookISBN=${book.ISBN}" class="glyphicon glyphicon-trash"></a>
```



**表格：**

> 项目里的斑马条纹表格，也是使用的bootstrap

![image-20210106050729140](https://gitee.com/faro/images/raw/master/img/20210106050729.png)

```jsp
<table  class="table table-striped" style="text-align: center;margin: auto;width: 80%">
```



前端的剩余部分，无论是外观，还是功能上的，都大体类似，所以，我不再做过多的赘述，下面，我们进入后端部分的讲解



### 后端

> 后端的主要编写，在于Dao层和servlet部分，前者，是用来固化数据的，后者，是用来处理前端传来的数据的（即我们常说的业务代码）

#### Bean层

> Bean，也称为pojo，entity（个人习惯问题），是数据库中，每个表的对应类，其中每个成员变量，都对应数据库表中的一个属性
>
> 一般的bean，都包含带参构造函数，无参构造函数，get set方法 和重写的toString方法（方便打印查看），为了保障通用性，我没有使用lombok简化bean（我觉得用这玩意儿就是绑架）
>
> 编写Bean层，是为了保证数据库数据，和业务数据可以有一个良好的交互和完美的转换方案

**bean.Book：**

```java
public class Book {
    private String name;
    private float price;
    private int num;
    private String writer;
    private int ISBN;

    public Book(String name, float price, int num, String writer, int ISBN) {
        this.name = name;
        this.price = price;
        this.num = num;
        this.writer = writer;
        this.ISBN = ISBN;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", writer='" + writer + '\'' +
                ", ISBN=" + ISBN +
                '}';
    }
}
```





#### Dao层

> Dao层，是用来固化数据的，其中的业务代码，无非就是增删改查这四种，这里，我拿一个BookDao的部分来举例子

**BookDao.list()：**

用来提取出book表中所有的数据

```java
public List<Book> list() {
        List<Book> list = new ArrayList<>();
        String sql = "select * from book";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int ISBN = rs.getInt(1);
                String name = rs.getString(2);
                float price = rs.getFloat(3);
                int num = rs.getInt(4);
                String writer = rs.getString(5);
                Book book = new Book(name, price, num, writer, ISBN);
                list.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
```



**BookDao.add(Book bean)：**

用来向book表中添加数据

```java
 public void add(Book bean) {
        String sql = "insert into book values(?,?,?,?,?)";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,bean.getISBN());
            stmt.setString(2,bean.getName());
            stmt.setFloat(3,bean.getPrice());
            stmt.setInt(4,bean.getNum());
            stmt.setString(5,bean.getWriter());

            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
```

因为JDBC中很多内容，都是挺重复的，所以剩下的我就不展示了





#### Servlet

> 这里，我先来分享一下 servlet的事件处理流程
>
> ![image-20210106050821845](https://gitee.com/faro/images/raw/master/img/20210106050821.png)
>
> 下面，我就以一个BorrowBookServlet来举例，并展示一下整个由前端到后端，再到前端的流程

**servlet.BookBookServlet：**

* **ajax异步传输数据：**

> 这里传输的，是书本的ISBN编号，并且指定使用post方法
>
> 如果传输失败，会弹出提交错误的提示
>
> 请求访问的是/borrowbook，这会在.xml中进行匹配

```jsp
 <script>
        $(document).ready(function () {
           $("button[id='borrow']").click(function () {
                var bookISBN=$(this).val();
               $.ajax({
                   type:"post",//请求方式
                   url:"/borrowbook",//请求传输的位置
                   data:{
                       "bookISBN":bookISBN
                   },
                   error:function () {
                       alert("提交错误");
                   },
                   success:function (data) {
                       if (data==-1) {
                           alert("没有该图书")
                       } else if (data==-2) {
                           alert("图书不足");
                       } else if (data==-3) {
                           alert("当前正在借阅该书");
                       } else {
                           alert("借阅成功！")
                           //自动刷新页面
                           location.reload(true);
                       }
                   }
               });
           });
        });
    </script>
```

* **xml配置文件：**

> ajax中，要求访问的地址，是/borrowbook，这要在.xml中匹配，转移到对应的servlet中

```xml
 <servlet>
        <servlet-name>BorrowBookServlet</servlet-name>
        <servlet-class>top.faroz.servlet.BorrowBookServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>BorrowBookServlet</servlet-name>
        <url-pattern>/borrowbook</url-pattern>
    </servlet-mapping>
```

* **Servlet：**

> 这里到了我们的重头戏，servlet
>
> 这里，会根据传来的ISBN编号，和当前登录的用户的ID，向reader_book表中存储图书借阅的信息。如果在判断中出现错误，会返回错误代码，以让前端的ajax接收到，从而更具错误代码，对用户进行提示。

```java
@WebServlet(name = "BorrowBookServlet")
public class BorrowBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookISBN = request.getParameter("bookISBN");
        Reader reader = (Reader) request.getSession().getAttribute("user");//登录的读者信息

        PrintWriter writer = response.getWriter();
        BookDao bookDao = new BookDao();
        ReaderBookDao readerBookDao = new ReaderBookDao();
        ReaderBookService readerBookService = new ReaderBookService();
        Book book = bookDao.get(Integer.parseInt(bookISBN));

        //对借阅进行判断
        if (book==null) {
            writer.print(-1);//-1 没有该图书
            return;
        }
        if (book.getNum()<=0) {
            writer.print(-2);//-2 图书不足
            return;
        }
        if (readerBookService.getByReaderAndBook(reader.getId(),Integer.parseInt(bookISBN))!=null) {
            writer.print(-3);//-3 读者正在借阅该书
            return;
        }

        //将借阅的书籍个数-1
        book.setNum(book.getNum()-1);
        bookDao.update(book);
        //将读者信息和书本信息存入reader_book
        ReaderBook readerBook = new ReaderBook();
        readerBook.setReader_id(reader.getId());
        readerBook.setBook_ISBN(Integer.parseInt(bookISBN));
        readerBookDao.add(readerBook);
        request.getRequestDispatcher("/borrowbook.jsp").forward(request,response);
    }
```

上面，就是用户选择借阅以后，整个的运行流程。

其他的功能，因为和上面是实现方式差不多，所以不再过多的进行赘述









