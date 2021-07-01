package com.stock.algorithm;


public class ListNode {
    public int num;

    public ListNode next;

    public ListNode() {
    }

    public ListNode(int num) {
        this.num = num;
    }

    public ListNode(int num, ListNode next) {
        this.num = num;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "num=" + num +
                ", next=" + next +
                '}';
    }
}