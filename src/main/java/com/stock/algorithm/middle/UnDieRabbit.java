package com.stock.algorithm.middle;

/**
 * 不死兔问题
 *
 * @Author mk
 * @Date 2021/5/28 10:17
 * @Version 1.0
 */
public class UnDieRabbit {

    public static void main(String[] args) {
        //getCount();
//        System.out.println(digui(30));
        /** 当除数是2的次幂的时候，位与运算（除数-1）和取模预算结果一致 */
        System.out.println(8 & 15);
        System.out.println(8 % 16);

        /** 至少执行一次 */
        do {
            System.out.println(11);
        }while (false);

    }

    private static long digui(long n){
        if (n == 1||n==2){
            return 1;
        }

        return digui(n - 2) + digui(n - 1);
    }

    private static void getCount() {
        int f1 = 1, f2 = 1, f;
        int M = 30;
        System.out.println(1);
        System.out.println(2);
        for (int i = 3; i < M; i++) {
            f = f2;
            f2 = f1 + f2;
            f1 = f;
            System.out.println(f2);
        }
    }

}
