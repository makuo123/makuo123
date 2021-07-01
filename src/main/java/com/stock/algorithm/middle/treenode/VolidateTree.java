package com.stock.algorithm.middle.treenode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 98. 验证二叉搜索树
 *
 * @Author mk
 * @Date 2021/7/1 9:30
 * @Version 1.0
 */
public class VolidateTree {

    /** 方法一: 递归 */
    public Boolean volidateTree(TreeNode treeNode){
        return compareData(treeNode);
    }

    public Boolean compareData(TreeNode treeNode){

        if (treeNode == null || treeNode.left == null || treeNode.right == null){
            return true;
        }

        if (treeNode.left.val > treeNode.val || treeNode.right.val < treeNode.val){
            return false;
        }

        compareData(treeNode.left);
        compareData(treeNode.right);

        return true;
    }

    @Test
    public void test(){
        TreeNode treeNode = new TreeNode(5,
                new TreeNode(3,new TreeNode(2), new TreeNode(4)),
                new TreeNode(6));

        //System.out.println(volidateTree(treeNode));

        Solution solution = new Solution();
        System.out.println(solution.isValidBST(treeNode));

    }


    /** 方法二：中序遍历
     思路和算法

     基于方法一中提及的性质，我们可以进一步知道二叉搜索树「中序遍历」得到的值构成的序列一定是升序的，这启示我们在中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可。如果均大于说明这个序列是升序的，整棵树是二叉搜索树，否则不是，下面的代码我们使用栈来模拟中序遍历的过程。

     可能由读者不知道中序遍历是什么，我们这里简单提及一下，中序遍历是二叉树的一种遍历方式，它先遍历左子树，再遍历根节点，最后遍历右子树。而我们二叉搜索树保证了左子树的节点的值均小于根节点的值，根节点的值均小于右子树的值，因此中序遍历以后得到的序列一定是升序序列。

     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    class Solution {
        public boolean isValidBST(TreeNode root) {
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            double inorder = -Double.MAX_VALUE;

            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
                if (root.val <= inorder) {
                    return false;
                }
                inorder = root.val;
                root = root.right;
            }
            return true;
        }
    }

}
