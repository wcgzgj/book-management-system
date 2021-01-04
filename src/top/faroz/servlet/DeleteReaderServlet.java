package top.faroz.servlet;

import top.faroz.dao.ReaderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteReaderServlet")
public class DeleteReaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String readerID = request.getParameter("readerID");
        ReaderDao readerDao = new ReaderDao();
        readerDao.delete(Integer.parseInt(readerID));

        request.getRequestDispatcher("/managereader.jsp").forward(request, response);
    }
}
