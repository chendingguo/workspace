package com.algorithm;

import java.util.LinkedList;

/**
 * Binary search tree. Internal representation
 * <p>
 * Like any other dynamic data structure, BST requires storing of some
 * additional auxiliary data, in order to keep its structure. Each node of
 * binary tree contains the following information:
 * <p>
 * a value (user's data); a link to the left child (auxiliary data); a link to
 * the right child (auxiliary data). Depending on the size of user data, memory
 * overhead may vary, but in general it is quite reasonable. In some
 * implementations, node may store a link to the parent, but it depends on
 * algorithm, programmer want to apply to BST. For basic operations, like
 * addition, removal and search a link to the parent is not necessary. It is
 * needed in order to implement iterator
 *
 * @author arisupply
 */


public class BinaryNode {
    private Object data;
    private BinaryNode left;
    private BinaryNode right;

    public BinaryNode() {
    }

    public BinaryNode(Object data, BinaryNode left, BinaryNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }


    public BinaryNode createNode() {
        BinaryNode node = new BinaryNode("1", null, null);
        BinaryNode left2 = new BinaryNode("2", null, null);
        BinaryNode left3 = new BinaryNode("3", null, null);
        BinaryNode left4 = new BinaryNode("4", null, null);
        BinaryNode left5 = new BinaryNode("5", null, null);
        BinaryNode left6 = new BinaryNode("6", null, null);
        node.setLeft(left2);
        left2.setLeft(left4);
        left2.setRight(left6);
        node.setRight(left3);
        left3.setRight(left5);
        return node;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }


    /**
     * 二叉树的层序遍历 借助于队列来实现 借助队列的先进先出的特性
     * <p>
     * 首先将根节点入队列 然后遍历队列。
     * 首先将根节点打印出来，接着判断左节点是否为空 不为空则加入队列
     *
     * @param node
     */
    public void levelIterator(BinaryNode node) {
        LinkedList<BinaryNode> queue = new LinkedList<>();

        //先将根节点入队
        queue.offer(node);
        BinaryNode current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            System.out.print(current.data + "--->");

            if (current.getLeft() != null) {
                queue.offer(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.offer(current.getRight());
            }
        }
    }

    public void preOrder(BinaryNode node) {
        System.out.print(node.data + "\t");
        if (node.getLeft() != null) {
            preOrder(node.getLeft());
        }
        if (node.getRight() != null) {
            preOrder(node.getRight());
        }
    }

    public void middleOrder(BinaryNode node) {

        if (node.getLeft() != null) {
            middleOrder(node.getLeft());
        }
        System.out.print(node.data + "\t");
        if (node.getRight() != null) {
            middleOrder(node.getRight());
        }
    }

    public void postOrder(BinaryNode node) {

        if (node.getLeft() != null) {
            postOrder(node.getLeft());
        }
        if (node.getRight() != null) {
            postOrder(node.getRight());
        }
        System.out.print(node.data + "\t");

    }

    public static void main(String[] args) {
        BinaryNode binaryNode = new BinaryNode();
        BinaryNode node = binaryNode.createNode();
        //binaryNode.levelIterator(node);
        System.out.println("先序遍历");
        binaryNode.preOrder(node);
        System.out.println("\n中序遍历");
        binaryNode.middleOrder(node);
        System.out.println("\n后序遍历");
        binaryNode.postOrder(node);


    }
}