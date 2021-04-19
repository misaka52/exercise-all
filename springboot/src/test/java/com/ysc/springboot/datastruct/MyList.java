package com.ysc.springboot.datastruct;

/**
 * @author yuanshancheng
 * @date 2021/3/25
 */
public class MyList<V> {
    private ListNode<V> head;
    private ListNode<V> tail;

    static class ListNode<V> {
        private V value;
        private ListNode<V> pre;
        private ListNode<V> next;
        public ListNode(V value) {
            this.value = value;
        }

        public V getValue() {
            return this.value;
        }
    }

    public void addFirst(V value) {
        ListNode node = new ListNode(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.pre = node;
            node.next = head;
            head = node;
        }
    }

    public void removeToHead(ListNode<V> node) {
        if (node == null || node == head) {
            return;
        }
        // 重组node前后节点指针
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        // 将node转移到head之前
        node.next = head;
        head.pre = node;
        node.pre = null;
        head = node;
    }

    public ListNode removeLast() {
        if (tail == null) {
            return null;
        }
        ListNode returnNode = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.pre;
            tail.next = null;
        }
        return returnNode;
    }
}
