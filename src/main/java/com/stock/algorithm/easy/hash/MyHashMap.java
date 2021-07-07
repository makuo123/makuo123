package com.stock.algorithm.easy.hash;

import org.junit.Test;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Author mk
 * @Date 2021/7/6 10:29
 * @Version 1.0
 */
public class MyHashMap<K,V> {

    private class Pair{
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private LinkedList[] data;
    private static final int BASE = 769;

    public MyHashMap() {

        data = new LinkedList[BASE];

        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Pair>();
        }

    }

    public void put(K key, V value){
        int h = hash(key);

        Iterator<Pair> iterator = data[h].iterator();

        while (iterator.hasNext()){
            Pair pair = iterator.next();
            if (pair.getKey() == key){
                pair.setValue(value);
                return;
            }
        }

        data[h].offerLast(new Pair(key, value));
    }

    public V get(K key){
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Pair pair = iterator.next();
            if (pair.getKey() == key){
                return pair.getValue();
            }
        }
        return null;
    }

    public void remove(K key){
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Pair pair = iterator.next();
            if (pair.getKey() == key){
                data[h].remove(pair);
                return;
            }
        }
    }

    private int hash(K key){
        return Integer.valueOf(String.valueOf(key)) % BASE;
    }

    @Test
    public void test(){
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        myHashMap.put(1, "a");
        myHashMap.put(2, "b");
        String s = myHashMap.get(1);
        myHashMap.remove(1);
        myHashMap.put(2, "c");
        System.out.println(1);
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1, 1);
        if (map == null){
            //region Description
            System.out.println(1);
            //endregion
        }

    }
}
