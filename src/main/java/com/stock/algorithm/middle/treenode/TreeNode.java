package com.stock.algorithm.middle.treenode;

/**
 * @Author mk
 * @Date 2021/6/30 14:04
 * @Version 1.0
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
