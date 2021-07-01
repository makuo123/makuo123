package com.stock.algorithm.middle;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 *
 * @Author mk
 * @Date 2021/6/29 11:50
 * @Version 1.0
 */
public class MaxUnRepeatSubStr {

    /** 「滑动窗口」来解决这个问题了：

     我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为上文中的 r_kr
     k
     ；

     在每一步的操作中，我们会将左指针向右移动一格，表示 我们开始枚举下一个字符作为起始位置，然后我们可以不断地向右移动右指针，但需要保证这两个指针对应的子串中没有重复的字符。在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。我们记录下这个子串的长度；

     在枚举结束后，我们找到的最长的子串的长度即为答案。

     判断重复字符

     在上面的流程中，我们还需要使用一种数据结构来判断 是否有重复的字符，常用的数据结构为哈希集合（即 C++ 中的 std::unordered_set，Java 中的 HashSet，Python 中的 set, JavaScript 中的 Set）。在左指针向右移动的时候，我们从哈希集合中移除一个字符，在右指针向右移动的时候，我们往哈希集合中添加一个字符。

     至此，我们就完美解决了本题。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public int maxSubStr(String str){
        int rk = -1,max = 0;
        Set<Character> characters = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {

            if (i != 0){
                characters.remove(str.charAt(i-1));
            }

            while (rk + 1 < str.length() && !characters.contains(str.charAt(rk + 1))){
                characters.add(str.charAt(rk + 1));
                ++rk;
            }

           max = Math.max(max, rk - i + 1);
        }
        return max;
    }

    @Test
    public void test(){
        System.out.println(maxSubStr("abbcde"));
    }
}

