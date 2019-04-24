package study.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 将数字的每一个数字平方求和，如果等于1就是happy，如果不是1无限循环下去
 *
 * <a href="https://leetcode.com/problems/happy-number/">Happy Number</a>
 *
 *
 * @author chao
 * @since 2019-04-21
 */
public class Solution202 {


    public static void main(String[] args) {
        System.out.println(new Solution202().isHappy(21));
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        String str;
        int temp;
        while (true) {
            str = String.valueOf(n);
            set.add(n);
            n = 0;
            for (int i = 0; i < str.length(); i++) {
                temp = (int) str.charAt(i) - 48;
                n += temp * temp;
            }
            if (n == 1) {
                return true;
            }
            if (set.contains(n)) {
                return false;
            }
        }

    }
}
