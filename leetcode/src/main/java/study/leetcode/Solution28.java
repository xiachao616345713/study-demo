package study.leetcode;

import java.util.Arrays;

/**
 * Solution28
 *
 * @author xiachao
 * @date 2022/10/29
 */
public class Solution28 {

    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        int[] table = genTable(needle);
        for (int i = 0, j = 0; i < haystack.length() - needle.length() + 1; ) {
            while (j < needle.length()) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    if (j > 0) {
                        i = i + j - table[j - 1];
                        j = table[j - 1];
                    } else {
                        i++;
                    }
                    break;
                }
                j++;
            }
            if (j == needle.length()) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 生成前缀匹配度
     * aabaacaaa
     * 0,1；i=2第三轮循环，index=1，char[2] = b，对比前缀aa和ab，不匹配，需要继续对比a和b（缩短匹配度对比是否相同，前一个匹配度=table[1 - 1]=0
     * 0,1,0,1,2,0,1,2;i=8，index=7，char[8] = a,对比前缀aab和aaa，不匹配， 对比aa和aa（前一个匹配度=table[7 - 1]=1
     */
    private int[] genTable(String needle) {
        int[] table = new int[needle.length()];
        for (int i = 1; i < needle.length(); i++) {
            char tmp = needle.charAt(i);
            int index = i - 1;
            do {
                if (needle.charAt(table[index]) == tmp) {
                    table[i] = table[index] + 1;
                    break;
                }
                if (index == 0) {
                    break;
                }
                //
                index = table[index - 1];
            } while (index >= 0);
        }
        return table;
    }

    public static void main(String[] args) {
        Solution28 solution28 = new Solution28();
//        String haystack = "1ab1ab1ac1b1ab1aga";
//        String needle = "1ab1ac1b1ab1";
        String haystack = "ababcaababcaabc";
        String needle = "ababcaabc";
        // aabaaabaaac
        // aabaaac
        // 0101220
        System.out.println(Arrays.toString(solution28.genTable(needle)));
        System.out.println(solution28.strStr(haystack, needle));
        System.out.println(haystack.indexOf(needle));
    }
}
