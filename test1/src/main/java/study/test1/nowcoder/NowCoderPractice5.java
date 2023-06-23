package study.test1.nowcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author xiachao
 * @date 2021/03/31
 */
public class NowCoderPractice5 {

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        // 注意 while 处理多个 case
//        while (in.hasNextLine()) {
//            String s = in.nextLine();
//            if (s.isEmpty()) {
//                return;
//            }
//            // method4(s);
//            // method15(s);
//        }
//    }

    /**
     * 第四题-字符串分隔
     */
    private static void method4(String s) {
        int i = 0;
        for (; i < s.length() / 8; i++) {
            System.out.println(s.substring(i * 8, i * 8 + 8));
        }
        if (i * 8 != s.length()) {
            System.out.println((s.substring(i * 8) + "00000000").substring(0, 8));
        }
    }

    /**
     * 第15题-求int型数据在内存中存储时1的个数
     */
    private static void method15(String s) {
        int num = Integer.parseInt(s);
        int ret = 0;
        while (num != 0) {
            ret += num - (num >> 1 << 1);
            num = num >> 1;
        }
        System.out.println(ret);
    }


    /**
     * 第75题-最长公共子传
     */
    private static void method75(String s, String subStr) {
        int[] kmp = calKmpTable(subStr);

        for (int i = 0; i < s.length(); i++) {
            
        }

    }

    private static int[] calKmpTable(String subStr) {
        int[] table = new int[subStr.length()];
        table[0] = 0;
        // cal table
        for (int i = 1; i < subStr.length(); i++) {
            char tmp = subStr.charAt(i);
            int index = i - 1;
            while (index >= 0) {
                if (subStr.charAt(table[index]) == tmp) {
                    table[i] = table[index] + 1;
                    break;
                } else if (index == 0) {
                    break;
                }
                index = table[index];
            }
        }
        return table;
    }

}
