package study.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>
 * 718. Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * </p>
 *
 * @author chao
 * @since 2018-11-20
 * @see <a href="https://leetcode.com/problems/remove-invalid-parentheses/">Remove Invalid Parentheses</a>
 */
public class Solution301 {

    public static void main(String[] args) {
        String str = "()())()";
        List<String> list = removeInvalidParentheses(str);
        list.forEach(System.out::println);
    }

    public static List<String> removeInvalidParentheses(String s) {
        List<String> list = new ArrayList<>();
        return list;
    }
}
