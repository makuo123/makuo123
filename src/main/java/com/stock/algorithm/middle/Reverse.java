package com.stock.algorithm.middle;

/**
 * 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *  
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 *
 * 输入：x = 0
 * 输出：0
 * 提示：-231 <= x <= 231 - 1
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Reverse {

    /**
     * 取模截取最后一个数字，取余判断x是否为个位数
     * @param x
     * @return
     */
    public static int reverse(int x){
        int res = 0 , mod = 0;
        while (x != 0){
            mod = x % 10;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE){
                return 0;
            }
            res = res*10 + mod;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        int reverse = reverse(321);
        System.out.println(reverse);
    }
}
