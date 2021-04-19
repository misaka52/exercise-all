package com.ysc.springboot.datastruct;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuanshancheng
 * @date 2021/3/25
 */
public class SyncLRUCache<K, V> {
    private final LinkedList<K> list;
    private final Map<K, V> cacheMap;
    private final int size;
    private final ReentrantLock lock = new ReentrantLock();
    public SyncLRUCache(int size) {
        this.size = size;
        list = new LinkedList<>();
        cacheMap = new ConcurrentHashMap<>(size);
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
            addKey(key);
        } else {
            moveToHead(key);
        }
        if (cacheMap.size() > this.size) {
            K expireKey = removeLast();
            cacheMap.remove(expireKey);
        }
        return oldValue;
    }

    private void addKey(K key) {
        lock.lock();
        try {
            list.addFirst(key);
        } finally {
            lock.unlock();
        }
    }

    private K removeLast() {
        lock.lock();
        try {
            return list.removeLast();
        } finally {
            lock.unlock();
        }
    }

    private void moveToHead(K key) {
        lock.lock();
        try {
            list.remove(key);
            list.addFirst(key);
        } finally {
            lock.unlock();
        }
    }
}
