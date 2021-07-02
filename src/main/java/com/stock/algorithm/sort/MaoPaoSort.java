package com.stock.algorithm.sort;

import org.junit.Test;

/**
 * java常见排序算法:
 * 冒泡算法
 *
 * @Author mk
 * @Date 2021/7/2 9:45
 * @Version 1.0
 */
public class MaoPaoSort<T> {

    /**
     * 冒泡算法思路：
     *    a、冒泡排序，是通过每一次遍历获取最大/最小值
     *
     * 　　b、将最大值/最小值放在尾部/头部
     *
     * 　　c、然后除开最大值/最小值，剩下的数据在进行遍历获取最大/最小值
     *
     * 　　d、代码实现
     */
    public int[] bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            int temp;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }

        }

        return arr;
    }

    private void getMinValue(int[] arr) {
        int min = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                min = arr[0];
            } else {
                if (arr[i] < min) {
                    min = arr[i];
                    index = i;
                }
            }
        }
    }

    @Test
    public void test() {
        int[] ints = {4, 9, 1, 3, 4, 8};
        int[] ints1 = bubbleSort(ints);
        System.out.println(ints1.toString());
    }
}
