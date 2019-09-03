package com.algorithm;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	public static int search(int[] array, int a, int lo, int hi) {
		if (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (a == array[mid]) {
				return mid + 1;
			} else if (a > array[mid]) {
				return search(array, a, mid + 1, hi);
			} else {
				return search(array, a, lo, mid - 1);
			}
		}
		return -1;
	}


//	public List<List<Integer>> levelOrder(TreeNode root) {
//		Queue<TreeNode> queue=new LinkedList<TreeNode>();
//		List<List<Integer>> wrapList=new LinkedList<>();
//		if(root==null) return wrapList;
//		//1.先将树的根结点放入到队列中
//		queue.offer(root);
//		while(!queue.isEmpty()){
//			//2.计算每层的结点数目，也就是队列中的元素
//			int levelnums=queue.size();
//			List<Integer> subList=new LinkedList<>(); //存储每一层的结点的值val
//			//3.遍历一层的所有结点，把此层的结点的值保存下来(这时也会把遍历的结点踢出去)，同时把下一层的结点放入队列
//			for(int i=0;i<levelnums;i++){
//				if(queue.peek().left!=null) queue.offer(queue.peek().left);  //peek返回队列的头元素，但不删除
//				if(queue.peek().right!=null) queue.offer(queue.peek().right);
//				subList.add(queue.poll().val);                          //poll()返回队列的头元素，并且删除该元素
//			}
//			//4.将每一层的结点的值加入到集合中
//			wrapList.add(subList);
//		}
//		return wrapList;
//
//	}

}
