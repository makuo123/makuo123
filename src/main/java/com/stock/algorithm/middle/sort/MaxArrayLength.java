package com.stock.algorithm.middle.sort;

import org.junit.Test;

import java.util.Arrays;

public class MaxArrayLength {

    public int maxArrayLength(int[] arr) {
        Arrays.sort(arr);
        int length = arr.length;

        arr[0] = 1;
        for (int i = 1; i < length; i++) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }
        return arr[length - 1];
    }

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] cnt = new int[n + 1];
        for (int v : arr) {
            ++cnt[Math.min(v, n)];
        }

        int miss = 0;
        for (int i = 0; i <= n; i++) {
            if (cnt[i] == 0) {
                ++miss;
            } else {
                miss -= Math.min(cnt[i] - 1, miss);
            }
        }
        return n - miss;
    }


    public int maximumElementAfterDecrementingAndRearranging1(int[] arr) {
        int n = arr.length;
        int[] cnt = new int[n + 1];
        for (int v : arr) {
            ++cnt[Math.min(v, n)];
        }
        int miss = 0;
        for (int i = 1; i <= n; ++i) {
            if (cnt[i] == 0) {
                ++miss;
            } else {
                miss -= Math.min(cnt[i] - 1, miss); // miss 不会小于 0，故至多减去 miss 个元素
            }
        }
        return n - miss;
    }


    @Test
    public void test() {
        /*int[] arr = {1, 2, 2, 5, 4, 100, 100, 100, 101};
        //int[] arr = {2,2,1,2,1};
        int max = maxArrayLength(arr);
        System.out.println(max);*/
        String s = "abc";
        String substring = s.substring(0, 10);
        System.out.println("substring = " + substring);
    }
}
