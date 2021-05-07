package com.ysc.springboot.datastruct;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuanshancheng
 * @date 2021/5/7
 */
public class LRUCache2<K, V> implements LRUCache<K, V> {
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> pre;
        Node<K, V> next;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public String toString() {
            return key + ":" + value;
        }
    }
    private final Map<K, Node<K, V>> map;
    private Node<K, V> head;
    private Node<K, V> tail;
    private final int size;
    public LRUCache2(int size) {
        this.size = size;
        map = new ConcurrentHashMap<>(size);
    }
    @Override
    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    @Override
    public void set(K key, V value) {
        Node<K, V> oldNode = map.get(key);
        if (oldNode == null) {
            // 新增节点
            Node<K, V> newNode = new Node<>(key, value);
            map.put(key, newNode);
            addToHead(newNode);
            if (map.size() > size) {
                map.remove(tail.key);
                remove(tail);
            }
        } else {
            // 老节点，更新value
            oldNode.value = value;
            moveToHead(oldNode);
        }
    }
    
    private void moveToHead(Node<K, V> node) {
        if (node != head) {
            remove(node);
            addToHead(node);
        }
    }

    private void remove(Node<K, V> node) {
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node == head) {
            head = node.next;
        }
        if (node == tail) {
            tail = node.pre;
        }
        node.pre = null;
        node.next = null;
    }
    
    private void addToHead(Node<K, V> node) {
        if (head == null) {
            tail = node;
        } else {
            head.pre = node;
            node.next = head;
        }
        head = node;
    }

    @Override
    public void print() {
        Node<K, V> cur = head;
        StringBuilder stringBuilder = new StringBuilder();
        while (cur != null) {
            stringBuilder.append(cur).append(",");
            cur = cur.next;
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
    }
}
