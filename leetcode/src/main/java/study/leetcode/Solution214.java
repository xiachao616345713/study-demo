package study.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author chao
 * @since 2019-06-18
 */
public class Solution214 {

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
    public String shortestPalindrome3(String s) {
        StringBuilder reverse = new StringBuilder(s).reverse();
        // reverse = abcbabcaba
        // newStr = reverse+"#" + s 即abcbabcaba#abacbabcba
        // 找newStr最后一位和前缀匹配程度，即s前缀的回文程度，需要补充的字符串为出去回文字符串剩余的反转字符串
        String newStr = s + "#" + reverse;

        int[] f = calculateTable(newStr);
        for (int item : f) {
            System.out.print(item);
        }
        System.out.println();
        return reverse.substring(0, s.length() - f[newStr.length() - 1]) + s;
    }

    /**
     * <code>
     * cacacabc
     * caca
     *   caca
     * 匹配+1
     * cacac
     *   cacab
     * </code>
     *
     * <p>不匹配找更短的，cacac和cacab因为不匹配找更短的，cacac的更短的caca和cacab更短的caca相同。</p>
     * <p>所以cacac减少1即caca的前缀匹配度，如果cacac的caca无匹配前缀表示cacab前4位就无匹配字符串，结束，
     * 直接从失配处和子串第一位重新开始匹配;</p>
     * <p>如果cacac的caca前缀有匹配，那么假设匹配2位表示cacab和前缀也匹配2位即ca，那么比较第三位b和前缀第三位</p>
     * <p>如果匹配则匹配度是3位，如果不匹配则继续找前缀ca的前缀匹无匹配结束有匹配继续和b比较，如此匹配一直循环下去</p>
     *
     * @param s s
     * @param subStr subString
     * @return index
     */
    public Integer indexSubStr(String s, String subStr) {
        int[] f = calculateTable(subStr);

        int j = 0;
        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) == subStr.charAt(j)) {
                j++;
                if (j == subStr.length()) {
                    return i - (j - 1);
                }
                i++;
            } else {
                // 第一位没匹配成功，i++继续从j=0即字串第1位开始匹配
                if (j == 0) {
                   i++;
                } else {
                    // 有匹配成功的前缀，i不变，对齐匹配成功的前缀
                    // 匹配到"BBC ABCDAB "和"ABCDABD"时，" "和"D"不等
                    // 但"D"前缀f[j-1]为2即"AB"，i不动继续和"ABCDABD"前缀之后比较，即"BBC ABCDAB "的"AB "和"ABCDABD"的"ABC"比较
                    j = f[j - 1];
                }
            }
        }

        return -1;
    }

    public int[] calculateTable(String subStr) {
        // 计算table
        int[] f = new int[subStr.length()];
        f[0] = 0;
        int t = 0;
        char temp;
        for (int i = 1; i < subStr.length(); i++) {
            temp = subStr.charAt(i);
            if (temp == subStr.charAt(t)) {
                f[i] = ++t;
            } else {
                while (t > 0) {
                    // 假设到b不匹配，b之前匹配度为caca即t，t=4
                    // 拿caca继续找匹配度(索引从0开始)t=f[t-1]=f[3]=2，s[t]=s[2]=c≠b
                    // 拿ca继续找t=f[t-1]=f[1]=0匹配度为0结束
                    /**
                     * t不匹配，t之前的匹配的内容就是索引[0,t-1]
                     * 看t-1的匹配度f[t-1]，如果等于2那表示索引[0,1]是匹配的，比较索引[2]即当前匹配度，的值是否相等
                     * 不相等继续处理，匹配度为0是比较第一位即可
                     */
                    t = f[t - 1];
                    if (t >= 0) {
                        if (temp == subStr.charAt(t)) {
                            f[i] = ++t;
                            break;
                        }
                    }
                }
            }
        }
        return f;
    }

    public static void main(String[] args) {
        //System.out.println(new Solution214().shortestPalindrome("aacecaaa"));
        //System.out.println(new Solution214().shortestPalindrome("abcbabcaba"));

        // System.out.println("aaaaabc".indexOf("abc"));

        //System.out.println(new Solution214().shortestPalindrome2("abcd"));

        System.out.println(new Solution214().shortestPalindrome3("abcbabcaba"));
        System.out.println(new Solution214().shortestPalindrome2("abcbabcaba"));
        int[] f = new Solution214().calculateTable("abcbabcaba");
        System.out.println();

        //System.out.println(new Solution214().indexSubStr("BBC ABCDAB ABCDABCDABDE", "ABCDABD"));

        System.out.println(Arrays.toString(new Solution214().calculateTable("aacbaaca")));
        System.out.println(Arrays.toString(new Solution214().calculateTable("abcbabca")));
        System.out.println(Arrays.toString(new Solution214().calculateTable("cacbcaca")));
        System.out.println(Arrays.toString(new Solution214().calculateTable("aacbaacc")));
    }
}
