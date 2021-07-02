package com.stock.algorithm.sort;

/**
 * @Author mk
 * @Date 2021/7/2 17:27
 * @Version 1.0
 */
public class GuiBinSort {

    public void mergeSort(int[] arr, int start, int end) {
        // 当数组中多于一个数据时，继续拆分
        if (end - start > 0) {

            mergeSort(arr, start, (start + end) / 2);
            mergeSort(arr, (start + end) / 2 + 1, end);



        }
    }

}
