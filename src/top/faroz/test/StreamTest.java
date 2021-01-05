package top.faroz.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName StreamTest
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/1/6 上午12:48
 * @Version 1.0
 **/
public class StreamTest {
    public static void main(String[] args) {
        Guy g1 = new Guy(1, "haha");
        Guy g2 = new Guy(2, "jojo");
        Guy g3 = new Guy(3, "dio");
        Guy g4 = new Guy(1, "faro");
        List<Guy> list = new ArrayList<>();

        list.add(g1);
        list.add(g2);
        list.add(g3);
        list.add(g4);

        System.out.println(list.toString());

        System.out.println();

        list = list.stream()
                .filter(guy -> guy.id == 1)
                .collect(Collectors.toList());

        System.out.println(list.toString());
    }

}

class Guy {
    public int id;
    public String name;

    public Guy(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Guy() {
    }

    @Override
    public String toString() {
        return "Guy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
