package com.stock.algorithm.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeNumAdd {

    public static List<List<Integer>> threeNumAdd(int[] arr){
        // 1、将数组排序，避免重复
        Arrays.sort(arr);
        // 2、确定第三层循环指针位置
        int length = arr.length;
        int third = length - 1;
        // 返回结果
        List<List<Integer>> list = new ArrayList<>();
        // 3、循环遍历
        for (int first = 0; first < length; first++) {
            // 4、当first遍历时，需要和上次的数值不同
            if (first > 0 && arr[first] == arr[first -1]){
                continue;
            }
            // 5、arr[second] + arr[third] = -arr[first]
            int target = -arr[first];
            for (int second = first + 1; second < length; second++) {
                // 6、second移动的数据不同
                if (second > first + 1 && arr[second] == arr[second -1]){
                    continue;
                }
                // 7、当second < third，即双指针的第二场指针在第三层的左边，两数之和大于目标值时，third左移
                while (second < third && arr[second] + arr[third] > target){
                    --third;
                }
                // 8、当second = third时，循环结束
                if (second == third){
                    break;
                }
                // 9、当符合条件时，封装数据
                if (arr[second] + arr[third] == target){
                    List<Integer> subList = new ArrayList<>();
                    subList.add(arr[first]);
                    subList.add(arr[second]);
                    subList.add(arr[third]);
                    list.add(subList);
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeNumAdd(nums));
    }
}
