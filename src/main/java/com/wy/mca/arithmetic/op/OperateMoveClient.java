package com.wy.mca.arithmetic.op;

public class OperateMoveClient {

    public static void main(String[] args) {
        rightMove();
    }

    private static void leftMove(){

    }

    private static void rightMove(){
        int i=10;
        int j=10;
        int s = i + j >> 1;
        System.out.println("s-->" + s);

        int s2 = i + (j >> 1);
        System.out.println("s2-->" + s2);
    }
}
