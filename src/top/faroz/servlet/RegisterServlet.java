package top.faroz.servlet;

import top.faroz.bean.Admin;
import top.faroz.bean.Reader;
import top.faroz.dao.AdminDao;
import top.faroz.dao.ReaderDao;
import top.faroz.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过writer，输出不同的值，这就是ajax回调函数中data的值
        //通过这个值，ajax可以做出判断
        PrintWriter writer = response.getWriter();
        String username = request.getParameter("username");
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");//获得是管理员还是读者

        //保证输入的信息完整
        //如果三个输入框，有一个没有输入，都会显示输入信息不完整
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(userid) || StringUtil.isEmpty(password)) {
            writer.print(0);
            return;
        }
        if ("reader".equals(identity)) {//如果是学生注册

            ReaderDao ReaderDao = new ReaderDao();
            Reader Reader=null;
            try {
                Integer.parseInt(userid);
            } catch (Exception e) {
                e.printStackTrace();
                writer.print(-1);//输入的账号不是纯数字
                return;
            }
            Reader = ReaderDao.get(Integer.parseInt(userid));

            if (!(Reader==null)) {
                writer.print(-2);//读者存在
                return;
            }
            Reader=new Reader();
            Reader.setId(Integer.parseInt(userid));
            Reader.setPassword(password);
            Reader.setName(username);
            System.out.println(Reader.toString());
            ReaderDao.add(Reader);
            writer.print(-3);//注册成功
            return;
        } else if ("admin".equals(identity)) {//如果是老师注册
            AdminDao AdminDao = new AdminDao();
            Admin Admin=null;
            try {
                Integer.parseInt(userid);
            } catch (Exception e) {
                e.printStackTrace();
                writer.print(-1);//输入的账号不是纯数字
                return;
            }
            Admin = AdminDao.get(Integer.parseInt(userid));

            if (!(Admin==null)) {
                writer.print(-2);//管理员存在存在
                return;
            }
            Admin=new Admin();
            Admin.setId(Integer.parseInt(userid));
            Admin.setPassword(password);
            Admin.setName(username);
            System.out.println(Admin.toString());
            AdminDao.add(Admin);
            writer.print(-3);//注册成功
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
