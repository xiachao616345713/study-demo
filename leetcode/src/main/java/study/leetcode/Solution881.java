package study.leetcode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/boats-to-save-people/">881. Boats to Save People</a>
 *
 * <p>
 * You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where
 * each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the
 * sum of the weight of those people is at most limit.
 * <p>
 * Return the minimum number of boats to carry every given person.
 * </p>
 *
 * @author xiachao
 * @date 2021/03/22
 */
public class Solution881 {

    /**
     * 1 <= people.length <= 5 * 10^4 <br/> 1 <= people[i] <= limit <= 3 * 10^4
     */
    public int numRescueBoats(int[] people, int limit) {
        int[] array = new int[limit + 1];
        for (int item : people) {
            array[item] += 1;
        }

        int ret = array[limit];
        for (int i = limit - 1; i > limit / 2; i--) {
            if (array[i] == 0) {
                continue;
            }
            int tmp = array[i];
            ret += tmp;

            // decrement array[i], index from limit - i to 1
            for (int j = limit - i; j >= 1 && tmp > 0; j--) {
                if (array[j] == 0) {
                    continue;
                }
                if (array[j] >= tmp) {
                    array[j] = array[j] - tmp;
                    break;
                }
                tmp = tmp - array[j];
                array[j] = 0;
            }
        }

        int left = 0;
        for (int i = 1; i <= limit / 2; i++) {
            if (array[i] > 0) {
                left += array[i];
            }
        }
        ret += left / 2 + (left % 2 == 0 ? 0 : 1);

        return ret;
    }

    public static void main(String[] args) {
        int[] people = new int[]{36,74,84,92,17,68,97,6,68,85,35,26,60,45,18,48,41,57,51,32};
        Arrays.sort(people);
        System.out.println(Arrays.toString(people));
        int limit = 100;
        System.out.println(new Solution881().numRescueBoats(people, limit));
    }

}
