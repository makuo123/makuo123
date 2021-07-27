package com.stock.algorithm.sort;

import com.stock.algorithm.ListNode;
import org.junit.Test;

/**
 * 归并查找
 *
 * @Author mk
 * @Date 2021/7/2 17:27
 * @Version 1.0
 */
public class GuiBinSort {

    public int[] mergeSort(int[] arr, int start, int end) {
        // 当数组中多于一个数据时，继续拆分
        if (end - start > 0) {

            mergeSort(arr, start, (start + end) / 2);
            mergeSort(arr, (start + end) / 2 + 1, end);

            // 记录移动位置
            int left = start;
            int right = (start + end) / 2 + 1;

            int index = 0;
            int[] result = new int[(end - start + 1)];

            while (left <= (start + end)/2 && right <= end){
                if (arr[left] < arr[right]){
                    result[index] = arr[left];
                    left++;
                }else {
                    result[index] = arr[right];
                    right++;
                }
                index++;
            }

            while (left <= (start + end)/2 || right <= end){
                if (left <= (start + end)/2){
                    result[index] = arr[left];
                    left++;
                }else {
                    result[index] = arr[right];
                    right++;
                }
                index++;
            }

            for (int i = start; i <= end; i++) {
                arr[i] = result[i - start];
            }
        }

        return arr;
    }

    @Test
    public void test(){
//        int[] arr = {3,2,1,9,8};
//        int[] ints = mergeSort(arr, 0, 4);
        Solution solution = new Solution();
        ListNode listNode = solution.sortList(new ListNode(3, new ListNode(4, new ListNode(1))));
        System.out.println(listNode);
    }
}

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merged = merge(head1, head2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.num <= temp2.num) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}
