package study.leetcode;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author xiachao
 * @date 2020/08/06
 */
public class Solution1047 {

    // 暴力 brute force，连续的全部删除
    public String removeDuplicates(String S) {
        char[] chars = S.toCharArray();

        boolean continueFlag = true;
        while (continueFlag) {
            continueFlag = false;

            int index = 0;
            char tmp = 1;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != 1) {
                    index = i;
                    tmp = chars[i];
                    break;
                }
            }

            if (tmp == 1) {
                break;
            }

            for (int i = index + 1; i < chars.length; i++) {
                if (chars[i] == 1) {
                    continue;
                }
                if (tmp == chars[i]) {
                    chars[i] = 1;
                    chars[index] = 1;
                    continueFlag = true;
                } else {
                    index = i;
                    tmp = chars[i];
                }
            }
        }

        int length = 0;
        for (char c : chars) {
            if (c != 1) {
                length++;
            }
        }
        if (length == 0) {
            return "";
        }

        char[] ret = new char[length];
        int i = 0;
        for (char c : chars) {
            if (c != 1) {
                ret[i++] = c;
            }
        }

        return new String(ret);
    }

    // 栈,连续的全部删除
    public String removeDuplicates1(String S) {
        char[] chars = S.toCharArray();

        Stack<Character> stack = new Stack<>();
        stack.push(chars[0]);

        char tmp = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == tmp) {
                continue;
            }
            if (stack.size() > 0 && chars[i] == stack.peek()) {
                tmp = stack.pop();
            } else {
                tmp = 1;
                stack.push(chars[i]);
            }
        }

        char[] ret = new char[stack.size()];
        for (int i = ret.length - 1; i >= 0; i--) {
            ret[i] = stack.pop();
        }
        return new String(ret);

        //return Arrays.stream(stack.toArray()).map(String::valueOf).collect(Collectors.joining());
    }

    // 栈，只成对删除
    public String removeDuplicates2(String S) {
        char[] chars = S.toCharArray();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (stack.size() > 0 && chars[i] == stack.peek()) {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }

        char[] ret = new char[stack.size()];
        for (int i = ret.length - 1; i >= 0; i--) {
            ret[i] = stack.pop();
        }
        return new String(ret);
    }

    public static void main(String[] args) {
        String s = "abbaca";

        System.out.println(new Solution1047().removeDuplicates(s));
        System.out.println(new Solution1047().removeDuplicates1(s));
        System.out.println(new Solution1047().removeDuplicates1(s));
    }


}
