package top.faroz.servlet;

import top.faroz.bean.Book;
import top.faroz.bean.Reader;
import top.faroz.dao.BookDao;
import top.faroz.service.ReaderBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookISBN = Integer.parseInt(request.getParameter("bookISBN"));
        Reader reader = (Reader) request.getSession().getAttribute("user");

        //将reader_book表中的对应记录删除
        ReaderBookService readerBookService = new ReaderBookService();
        readerBookService.deleteByReaderAndBook(reader.getId(),bookISBN);

        //将对应book的数目加一
        BookDao bookDao = new BookDao();
        Book book = bookDao.get(bookISBN);
        book.setNum(book.getNum()+1);
        bookDao.update(book);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
