package study.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * <p>
 * 301. Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * </p>
 *
 * @author chao
 * @since 2018-11-20
 * @see <a href="https://leetcode.com/problems/remove-invalid-parentheses/">Remove Invalid Parentheses</a>
 */
public class Solution301 {

    public static void main(String[] args) {
        //String str = "()())()";
        //String str = "(a)())()";
        String str = ")(";
        List<String> list = removeInvalidParentheses(str);
        list.forEach(System.out::println);
    }

    public static List<String> removeInvalidParentheses(String s) {
        // 求出1个结果
        String result = remove(s);
        // 删除无效数量
        //int minDelete = str.length() - result.length();
        int strLeft = 0, strRight = 0, resultLeft = 0, resultRight = 0;
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '(') {
                strLeft++;
            } else if (c == ')') {
                strRight++;
            }
        }
        for (int i = 0; i < result.length(); i++) {
            c = result.charAt(i);
            if (c == '(') {
                resultLeft++;
            } else if (c == ')') {
                resultRight++;
            }
        }

        List<String> list = new ArrayList<>();
        recursion(list, s, "", 0, 0, 0, strLeft - resultLeft, strRight - resultRight);

        Iterator<String> it = list.iterator();
        String item;
        while (it.hasNext()) {
            item = it.next();
            if (remove(item).length() != item.length()) {
                it.remove();
            }
        }
        return list;
    }

    /**
     * 删除无效的字符串
     */
    private static String remove(String s) {
        if (s.length() <= 0) {
            return "";
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder ret = new StringBuilder();
        Character c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (stack.size() == 0) {
                if (c == '(') {
                    stack.push(c);
                } else if (c != ')') {
                    ret.append(c);
                }
            } else {
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    Character temp;
                    StringBuilder tempS = new StringBuilder();
                    while (stack.size() > 0) {
                        temp = stack.pop();
                        if (temp == '(') {
                            tempS.insert(0, temp).append(c);
                            break;
                        } else {
                            tempS.insert(0, temp);
                        }
                    }
                    ret.append(tempS);
                } else {
                    Character temp = stack.peek();
                    if (temp == ')') {
                        ret.append(c);
                    } else {
                        stack.push(c);
                    }
                }
            }
        }
        Character temp;
        StringBuilder tempS = new StringBuilder();
        while (stack.size() > 0) {
            temp = stack.pop();
            if (temp != '(' && temp != ')') {
                tempS.insert(0, temp);
            }
        }
        ret.append(tempS);
        return ret.toString();
    }

    /**
     * 可能结果
     *
     * @param list 结果
     * @param s 原字符串
     * @param result 递归中结果
     * @param index index
     * @param deleteLeft 左括号删除数
     * @param deleteRight  右括号删除数
     * @param minDeleteLeft 左括号最小删除数
     * @param minDeleteRight 右括号最小删除数
     */
    private static void recursion(List<String> list, String s, String result, int index, int deleteLeft, int deleteRight, int minDeleteLeft, int minDeleteRight) {
        if (deleteLeft > minDeleteLeft) return;
        if (deleteRight > minDeleteRight) return;
        // 剩余字符串小于删除数
        if (minDeleteLeft + minDeleteRight - deleteLeft - deleteRight > s.length() - index) return;
        if (index >= s.length()) {
            if (deleteLeft == minDeleteLeft && deleteRight == minDeleteRight) {
                if (!list.contains(result)) {
                    list.add(result);
                }
            }
        } else {
            Character c;
            int i;
            StringBuilder resultBuilder = new StringBuilder(result);
            for (i = index; i < s.length(); i++) {
                c = s.charAt(i);
                if (c == '(') {
                    recursion(list, s, resultBuilder.toString() + c, i + 1, deleteLeft, deleteRight, minDeleteLeft, minDeleteRight);
                    deleteLeft++;
//                    recursion(list, s, result, i + 1, deleteLeft + 1, deleteRight, minDeleteLeft, minDeleteRight);
//                    break;
                } else if (c == ')') {
                    recursion(list, s, resultBuilder.toString() + c, i + 1, deleteLeft, deleteRight, minDeleteLeft, minDeleteRight);
                    deleteRight++;
//                    recursion(list, s, result, i + 1, deleteLeft, deleteRight + 1, minDeleteLeft, minDeleteRight);
//                    break;
                } else {
                    resultBuilder.append(c);
                }
            }
            result = resultBuilder.toString();
            if (i == s.length() && deleteLeft == minDeleteLeft && deleteRight == minDeleteRight) {
                if (!list.contains(result)) {
                    list.add(result);
                }
            }
        }
    }
}
