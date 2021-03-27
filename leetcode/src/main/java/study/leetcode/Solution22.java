package study.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * <a href = "https://leetcode.com/problems/generate-parentheses/">Generate Parentheses</a>
 *
 * @author xiachao
 * @date 2021/03/27
 */
public class Solution22 {

    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        char[] chars = new char[2 * n];
        chars[0] = '(';
        recursion(ret, 2 * n, chars, 1, 1, 0);

        return ret;
    }

    private void recursion(List<String> ret, int n, char[] chars, int index, int left, int right) {
        if (index == n) {
            if (isPair(chars)) {
                ret.add(new String(chars));
            }
            return;
        }
        if (left > n || right > n) {
            return;
        }
        chars[index] = '(';
        recursion(ret, n, chars, index + 1,  left + 1, right);

        chars[index] = ')';
        recursion(ret, n, chars, index + 1, left, right + 1);
    }

    private boolean isPair(char[] array) {
        Deque<Integer> stack = new ArrayDeque<>(array.length);
        for (int value : array) {
            if (value == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.removeLast();
            } else {
                stack.addLast(value);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution22().generateParenthesis(3));
    }

}
