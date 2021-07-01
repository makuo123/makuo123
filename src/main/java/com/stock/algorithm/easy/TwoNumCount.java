package com.stock.algorithm.easy;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * @Author mk
 * @Date 2021/6/28 14:13
 * @Version 1.0
 */
public class TwoNumCount {


    /**
     * 时间复杂度为O(n) n为nums的长度
     */
    @Test
    public void numCount() {

        Map<Integer, Integer> map = new HashMap<>();

        int[] nums = {0, 1, 2, 4, 6};
        int target = 6;

        for (int i = 0; i < nums.length; i++) {
            int one = target - nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                if (one == nums[j]) {
                    map.put(i, j);
                }
            }
        }

        System.out.println(map.toString());
    }


    /**
     * 时间复杂度：O(N)O(N)，其中 NN 是数组中的元素数量。对于每一个元素 x，我们可以 O(1)O(1) 地寻找 target - x。
     *
     * 空间复杂度：O(N)O(N)，其中 NN 是数组中的元素数量。主要为哈希表的开销。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * */
    @Test
    public void numCount2() {

        Map<Integer, Integer> map = new HashMap<>();

        int[] nums = {0, 1, 2, 4, 6};
        int target = 6;

        for (int i = 0; i < nums.length; i++) {
            int one = target - nums[i];

            if (map.containsKey(one)) {
                Integer integer = map.get(one);
                System.out.println(one + "=" + integer);
                break;
            }

            map.put(nums[i], one);
        }

        //System.out.println(map.toString());
    }
}
