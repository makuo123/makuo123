package com.stock.algorithm.middle;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author mk
 * @Date 2021/7/7 14:04
 * @Version 1.0
 */
public class DeliciousType {

        public int countPairs(int[] deliciousness) {
            final int MOD = 1000000007;
            int maxVal = 0;
            for (int val : deliciousness) {
                maxVal = Math.max(maxVal, val);
            }
            int maxSum = maxVal * 2;
            int pairs = 0;
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            int n = deliciousness.length;
            for (int i = 0; i < n; i++) {
                int val = deliciousness[i];
                for (int sum = 1; sum <= maxSum; sum <<= 1) {
                    int count = map.getOrDefault(sum - val, 0);
                    pairs = (pairs + count) % MOD;
                }
                map.put(val, map.getOrDefault(val, 0) + 1);
            }
            return pairs;
        }

        @Test
        public void test(){
            int[] arr = {1,3,5,7,9};
            int i = countPairs(arr);
            System.out.println(i);
        }
}
