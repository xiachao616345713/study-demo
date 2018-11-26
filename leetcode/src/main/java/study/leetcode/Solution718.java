package study.leetcode;

/**
 * <p>
 * 718. Maximum Length of Repeated Subarray Given two integer arrays A and B, return the maximum length of an subarray that appears in both
 * arrays.
 *
 * <a href="https://leetcode.com/problems/maximum-length-of-repeated-subarray/"/>
 * </p>
 *
 * @author chao
 * @since 2018-11-20
 */
public class Solution718 {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 2, 1, 2, 3, 2, 1, 4, 7, 7, 3, 2, 1, 4, 7, 8, 5, 0, 3, 2, 1, 8};
        int[] b = new int[]{3, 2, 1, 4, 7, 8};
        System.out.println(findLength(a, b));
    }

//    /**
//     * max 保存最大值，相等(1加后一个值)，不相等(0和max对比)
//     */
//    public static int findLength(int[] a, int[] b) {
//        int m = a.length, n = b.length;
//        if (m == 0 || n == 0) {
//            return 0;
//        }
//        int[][] dp = new int[m + 1][n + 1];
//        int max = 0;
//        for (int i = m - 1; i >= 0; i--) {
//            for (int j = n - 1; j >= 0; j--) {
//                max = Math.max(max, dp[i][j] = a[i] == b[j] ? 1 + dp[i + 1][j + 1] : 0);
//            }
//        }
//        return max;
//    }

    public static int findLength(int[] A, int[] B) {
        boolean[][] array = new boolean[A.length][B.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                array[i][j] = A[i] == B[j];
            }
        }
        return findx(array, 0, 0, true, true);
    }

    /**
     * 数组斜线中连续true长度
     */
    private static int findx(boolean[][] array, int x, int y, boolean ax, boolean ay) {
        int a = 0;
        if (ax && array.length > x + 1) {
            a = findx(array, x + 1, y, true, false);
        }
        int b = 0;
        if (ay && array[0].length > y + 1) {
            b = findx(array, x, y + 1, false, true);
        }

        int c = 0;
        int max = 0;
        for (int i = x,j = y; i < array.length && j < array[0].length; i++,j++) {
            if (array[i][j]) {
                c += 1;
            } else {
                max = Math.max(max, c);
                c = 0;
            }
        }
        c = Math.max(max, c);
        return Math.max(a, Math.max(b, c));
    }
}
