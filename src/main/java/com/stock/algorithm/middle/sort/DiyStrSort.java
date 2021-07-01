package com.stock.algorithm.middle.sort;

import org.junit.Test;

/**
 * 791. 自定义字符串排序
 *
 * @Author mk
 * @Date 2021/7/1 14:37
 * @Version 1.0
 */
public class DiyStrSort {

    /** 方法一：统计字符数量重新构造字符串【通过】
     思路

     首先找出在 T 中出现的所有的 S 的元素，并且将这些元素按照 S 中出现的相对顺序排序，然后把 T 中出现的但不在 S 中的元素添加到排好序的字符串中，就得到了我们想要的结果。

     在将 T 中出现的但不在 S 中的元素添加到字符串时，无序关注顺序，因为这些元素并没有在 S 中出现，不需要满足排序关系。

     算法

     一种巧妙的实现方法是统计 T 中每个字符出现的次数，把结果存储在数组 count 中，count[char] 表示字符 char 出现的次数。然后把在 S 中出现的字符按照在 S 中的相对顺序排列，剩余字符添加到当前字符串的后面，最终排好序的字符串顺序为 S + (未在 S 中出现的字符)。

     作者：LeetCode
     链接：https://leetcode-cn.com/problems/custom-sort-string/solution/zi-ding-yi-zi-fu-chuan-pai-xu-by-leetcode/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public String customSortString(String S, String T) {
        // count[char] = the number of occurrences of 'char' in T.
        // This is offset so that count[0] = occurrences of 'a', etc.
        // 'count' represents the current state of characters
        // (with multiplicity) we need to write to our answer.
        int[] count = new int[26];
        for (char c : T.toCharArray())
            count[c - 'a']++;

        // ans will be our final answer.  We use StringBuilder to join
        // the answer so that we more efficiently calculate a
        // concatenation of strings.
        StringBuilder ans = new StringBuilder();

        // Write all characters that occur in S, in the order of S.
        for (char c : S.toCharArray()) {
            for (int i = 0; i < count[c - 'a']; ++i)
                ans.append(c);
            // Setting count[char] to zero to denote that we do
            // not need to write 'char' into our answer anymore.
            count[c - 'a'] = 0;
        }

        // Write all remaining characters that don't occur in S.
        // That information is specified by 'count'.
        for (char c = 'a'; c <= 'z'; ++c)
            for (int i = 0; i < count[c - 'a']; ++i)
                ans.append(c);

        return ans.toString();
    }

    @Test
    public void test() {
        System.out.println(customSortString("abc", "dcgab"));
    }
}