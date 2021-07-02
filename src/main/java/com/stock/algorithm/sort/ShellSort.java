package com.stock.algorithm.sort;

import org.junit.Test;

/**
 * shell排序
 *
 * @Author mk
 * @Date 2021/7/2 15:34
 * @Version 1.0
 */
public class ShellSort {

    public int[] shellSort(int[] arr) {

        for (int i = arr.length / 2; i > 0; i /= 2) {

            for (int j = i; j < arr.length; j++) {

                for (int k = j; k > 0 && k - i >= 0; k -= i) {
                    int temp;
                    if (arr[k] < arr[k - i]) {
                        temp = arr[k - i];
                        arr[k - i] = arr[k];
                        arr[k] = temp;
                    } else {
                        break;
                    }
                }

            }
        }

        return arr;
    }

    @Test
    public void test(){
        int[] arr = {1,4,2,9,4};
        int[] ints = shellSort(arr);
        System.out.println(ints);
    }
}
