package com.wy.mca.arithmetic.op;

/**
 * 操作符-亦或运算 也叫做无进位相加
 * 1 特性：相同为0，不同为1
 *   1.1 0^n = n
 *   1.2 n^n = 0
 *   1.3 ~n+1 = -n
 *
 * @author wangyong
 */
public class OperatorOrClient {

    public static void main(String[] args) {
        System.out.println("-------利用亦或的特性交换a b-------");
        swap();

        System.out.println("-------数组中有一种数出现了奇数次，其他数出现了偶数次，求这个数-------");
        findOneOdd();

        System.out.println("--------提取int类型最右侧的1----------");
        findRightOne();

        System.out.println("--------一个数组中arr有两个数出现了奇数次，其他数出现了偶数次，打印这两种数----------");
        findTwoOdd();
    }

    /**
     * 利用亦或的特性交换a b
     */
    private static void swap(){
        int a = 1;
        int b = 2;

        System.out.println("交换前：a->" + a + "; b->" + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("交换后：a->" + a + "; b->" + b);
    }

    /**
     * 数组中有一种数出现了奇数次，其他数出现了偶数次，求这个数
     */
    private static void findOneOdd(){
        int[] arr = {1,1,2,2,3,4,4,5,5};
        int e = 0;
        for (int i=0; i<arr.length; i++){
            e = e ^ arr[i];
        }
        System.out.println("findOne-->" + e);
    }

    /**
     * 提取int类型最右侧的1
     */
    private static void findRightOne(){
        //1010
        int a = 10;

        //0101
        //~a;

        //0110
        //~a+1

        int result = a&(~a+1);
        System.out.println("a^(~a+1)==>" + result);

        //演变结果
        int result2 = a & (-a);
        System.out.println("a ^ (-a)==>" + result2);
    }

    /**
     * 一个数组中arr有两个数出现了奇数次，其他数出现了偶数次，打印这两种数
     */
    private static void findTwoOdd(){
        int[] arr = {1,1,2,2,3,4,4,5,5,7};
        int e = 0;
        for (int i=0; i<arr.length; i++){
            e = e ^ arr[i];
        }
        //此时e = t1 ^ t2
        int rightOne = e & (-e);
        int t1 = 0;
        for (int i=0; i<arr.length; i++){
            if ((rightOne & arr[i]) > 0){
                t1 = t1^arr[i];
            }
        }
        int t2 = e ^ t1;
        System.out.println(String.format("Two num is:%s,%s",t1,t2));
    }

}
