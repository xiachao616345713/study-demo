package study.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xiachao
 * @date 2020/08/04
 */
public class Solution345 {

    public static void main(String[] args) {
        Set<Character> vowels = new HashSet<>();
        vowels.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        String s = "leetcode";

        Character tmp = null;
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            if (tmp == null) {
                if (vowels.contains(chars[i])) {
                    tmp = chars[i];
                } else {
                    i++;
                }
            } else {
                if (vowels.contains(chars[j])) {
                    chars[i++] = chars[j];
                    chars[j--] = tmp;
                    tmp = null;
                } else {
                    j--;
                }
            }
        }

        System.out.println(new String(chars));
    }

}
