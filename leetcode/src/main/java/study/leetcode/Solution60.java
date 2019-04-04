package study.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Permutation Sequence
 *
 * <p>The set [1,2,3,...,n] contains a total of n! unique permutations.</p>
 * <p>Given n and k, return the kth permutation sequence.</p>
 *
 * @author chao
 * @since 2019-04-04
 * @see <a href="https://leetcode.com/problems/permutation-sequence/">Permutation Sequence</a>
 */
public class Solution60 {

    public static void main(String[] args) {
        Solution60 solution = new Solution60();
        for (int i = 1; i < 24; i++) {
            System.out.println(solution.getPermutation(4, i));
        }
    }

    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            list.add(i);
            factorial *= i;
        }
        int temp;
        for (int i = n; i >= 1; i--) {
            factorial = factorial / i;
            temp = k / factorial;
            k = k % factorial;
            if (k == 0) {
                sb.append(list.remove(temp - 1));
                break;
            } else {
                sb.append(list.remove(temp));
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
