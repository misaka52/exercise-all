package juc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuanshancheng
 * @date 2021/3/19
 */
public class CHMTest {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put(1, null);
    }
}
