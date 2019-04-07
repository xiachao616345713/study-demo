package study.leetcode;


/**
 * Valid Palindrome
 * <a href="https://leetcode.com/problems/valid-palindrome/">Valid Palindrome</a>
 *
 * @author chao
 * @since 2019-03-31
 */
public class Solution125 {

    public static void main(String[] args) {
        Solution125 solution = new Solution125();
        //System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
        //System.out.println(solution.isPalindrome("race a car"));
        System.out.println(solution.isPalindrome("0P"));
    }

    public boolean isPalindrome(String s) {
        Character ci;
        Character cj;
        for (int i = 0, j = s.length() -1; i < s.length() && j >= 0; i++) {
            if (i == j) {
                return true;
            }
            ci = s.charAt(i);
            if (ci >= '0' && ci <= '9') {
                for (; j >= 0; j--) {
                    if (i == j) {
                        return true;
                    }
                    cj = s.charAt(j);
                    if (cj >= '0' && cj <= '9') {
                        if (ci == cj) {
                            j--;
                            break;
                        } else {
                            return false;
                        }
                    } else if (cj >= 'a' && cj <= 'z' || cj >= 'A' && cj <= 'Z') {
                        return false;
                    }
                }
            } else if (ci >= 'a' && ci <= 'z') {
                for (; j >= 0; j--) {
                    if (i == j) {
                        return true;
                    }
                    cj = s.charAt(j);
                    if (cj >= '0' && cj <= '9') {
                        return false;
                    } else if (cj >= 'a' && cj <= 'z') {
                        if (ci != cj) {
                            return false;
                        } else {
                            j--;
                            break;
                        }
                    } else if (cj >= 'A' && cj <= 'Z') {
                        if (ci - cj != 32) {
                            return false;
                        } else {
                            j--;
                            break;
                        }
                    }
                }
            } else if (ci >= 'A' && ci <= 'Z') {
                for (; j >= 0; j--) {
                    if (i == j) {
                        return true;
                    }
                    cj = s.charAt(j);
                    if (cj >= '0' && cj <= '9') {
                        return false;
                    } else if (cj >= 'a' && cj <= 'z') {
                        if (cj - ci != 32) {
                            return false;
                        } else {
                            j--;
                            break;
                        }
                    } else if (cj >= 'A' && cj <= 'Z') {
                        if (ci != cj) {
                            return false;
                        } else {
                            j--;
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }
}
