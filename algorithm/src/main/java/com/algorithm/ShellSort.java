package com.algorithm;

/**
 * 希尔排序法
 */
public class ShellSort {
    static final int SIZE = 6;

    /**
     * 希尔排序法
     */
    public static void shellSort(int[] a) {
        int i, j, h;
        int r, temp;
        int x = 0;

        for (r = a.length / 2; r >= 1; r /= 2) {
            for (i = r; i < a.length; i++) {
                temp = a[i];
                j = i - r;
                while (j >= 0 && temp < a[j]) {
                    a[j+r] = a[j];
                    j -= r;
                }
                a[j + r] = temp;
            }
            x++;
            System.out.print("第" + x + "步排序结果：");
            for (h = 0; h < a.length; h++) {
                System.out.print(a[h] + "\t");
            }
            System.out.print("\n");
        }
    }


    public static void main(String[] args) {
        int[] shuzu = new int[SIZE];
        int i;

//        for (i = 0; i < SIZE; i++) {
//            shuzu[i] = (int) (100 + Math.random() * (100 + 1));
//        }

        shuzu = new int[]{127, 118, 105, 101, 112, 100};
        System.out.print("排序前的数组为：\n");
        for (i = 0; i < SIZE; i++) {
            System.out.print(shuzu[i] + "\t");
        }
        System.out.print("\n");
        shellSort(shuzu);
        System.out.print("排序后的数组为：\n");
        for (i = 0; i < SIZE; i++) {
            System.out.print(shuzu[i] + "\t");
        }
        System.out.print("\n");
    }
}