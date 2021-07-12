package com.stock.algorithm.middle.query;

import org.junit.Test;

public class HIndex {

    public int hIndex(int[] arr) {
        int left = 0;
        int n = arr.length;
        int right = n - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if (arr[mid] >= n - mid){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return n - left;
    }


    @Test
    public void test(){
        int[] arr = {0,1,2,5,6};
        int i = hIndex(arr);
        System.out.println(i);
    }

}
