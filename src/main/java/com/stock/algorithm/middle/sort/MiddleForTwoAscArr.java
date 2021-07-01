package com.stock.algorithm.middle.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2
 * 请你找出并返回这两个正序数组的 中位数
 *
 * @Author mk
 * @Date 2021/7/1 15:02
 * @Version 1.0
 */
public class MiddleForTwoAscArr {
    int a1 = 0;
    int a2 = 0;
    int num = 0;

    public Double middleForTwoAscArr(int[] arr1, int[] arr2) {

        int[] all = new int[arr1.length + arr2.length];

        int[] ints = mergeArr(arr1, arr2, all);

        if (ints != null) {
            int i = ints.length & 1;
            if (i == 1) {
                return Double.valueOf(ints[ints.length/2]);
            }else {
                return (Double.valueOf(ints[ints.length/2]) + Double.valueOf(ints[ints.length/2 -1]))/2;
            }
        }
        return 0d;
    }

    /** 示例 1：

     输入：nums1 = [1,3], nums2 = [2]
     输出：2.00000
     解释：合并数组 = [1,2,3] ，中位数 2
     示例 2：

     输入：nums1 = [1,2], nums2 = [3,4]
     输出：2.50000
     解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     示例 3：

     输入：nums1 = [0,0], nums2 = [0,0]
     输出：0.00000
     示例 4：

     输入：nums1 = [], nums2 = [1]
     输出：1.00000
     示例 5：

     输入：nums1 = [2], nums2 = []
     输出：2.00000

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
    private int[] mergeArr(int[] arr1, int[] arr2, int[] all) {

        if (arr1 == null || arr1.length == 0) {
            return arr2;
        }

        if (arr2 == null || arr2.length == 0) {
            return arr1;
        }

        if (a1 == arr1.length) {
            for (int i = a2; i < arr2.length; i++) {
                all[a1 + a2] = arr2[a2];
                a2++;
            }
            return all;
        }

        if (a2 == arr2.length) {
            for (int i = a1; i < arr1.length; i++) {
                all[a1 + a2] = arr2[a1];
                a1++;
            }
            return all;
        }

        if (arr1[a1] > arr2[a2]) {
            all[num] = arr2[a2];
            a2++;
        } else {
            all[num] = arr1[a1];
            a1++;
        }
        num++;
        mergeArr(arr1, arr2, all);
        return all;
    }

    @Test
    public void test(){
        System.out.println(middleForTwoAscArr(new int[]{1,2,3}, new int[]{}));
    }
}
