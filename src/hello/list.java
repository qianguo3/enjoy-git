package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static javax.swing.text.html.HTML.Tag.HEAD;

public class list {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list = new ArrayList<>();

        try {
            list.get(0);
            System.out.println("捕获了上合并");

        } catch (Exception e) {
            System.out.println("捕获了");
        }

    }
}
