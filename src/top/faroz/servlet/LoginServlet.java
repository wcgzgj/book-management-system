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

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");//获得是管理员还是读者
        PrintWriter writer = response.getWriter();//用来向ajax的回调函数返回数据
        if (StringUtil.isEmpty(userid) || StringUtil.isEmpty(password)) {//如果输入的信息为空
            writer.print(-1);//-1 信息为空
            return;
        }
        if (!StringUtil.isInteger(userid)) {
            writer.print(-2);//-2 输入的账号不是纯数字
            return;
        }

        if ("admin".equals(identity)) {//管理员
            AdminDao AdminDao = new AdminDao();
            Admin Admin=null;
            Admin=AdminDao.get(Integer.parseInt(userid));
            if (Admin==null) {
                writer.print(-3);//-3 查无此人
                return;
            }
            if (!(Admin.getPassword().equals(password))) {
                writer.print(-4);//-4 密码错误
                return;
            }
            //当一切判断正常，要在session中设置登录的相关信息
            request.getSession().setAttribute("user",Admin);
            request.getSession().setAttribute("username",Admin.getName());
        } else if ("reader".equals(identity)) {
            ReaderDao ReaderDao = new ReaderDao();
            Reader Reader=null;
            Reader=ReaderDao.get(Integer.parseInt(userid));
            if (Reader==null) {
                writer.print(-3);//-3 查无此人
                return;
            }
            if (!(Reader.getPassword().equals(password))) {
                writer.print(-4);//-4 密码错误
                return;
            }
            //在session中设置用户的相关信息
            request.getSession().setAttribute("user",Reader);
            request.getSession().setAttribute("username",Reader.getName());
        }
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
