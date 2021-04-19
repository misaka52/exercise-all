package com.ysc.springboot.datastruct;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author yuanshancheng
 * @date 2021/3/25
 */
public class LRUCache<K, V> {
    private final LinkedList<K> list;
    private final Map<K, V> cacheMap;
    private final int size;
    public LRUCache(int size) {
        this.size = size;
        list = new LinkedList<>();
        cacheMap = new HashMap<>(size * 4 / 3 + 1);
    }

    public V get(K key) {
        V value = cacheMap.get(key);
        if (key != null) {
            moveToHead(key);
        }
        return value;
    }

    public V set(K key, V value) {
        V oldValue = cacheMap.put(key, value);
        if (oldValue == null) {
            list.addFirst(key);
        }  else {
            moveToHead(key);
        }
        if (cacheMap.size() > this.size) {
            K expireKey = list.removeLast();
            cacheMap.remove(expireKey);
        }
        return oldValue;
    }

    private void moveToHead(K key) {
        list.remove(key);
        list.addFirst(key);
    }
}
