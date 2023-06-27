package com.stock.algorithm.slipwindows;

import java.util.HashMap;

/**
 * @Description
 * @Author makuo
 * @Date 2023/4/7 13:42
 **/
public class UnRepeatStringLength {
    public static int stringLength(String s) {
        // 滑动窗口
        if (s.length() == 0) return 0;
        // 通过hash记录字符是否重复及字符所在字符串的元素索引位置
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        // 当前最大重复字符位位置
        Integer left = 0;
        Integer max = 0;
        // 循环字符串
        for (int i = 0; i < s.length(); i++) {
            // 字符已存在，则获取当前字符索引位置
            if (map.containsKey(s.charAt(i))){
                Integer existIndex = map.get(s.charAt(i)) + 1;
                left = Math.max(left, existIndex);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(UnRepeatStringLength.stringLength("UnRepeUatStrSiSngLength"));
    }
}
