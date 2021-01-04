package top.faroz.bean;

/**
 * @ClassName ReaderBook
 * @Description 用于管理读者的书本借阅
 * 一行数据，代表该读者借了这本书
 * 每个读者，同一本书，只能借一本
 * @Author FARO_Z
 * @Date 2021/1/3 下午9:48
 * @Version 1.0
 **/
public class ReaderBook {
    private int id;
    private int reader_id;
    private int book_ISBN;

    public ReaderBook(int id, int reader_id, int book_ISBN) {
        this.id = id;
        this.reader_id = reader_id;
        this.book_ISBN = book_ISBN;
    }

    public ReaderBook() {
    }

    public int getReader_id() {
        return reader_id;
    }

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public int getBook_ISBN() {
        return book_ISBN;
    }

    public void setBook_ISBN(int book_ISBN) {
        this.book_ISBN = book_ISBN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
