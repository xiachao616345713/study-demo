package data;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 贪心
 *
 * @author xiachao
 * @date 2020/05/29
 */
public class TanxinTest {


    /**
     * <pre>
     * 在一个非负整数 a 中，我们希望从中移除 k 个数字，让剩下的数字值最小，如何选择移除哪 k 个数字呢？
     * </pre>
     */
    private static int[] minSum(int[] a, int k, int start, int end) {
        int[] ret = new int[k];
        if (start + k == end) {
            for (int i = start, j = 0; i < end; i++, j++) {
                ret[j] = i;
            }
            return ret;
        }
        int minIndex = start;
        int min = a[start];
        for (int i = start + 1; i < end; i++) {
            if (a[i] < min) {
                minIndex = i;
                min = a[i];
            }
        }
        if (minIndex - start == k) {
            for (int i = 0, j = start; i < k; i++, j++) {
                ret[i] = j;
            }
            return ret;
        }
        if (minIndex - start < k) {
            int i = 0;
            for (int j = start; j < minIndex; j++, i++) {
                ret[i] = j;
            }
            // 移除前面,剩余的重复操作
            int[] left = minSum(a, k - minIndex + start, minIndex + 1, end);
            for (int j = 0; j < left.length; j++, i++) {
                ret[i] = left[j];
            }
            return ret;
        }
        // minIndex > k
        // 最小的数尽可能往前放，那么最小的数后面的数字就不能删出，（最小的数后面数字越多，最小的数十进制位越高）
        return minSum(a, k, start, minIndex);
    }

    private static int[] minSumUseStack(int[] a, int k) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(a[0]);
        int i = 1;
        Integer temp;
        while (i < a.length && k > 0) {
            temp = stack.peek();
            if (temp == null || a[i] > temp) {
                stack.push(a[i]);
                i++;
            } else {
                stack.pop();
                k--;
            }
        }
        while (k > 0) {
            stack.pop();
            k--;
        }
        while (i < a.length) {
            stack.push(a[i]);
            i++;
        }
        int[] ret = new int[stack.size()];
        for (int j = stack.size() - 1; j >= 0; j--) {
            ret[j] = stack.pop();
        }
        return ret;
    }

    /**
     * <pre>
     * 假设有 n 个人等待被服务，但是服务窗口只有一个，每个人需要被服务的时间长度是不同的，如何安排被服务的先后顺序，才能让这 n 个人总的等待时间最短？
     * </pre>
     */
    void minWaitTime() {
        // 每次取服务时间最短的
    }

    public static void main(String[] args) {
        int k = 2;
        int[] array = {1, 2, 4, 3};
        int[] array2 = {2, 1, 3, 4};
        int[] array3 = {2,3,3,3,1,2};
        int[] array4 = {3,3,3,4,1,2};
        int[] array5 = {3,3,1,2};
        int[] array6 = {2,2,2,2,2,2,2,2,1,2};

        Object[] o = {array, array2, array3, array4, array5, array6};
        int[] temp;
        for (int i = 0; i < o.length; i++) {
            temp = (int[]) o[i];
            System.out.println(Arrays.toString(minSum(temp, k, 0, temp.length)));
        }

        System.out.println("===========");
        for (int i = 0; i < o.length; i++) {
            System.out.println(Arrays.toString(minSumUseStack((int[]) o[i], k)));
        }
    }

}
