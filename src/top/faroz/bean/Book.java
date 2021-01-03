package top.faroz.bean;

/**
 * @ClassName Book
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/1/3 下午8:41
 * @Version 1.0
 **/
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
