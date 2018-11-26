package study.leetcode;

import java.util.LinkedList;

/**
 * @author chao
 * @since 2018-11-26
 */
public class Solution946 {

    public static void main(String[] args) {
        int[] pushed = new int[] {2,1,0};
        int[] popped = new int[] {2,1,0};
        System.out.println(new Solution946().validateStackSequences(pushed, popped));
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> list = new LinkedList<>();
        int i = 0;
        for (int p : popped) {
            if (list.size()> 0 && list.getLast() == p) {
                list.removeLast();
                continue;
            }
            while (i<pushed.length) {
                if (pushed[i] != p) {
                    list.add(pushed[i]);
                    i++;
                } else {
                    i++;
                    break;
                }
            }
        }
        return list.size() == 0;
    }
}
