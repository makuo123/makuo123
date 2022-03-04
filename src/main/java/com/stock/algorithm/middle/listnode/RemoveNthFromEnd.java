package com.stock.algorithm.middle.listnode;

import com.stock.algorithm.ListNode;

public class RemoveNthFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;

        ListNode ans = dummy.next;
        return ans;
    }

    public static void main(String[] args) {
        ListNode next = new ListNode(4, null);
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, next)));
        removeNthFromEnd(listNode,12);
    }
}
