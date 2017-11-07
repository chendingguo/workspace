package com.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 贪心算法思想
 * 贪心算法总是做出在当前看来做好的选择。也就是说贪心算法并不从整体最后考虑，他做出的选择只是局部最优选择。他所做出的仅是在某种意义上的局部最优解。贪心算法不是对所有问题都能得到整体最优解，但对范围相当广泛的许多问题他能产生整体最优解或者是整体最优解的近似解。
 * 
 * 1.算法思路
 * 
 * 贪心算法是一种不追求最优解，只希望得到较为满意解的方法。贪心算法一般可以快速得到满意的解，因为它省去了为找最优姐要穷尽所有肯呢个而必须耗费大量时间。贪婪（心）算法是一种改进了的分级处理方法。其核心是根据题意选取一种量度标准。然后将这多个输入排成这种量度标准所要求的顺序，按这种顺序一次输入一个量。如果这个输入和当前已构成在这种量度意义下的部分最佳解加在一起不能产生一个可行解，则不把此输入加到这部分解中。这种能够得到某种量度意义下最优解的分级处理方法称为贪婪算法。
 * 
 * 对于一个给定的问题，往往可能有好几种量度标准。初看起来，这些量度标准似乎都是可取的，但实际上，用其中的大多数量度标准作贪婪处理所得到该量度意义下的最优解并不是问题的最优解，而是次优解。因此，选择能产生问题最优解的最优量度标准是使用贪婪算法的核心。
 * 
 * 一般情况下，要选出最优量度标准并不是一件容易的事，但对某问题能选择出最优量度标准后，用贪婪算法求解则特别有效。最优解可以通过一系列局部最优的选择即贪婪选择来达到，根据当前状态做出在当前看来是最好的选择，即局部最优解选择，然后再去解做出这个选择后产生的相应的子问题。每做一次贪婪选择就将所求问题简化为一个规模更小的子问题，最终可得到问题的一个整体最优解。
 * 
 * 2.基本特性
 * 
 * 从问题的某一个初始解除发逐步逼近给定的目标，以尽可能快的求得更好的解。当达到算法中的某一步不能再继续前进时就停止算法，给出近似解。
 */

public class GreedyDemo {

	public static int MAX = 10;
	public static double Value[] = { 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10 };
	public static int num[] = new int[MAX];

	public static void main(String[] args) {
		
		System.out.println("请输入要换的数值");
		Scanner scanner = new Scanner(System.in);
		double a = scanner.nextDouble();
		scanner.close();
		conver(a * 100);
		System.out.println("找零");
		for (int i = 0; i < MAX; i++) {
			if (num[i] > 0) {
				System.out.println("面值" + Value[i] / 100 + "一共需要 " + num[i] + "张");
			}

		}
	}

	private static void conver(double a) {
		int i;
		for (i = 0; i < MAX; i++) {
			if (a > Value[i])
				break;
		}

		while (a > 0 && i < MAX) {
			if (a >= Value[i]) {
				a -= Value[i];
				num[i]++;
			} else if (a < 10 && a >= 5) {
				num[MAX - 1]++;
				break;
			} else
				i++;
		}
	}
}
