package interview;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-07-21
 */
public class Leetcode146 {

    public static class LRUCache{

        int capacity = 0;
        // 用hash表存储一份；便于查找
        private static Map<Integer,Node> map = new HashMap<>();
        // 两个傀儡节点-很重要 可以简化代码
        private static Node first;
        private static Node last;
        public LRUCache(int capacity) {
            this.capacity = capacity;
            first = new Node(0,0);
            last = new Node(0,0);
            first.next = last;
            last.pre = first;
        }

        public synchronized int get(int key) {
            Node node = map.get(key);
            if(null==node){
                return -1;
            }
            move2Head(node);

            return node.value;
        }

        public synchronized void put(int key, int value) {
            Node node1 = map.get(key);
            if(node1==null){
                Node newNode = new Node(key,value);
                add2Head(newNode);
                map.put(key,newNode);
                if(map.size()>capacity){
                    Node pre = last.pre;
                    removeNode(pre);
                    map.remove(pre.key);
                }
            }else {
                node1.value = value;
                move2Head(node1);
            }
        }

        public synchronized void add2Head(Node node){
            node.next = first.next;
            node.pre = first;
            first.next.pre = node;
            first.next = node;
        }

        public synchronized void removeNode(Node node){
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = null;
            node.next = null;
        }

        public synchronized void move2Head(Node node){
            removeNode(node);
            add2Head(node);
        }

        public static class Node{
            Integer key;
            Integer value;
            Node pre;
            Node next;
            public Node(Integer key,Integer value){
                this.key = key;
                this.value = value;
            }
        }
    }


    /**
     * 法一：利用linkedHashMap
     */
    public static class LRUCache0 extends LinkedHashMap<Integer,Integer>{
        int capacity = 0;
        public LRUCache0(int capacity) {
            // 主要设置accessOrder为true；代表使用访问来排序
            super(capacity,0.75F,true);
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer integer = super.get(key);
            return (null==integer)?-1:integer;
        }

        public void put(int key, int value) {
            super.put(key,value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
            // 如果链表长度大于设定的阈值；就执行删除最近最少使用元素
            return size()>capacity;
        }
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(1);
        lruCache.put(2,1);
        System.out.println(lruCache.get(2));
//            lruCache.put(2,2);
//            lruCache.get(1);
//            lruCache.put(3,3);
//            lruCache.get(2);
//            lruCache.put(4,4);
//            lruCache.get(1);
//            lruCache.get(3);
//            lruCache.get(4);
//            System.out.println();
    }
}
