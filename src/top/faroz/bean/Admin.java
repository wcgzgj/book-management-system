package top.faroz.bean;

/**
 * @ClassName Admin
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/1/3 下午8:43
 * @Version 1.0
 **/
public class Admin {
    private int id;
    private String name;
    private String password;

    public Admin() {
    }

    public Admin(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
