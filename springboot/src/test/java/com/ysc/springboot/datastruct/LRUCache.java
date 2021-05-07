package com.ysc.springboot.datastruct;

/**
 * @author yuanshancheng
 * @date 2021/5/7
 */
public interface LRUCache<K, V> {
    V get(K key);

    void set(K key, V value);

    void print();
}
