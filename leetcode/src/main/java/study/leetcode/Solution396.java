package study.leetcode;

/**
 * max rotate
 *
 * @author xiachao
 * @date 2020/12/22
 */
public class Solution396 {
    /**
     * A = [4, 3, 2, 6]
     *
     * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
     * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
     * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
     * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
     *
     * So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
     */
    /**
     * A = [4, 3, 2, 6]
     *
     * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
     * F(1) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
     * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
     * F(3) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
     */
    public int maxRotateFunction(int[] A) {
        // f(n-1) - f(n) = sum(A) - length * A(n-1)
        /*
         * f(n) = f(n-1) + length * A(n-1) - sum(A)
         * = f(n-2) + length*(A(n-1)+A(n-2)) - 2*sum(A)
         * = f(0) + length*sum(A[n-1..0]) - n*sum(A)
         */
        int f0 = 0;// f0
        int length = A.length;
        for (int i = 1; i < length; i++) {
            f0 += i * A[i];
            A[i] = A[i] + A[i - 1];
        }

        int max = f0;
        for (int i = 1; i < length; i++) {
            max = Math.max(max, f0 + length * A[i - 1] - i * A[length - 1]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] a = new int[] {4, 3, 2, 6};
        System.out.println(new Solution396().maxRotateFunction(a));
    }

}
