import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuanshancheng
 * @date 2021/3/20
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        for (int i = 0; i < list.size(); ++i) {
            list.remove(i);
        }
        System.out.println(list);
    }
}
