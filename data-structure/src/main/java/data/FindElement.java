package data;

import java.util.HashMap;
import java.util.Map;

/**
 * 矩阵元素查找
 *
 * @author xiachao
 * @date 2020/05/27
 */
public class FindElement {

    static Map<Character, Integer> map = new HashMap<>();

    static final int[] twentySix = new int[]{1, 26, 26 * 26, 26 * 26 * 26};

    static {
        for (int i = 0; i < 26; i++) {
            map.put((char) ('a' + i), i + 1);
        }
    }

    public static void main(String[] args) {

        // 矩阵
        char[][] chars = new char[][]{
            {'d', 'a', 'b', 'c'},
            {'e', 'f', 'a', 'd'},
            {'c', 'c', 'a', 'f'},
            {'d', 'e', 'f', 'c'},
            {'d', 'g', 'g', 'g'}
        };

        // 查找元素
        char[][] k = new char[][]{
            {'c', 'a'},
            {'e', 'f'}
        };
        int kHash = calHash(k, 0, 0);

        int[][] charsHash = new int[chars.length - 1][chars[0].length - 1];

        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 0; j < chars[0].length - 1; j++) {
                charsHash[i][j] = calHash(chars, i, j);
            }
        }

        int[] ret = find(charsHash, kHash);

        if (ret != null) {
            System.out.println("x = " + ret[0] + ";y = " + ret[1]);
        } else {
            System.out.println("not found");
        }
    }

    /**
     * 计算hash
     */
    private static int calHash(char[][] chars, int i, int j) {
        return map.get(chars[i][j]) + map.get(chars[i][j + 1]) * 26
            + map.get(chars[i + 1][j]) * twentySix[2] + map.get(chars[i + 1][j + 1]) * twentySix[3];
    }

    /**
     * find
     */
    private static int[] find(int[][] charsHash, int k) {
        for (int i = 0; i < charsHash.length; i++) {
            for (int j = 0; j < charsHash[0].length; j++) {
                if (k == charsHash[i][j]) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

}
