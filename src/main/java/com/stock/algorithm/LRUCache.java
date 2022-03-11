package com.stock.algorithm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @param <V> 缓存容量
 */
public class LRUCache<V> {

    /**
     * 容量
     */
    private int capacity = 1024;
    /**
     * Node记录表  记录数据
     */
    private Map<String, ListNode<String, V>> table = new ConcurrentHashMap<>();
    /**
     * 用双向链表记录数据的访问频率，头节点最近访问，尾节点访问频率低，内存满后淘汰尾节点
     * 双向链表头部
     */
    private ListNode<String, V> head;
    /**
     * 双向链表尾部
     */
    private ListNode<String, V> tail;


    /**
     * 指定内存容量
     *
     * @param capacity 容量值大小
     */
    public LRUCache(int capacity) {
        this();
        this.capacity = capacity;
    }

    /**
     * 初始化 LRUCache,默认内存容量
     */
    public LRUCache() {
        head = new ListNode<>();
        tail = new ListNode<>();
        head.next = tail;
        head.prev = null;
        tail.prev = head;
        tail.next = null;
    }


    public V get(String key) {

        ListNode<String, V> node = table.get(key);
        //如果Node不在表中，代表缓存中并没有
        if (node == null) {
            return null;
        }
        //如果存在，则需要移动Node节点到表头


        //截断链表，node.prev -> node  -> node.next ====> node.prev -> node.next
        //         node.prev <- node <- node.next  ====>  node.prev <- node.next
        node.prev.next = node.next;
        node.next.prev = node.prev;

        //移动节点到表头
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
        //存在缓存表
        table.put(key, node);
        return node.value;
    }


    public void put(String key, V value) {
        ListNode<String, V> node = table.get(key);
        //如果Node不在表中，代表缓存中并没有
        if (node == null) {
            if (table.size() == capacity) {
                //超过容量了 ,首先移除尾部的节点
                table.remove(tail.prev.key);
                tail.prev = tail.next;
                tail.next = null;
                tail = tail.prev;

            }
            node = new ListNode<>();
            node.key = key;
            node.value = value;
            table.put(key, node);
        }
        //如果存在，则需要移动Node节点到表头
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;


    }

    /**
     * 双向链表内部类
     */
    public static class ListNode<K, V> {
        private K key;
        private V value;
        ListNode<K, V> prev;
        ListNode<K, V> next;

        public ListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }


        public ListNode() {

        }
    }


    public static void main(String[] args) {
        LRUCache<ListNode> cache = new LRUCache<>(4);
        ListNode<String, Integer> node1 = new ListNode<>("key1", 1);
        ListNode<String, Integer> node2 = new ListNode<>("key2", 2);
        ListNode<String, Integer> node3 = new ListNode<>("key3", 3);
        ListNode<String, Integer> node4 = new ListNode<>("key4", 4);
        ListNode<String, Integer> node5 = new ListNode<>("key5", 5);
        cache.put("key1", node1);
        cache.put("key2", node2);
        cache.put("key3", node3);
        cache.put("key4", node4);
        cache.get("key2");
        cache.put("key5", node5);
        ListNode key2 = cache.get("key2");
        System.out.println(key2);
    }
}

