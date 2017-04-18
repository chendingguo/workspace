package com.algorithm;

/**
 * 二分法的基本思想如下：
 * 假设数据是按升序排序的,对于给定值x,从序列的中间位置开始比较,如果当前位置值等于x,则查找成功；若x小于当前位置值,则在数列的前半段中查找；
 * 若x大于当前位置值则在数列的后半段中继续查找,直到找到为止.
 * 由于是数组是预先排序好的,所以可以采用折半查询的方式,每次抛掉待查询部分的一半 这样,长度为N的数组,只需要log2N次查询即可,2是对数的底.
 * 例如,长度为7的数组,最多只需要3次就可以找到
 * O(log2n)只是表示是log2N同一数量级,因为有个取整的问题,而且也有可能在查询过程中就已经找到（也就是某个折半查询点正好是待查询数据）,
 * 这样O(log2n)就是一个上限
 * 
 * @author 蓝桥
 *
 */
public class BinarySearch {

	/**
	 * 非递归实现
	 * 
	 * @param array
	 * @param a
	 * @return
	 */
	public static int biSearch(int[] array, int a) {
		int lo = 0;
		int hi = array.length - 1;
		int mid;
		while (lo <= hi) {
			mid = (lo + hi) / 2;
			if (array[mid] == a) {
				return mid + 1;
			} else if (array[mid] < a) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return -1;
	}

	/**
	 * 递归实现
	 * 
	 * @param array
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static int sort(int[] array, int a, int lo, int hi) {
		if (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (a == array[mid]) {
				return mid + 1;
			} else if (a > array[mid]) {
				return sort(array, a, mid + 1, hi);
			} else {
				return sort(array, a, lo, mid - 1);
			}
		}
		return -1;
	}

}
