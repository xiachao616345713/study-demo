package study.leetcode;

/**
 * <a href = "https://leetcode.com/problems/binary-tree-pruning/">Binary Tree Pruning</a>
 *
 * <p>
 * We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
 * <p>
 * Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 * <p>
 * 移除全部子节点中不包含1的节点
 * <p>
 *
 * @author xiachao
 * @date 2020/12/28
 */
public class Solution814 {

    public TreeNode pruneTree(TreeNode root) {
        boolean flag = pruneRecurse(root);
        if (flag) {
            return null;
        }
        return root;
    }

    private boolean pruneRecurse(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = pruneRecurse(root.left);
        if (left) {
            root.left = null;
        }
        boolean right = pruneRecurse(root.right);
        if (right) {
            root.right = null;
        }
        return left && right && root.val == 0;
    }


    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}
