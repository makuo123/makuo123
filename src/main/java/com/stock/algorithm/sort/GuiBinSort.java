package com.stock.algorithm.sort;

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
        int[] arr = {3,2,1,9,8};
        int[] ints = mergeSort(arr, 0, 4);
        System.out.println(ints);
    }
}
