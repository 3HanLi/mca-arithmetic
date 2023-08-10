package com.wy.mca.arithmetic.dac;

/**
 * 分治算法-汉诺塔
 * @author wangyong01
 */
public class Hanoitower {

    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    private static void hanoiTower(int num, char a, char b, char c){
        //1 一个盘子：A -> C
        if (num == 1){
            System.out.println("第1个盘子从" + a + "移动到" + c);
        }else {
            //2.1 将A上面的n-1个盘当作一个盘：A -> B
            hanoiTower(num-1,a,c,b);
            //2.2 A->C
            System.out.println("第" + num + "个盘子从" + a + "移动到" + c);
            //2.3 将B上面的num-1个盘移动到C：B -> C
            hanoiTower(num-1,b,a,c);
        }
    }
}
