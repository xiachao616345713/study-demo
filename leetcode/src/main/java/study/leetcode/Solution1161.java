package study.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层相加，相加结果最大的是哪个一层
 * <a href="https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/>maximum-level-sum-of-a-binary-tree</a>
 * <p>
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * <p>
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 *
 * @author xiachao
 * @date 2020/09/23
 */
public class Solution1161 {

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

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    static class Solution {

        public int maxLevelSum(TreeNode root) {
            TreeNode empty = new TreeNode();

            int maxVal = root.val;
            int maxLevel = 1;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            queue.offer(empty);
            TreeNode tmp;
            int curVal = 0;
            int curLevel = 1;
            while (!queue.isEmpty()) {
                tmp = queue.poll();
                curVal += tmp.getVal();
                if (tmp != empty) {
                    if (tmp.getLeft() != null) {
                        queue.offer(tmp.getLeft());
                    }
                    if (tmp.getRight() != null) {
                        queue.offer(tmp.getRight());
                    }
                } else {
                    if (queue.isEmpty()) {
                        break;
                    }
                    queue.offer(empty);
                    if (maxVal < curVal) {
                        maxVal = curVal;
                        maxLevel = curLevel;
                    }

                    curVal = 0;
                    curLevel++;
                }
            }

            return maxLevel;
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1,
            new TreeNode(7, new TreeNode(7), new TreeNode(-8)),
            new TreeNode(0));

        System.out.println(new Solution().maxLevelSum(node));
    }

}
