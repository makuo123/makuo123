package com.stock.algorithm.middle;

import com.stock.algorithm.ListNode;
import org.junit.Test;

/**
 * 2. 两数相加
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照
 * 逆序的方式存储的，并且每个节点只能存储一位数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author mk
 * @Date 2021/6/30 10:02
 * @Version 1.0
 */
public class TwoNumAdd {

    public ListNode addNum(ListNode l1, ListNode l2){
        ListNode head = null, tail = null;
        int carry = 0;

        while (l1 != null || l2 != null){

            int n1 = l1 == null ? 0 : l1.num;
            int n2 = l2 == null ? 0 : l2.num;
            int sum = n1 + n2 + carry;
            if (head == null){
                head = tail = new ListNode(sum % 10);
            }else {
                tail = tail.next = new ListNode(sum % 10);
            }

            carry = sum / 10;
            if (l1 != null){
                l1 = l1.next;
            }

            if (l2 != null){
                l2 = l2.next;
            }

        }
        if (carry > 0){
            tail.next = new ListNode(carry);
        }
        return head;
    }

    @Test
    public void test(){
        ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(5)));

        ListNode l2 = new ListNode(2, new ListNode(4, new ListNode(6)));

        ListNode listNode = addNum(l1, l2);
        System.out.println(listNode.toString());
    }
}

