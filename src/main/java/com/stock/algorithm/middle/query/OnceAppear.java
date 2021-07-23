package com.stock.algorithm.middle.query;

import org.junit.Test;

/**
 *  &	与	两个位都为1时，结果才为1
 *  |	或	两个位都为0时，结果才为0
 *  ^	异或	两个位相同为0，相异为1
 *  ~	取反	0变1，1变0
 *  <<	左移	各二进位全部左移若干位，高位丢弃，低位补0
 *  >>	右移	各二进位全部右移若干位，对无符号数，高位补0，有符号数，
 *      各编译器处理方法不一样，有的补符号位（算术右移），有的补0（逻辑右移）
 */
public class OnceAppear {

    /** 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现三次。*/
    public int singleForThreeNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    /** 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。*/
    public int singleForTwoNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }


    @Test
    public void test(){
        int[] arr = {1,2,3,2,1};
        int i = singleForTwoNumber(arr);
        System.out.println("i = " + i);
    }
}
