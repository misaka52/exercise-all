/**
 * @author yuanshancheng
 * @date 2021/2/22
 */
public class Main {
    private static int _1MB = 1024 * 1024;
    public static void main(String[] args) throws InterruptedException {
        stringAdd();
    }

    static void stringAdd() {
        String s1 = "aaa";
        String s2 = "aaa";
        String s3 = "aaa";
        String s4 = s1 + s2;
        System.out.println(s4);
    }
}
