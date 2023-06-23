package study.test1.nowcoder;

import java.util.Scanner;

/**
 * @author xiachao
 * @date 2021/03/31
 */
public class NowCoderPractice3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        // 注意 while 处理多个 case
        while (in.hasNextLine()) {
            int length = Integer.parseInt(in.nextLine());
            int[] input = new int[1001];
            for (int i = 0; i < length; i++) {
                input[Integer.parseInt(in.nextLine())] = 1;
            }
            for (int i = 1; i < 1001; i++) {
                if (input[i] == 1) {
                    System.out.println(i);
                }
            }
        }
    }

    // practice2
    private int method(String str, String s) {
        int char1 = s.charAt(0);
        int char2;
        if (char1 >= 'A' && char1 <= 'Z') {
            char2 = 'a' - 'A' + char1;
        } else {
            char2 = char1 - ('a' - 'A');
        }

        int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            int item = str.charAt(i);
            if (item == char1 || item == char2) {
                ret++;
            }
        }
        return ret;
    }

}
