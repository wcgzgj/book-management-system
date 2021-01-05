package top.faroz.servlet;

import top.faroz.bean.ReaderBook;
import top.faroz.dao.BookDao;
import top.faroz.dao.ReaderBookDao;
import top.faroz.service.ReaderBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookISBN = Integer.parseInt(request.getParameter("bookISBN"));
        BookDao bookDao = new BookDao();
        bookDao.delete(bookISBN);

        //删除该书以后，如果有读者还在借阅该书，要将该记录删除
        ReaderBookService readerBookService = new ReaderBookService();
        readerBookService.deleteByBook(bookISBN);

        request.getRequestDispatcher("/managebook.jsp").forward(request,response);
    }
}
