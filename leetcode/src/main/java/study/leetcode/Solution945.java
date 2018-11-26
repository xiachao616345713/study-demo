package study.leetcode;


import java.util.Arrays;

/**
 * <p>
 * 945. Minimum Increment to Make Array Unique
 *
 * Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
 *
 * Return the least number of moves to make every value in A unique.
 *
 * <a href="https://leetcode.com/problems/minimum-increment-to-make-array-unique/"/>
 * </p>
 *
 * @author chao
 * @since 2018-11-26
 */
public class Solution945 {

    public static void main(String[] args) {
        Solution945 solution = new Solution945();
        //int[] A = new int[]{3,2,1,2,3,1,3,2,1,7,3,5,4};
        int[] A = new int[]{2,2,4,4,4,5,7,10,10,12,13,13,13,14,14,14,14,16,17};

        System.out.println(solution.minIncrementForUnique(A));
    }

    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int res = 0, need = 0;
        for (int a : A) {
            res += Math.max(need - a, 0);
            need = Math.max(a, need) + 1;
        }
        return res;
    }

}
