package study.leetcode;

import java.util.Arrays;

/**
 * @author xiachao
 * @date 2020/08/03
 */
public class Solution1471 {

    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);

        int median = arr[(arr.length - 1) / 2];

        int[] ret = new int[k];
        int maxDiff = arr[arr.length - 1] - arr[0];
        int start = 0, end = 0;
        int temp;
        boolean startFlag = true;
        for (int i = 0; i < k; i++) {
            if (startFlag) {
                temp = median - arr[start];
                if (maxDiff >= temp) {
                    ret[i] = arr[arr.length - 1 - end];
                    end++;
                    maxDiff = temp;
                    startFlag = false;
                } else {
                    ret[i] = arr[start];
                    start++;
                }
            } else {
                temp = arr[arr.length - 1 - end] - median;
                if (temp >= maxDiff) {
                    ret[i] = arr[arr.length - 1 - end];
                    end++;
                } else {
                    ret[i] = arr[start];
                    start++;
                    maxDiff = temp;
                    startFlag = true;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{1, 2, 3, 4, 5};
//        int k = 2;

//        int[] arr = new int[] {1,1,3,5,5};
//        int k = 2;

        int[] arr = new int[] {6,7,11,7,6,8};
        int k = 5;
        System.out.println(Arrays.toString(new Solution1471().getStrongest(arr, k)));
    }
}
