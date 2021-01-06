package com.algorithm;

public class Test1 {

    public static void main(String[] args) {
        int[] a=new int[]{1,2,3,2,3};
        int sign=0;
        for(int i=0;i<a.length;i++){
            System.out.println(Integer.toBinaryString( a[i]));
            sign^=a[i];

            System.out.println("*"+Integer.toBinaryString( sign));

        }
        System.out.println(sign);
    }
}
