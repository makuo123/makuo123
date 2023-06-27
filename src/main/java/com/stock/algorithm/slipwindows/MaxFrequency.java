package com.stock.algorithm.slipwindows;

import org.junit.Test;
import org.springframework.orm.hibernate5.SpringBeanContainer;

import java.util.Arrays;

/**
 * 元素的 频数 是该元素在一个数组中出现的次数。
 * <p>
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 * <p>
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,4], k = 5
 * 输出：3
 * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * 4 是数组中最高频元素，频数是 3 。
 * <p> 不重复字符串长度
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxFrequency {

    /**
     * 方法一：排序 + 滑动窗口
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    @Test
    public void test(){
        int[] arr = {1,2,3,4};
        int k = 4;
        System.out.println("maxFrequency(arr,k) = " + maxFrequency(arr, k));
    }
}
