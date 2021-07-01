package com.stock.algorithm.middle.treenode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 *
 * 前序遍历,也叫先根遍历,遍历的顺序是,根,左子树,右子树
 * 中序遍历,也叫中跟遍历,顺序是 左子树,根,右子树
 * 后序遍历,也叫后跟遍历,遍历顺序,左子树,右子树,根
 * @Author mk
 * @Date 2021/6/30 14:18
 * @Version 1.0
 */
public class MiddleIteratorTree {

    public List<Integer> middleIteratorTreeNode(TreeNode treeNode){

        List<Integer> res = new ArrayList<>();

        index(treeNode, res);

        return res;
    }

    public void index(TreeNode root, List<Integer> res){

        if (root == null){
            return;
        }

        index(root.left, res);
        res.add(root.val);
        index(root.right, res);

    }

    @Test
    public void test(){
        TreeNode treeNode = new TreeNode(3, new TreeNode(2,new TreeNode(5), new TreeNode(9)), new TreeNode(1));
        List<Integer> integers = middleIteratorTreeNode(treeNode);
        System.out.println(integers);
    }
}
