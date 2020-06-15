package study.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 * @author chao
 * @see <a href="https://leetcode.com/problems/all-possible-full-binary-trees/">All Possible Full Binary Trees</a>
 * @since 2019-03-22
 */
public class Solution894 {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int deep(TreeNode treeNode) {
        return 0;
    }


    public void consoleTree (TreeNode treeNode, Map<Integer, List<Integer>> map, Integer deep){
        List<Integer> list = map.get(deep);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(treeNode.val);
        map.put(deep, list);
        consoleTree(treeNode.left, map, deep + 1);
        consoleTree(treeNode.right, map, deep + 1);
    }

    /**
     * 1 <= N <= 20
     */
    public List<TreeNode> allPossibleFBT(int N) {
        return null;
    }

    public static void main(String[] args) {

    }
}
