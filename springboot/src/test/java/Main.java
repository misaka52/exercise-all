import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuanshancheng
 * @date 2021/2/22
 */
public class  Main {
    static void optimize() {
        for (int i = 0; i < 10; ++i) {
            loop();
        }
    }

    static void loop() {
        long start = System.currentTimeMillis();
        int k = 0;
        for (int i = 0; i < 1000000000; ++i) {
//            k += i;
        }
        System.out.println((System.currentTimeMillis() - start));
        List<Integer> list;
//        list.add(1);
    }
}
