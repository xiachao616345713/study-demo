package study.leetcode;

import java.util.Stack;

/**
 * @author chao
 * @since 2019-06-18
 */
public class Solution214 {

    public static void main(String[] args) {
        //System.out.println(new Solution214().shortestPalindrome("aacecaaa"));
        //System.out.println(new Solution214().shortestPalindrome("abcbabcaba"));

        // System.out.println("aaaaabc".indexOf("abc"));

        System.out.println(new Solution214().shortestPalindrome2("abcd"));
    }

    public String shortestPalindrome(String s) {
        String result = null;
        Stack<Character> stack = new Stack<>();
        for (int i = s.length() - 1; i >= s.length() / 2; i--) {
            stack.push(s.charAt(i));
        }
        Character temp;
        Character c1;
        Character c2;
        for (int i = s.length() / 2 - 1; i >= 0; i--) {
            temp = s.charAt(i);
            c1 = stack.pop();
            if (!stack.empty()) {
                c2 = stack.pop();
                if (temp.equals(c2)) {
                    result = palindrome((Stack<Character>) stack.clone(), s, i - 1);
                    if (result != null) {
                        break;
                    }
                }
                stack.push(c2);
            }
            if (temp.equals(c1)) {
                result = palindrome((Stack<Character>) stack.clone(), s, i - 1);
                if (result != null) {
                    break;
                }
            }
            stack.push(c1);
            stack.push(temp);
        }
        if (result != null) {
            return result + s;
        } else {
            if (!stack.empty()) {
                stack.pop();
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.empty()) {
                sb.insert(0,stack.pop());
            }
            sb.append(s);
            return sb.toString();
        }
    }

    private String palindrome(Stack<Character> stack, String s, int startIndex) {
        boolean flag = true;
        Character temp;
        for (; startIndex >= 0; startIndex--) {
            temp = s.charAt(startIndex);
            if (!temp.equals(stack.pop())) {
                flag = false;
                break;
            }
        }
        if (flag) {
            StringBuilder sb = new StringBuilder();
            while (!stack.empty()) {
                sb.insert(0,stack.pop());
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * brute force
     */
    public String shortestPalindrome2(String s) {
        int n = s.length();
        String recv = new StringBuilder(s).reverse().toString();
        for (int i = 0; i <= n - 1; i++) {
            if (s.substring(0, n - i).equals(recv.substring(i))) {
                return recv.substring(0, i) + s;
            }
        }
        return "";
    }

    /**
     * kmp
     */
//    public String shortestPalindrome3(String s) {
//        int n = s.length();
//
//    }
}
