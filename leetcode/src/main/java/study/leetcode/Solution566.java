package study.leetcode;

import java.util.Arrays;

/**
 * <a href = "https://leetcode.com/problems/reshape-the-matrix/">Reshape the Matrix</a>
 * <p>
 * In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different
 * size but keep its original data.
 *
 * You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row
 * number and column number of the wanted reshaped matrix, respectively.
 *
 * The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order
 * as they were.
 *
 * If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise,
 * output the original matrix.
 * </p>
 *
 * @author xiachao
 * @date 2020/12/05
 */
public class Solution566 {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int rows = nums.length;
        int columns = 0;
        if (rows > 0) {
            columns = nums[0].length;
        }
        // illegal
        if (rows * columns != r * c) {
            return nums;
        }
        int[][] ret = new int[r][c];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                index = i * columns + j;
                ret[index / c][index % c] = nums[i][j];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] nums = {{1, 2, 4, 5, 10, 11}, {3, 4, 6, 8, 11, 2}, {3, 4, 6, 8, 11, 3}, {3, 4, 6, 8, 11, 5}};

        System.out.println(Arrays.deepToString(new Solution566().matrixReshape(nums, 3, 8)));
        System.out.println(Arrays.deepToString(new Solution566().matrixReshape(nums, 3, 5)));

    }

}
