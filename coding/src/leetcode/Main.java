package leetcode;

import java.util.*;

/**
 * @author yuanshancheng
 * @date 2021/3/9
 */
public class Main {
    public static void main(String[] args) {
//        new Temp().reversePairs(new int[]{7, 5, 6, 4});
        System.out.println(new Temp().LCS("1A2", "12"));
    }

    private static void order(Integer i) {
        i = 1;
    }


    static void scanner() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int i = scanner.nextInt();
            System.out.println(~i);
        }
    }

    static void struct() {
        LinkedList<Integer> list = new LinkedList<>();
        list.pop();
        list.poll();
        list.peek();

        Collections.reverse(list);

        List<String> list2 = new ArrayList<>();
        String[] is = (String[])list2.toArray();

        List<Integer> arrayList = new ArrayList<>();

        int[] res = new int[1];

        Set<String> set = new HashSet<>();
        set.add("12");
        Integer[] arr = new Integer[10];
        Arrays.sort(arr, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                return -1;

            }
        });

        list.toArray();


        String string = new String();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.reverse();
        Deque<Integer> deque = new LinkedList<>();
        deque.poll();
        deque.peek();
        deque.add(1);
        Queue<Integer> queue = new LinkedList<>();
        Math.pow(1,2);
        List<Integer> list4 = new ArrayList<Integer>(){{
            add(1);
        }};

    }
}
