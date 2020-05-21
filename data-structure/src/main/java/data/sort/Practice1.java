package data.sort;

import com.alibaba.fastjson.JSON;
import data.structure.LinkedQueue;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xiachao
 * @date 2020/05/21
 */
public class Practice1 {

    //找出第k大大元素
    public int findK(int[] a, int k) {
        // 选择排序，循环k次
        int min = a[0];
        for (int i = 0; i < k; i++) {
            min = a[i];
            int x = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    x = j;
                }
            }
            if (x != i) {
                a[x] = a[i];
                a[i] = min;
            }
        }
        return min;
    }

    public int findK2(int[] a, int k) {
        k--;
        // 快排方式
        int start = 0;
        int end = a.length - 1;
        while (start < end) {
            int p = doQuickSort(a, start, end);
            if (p == k) {
                return a[p];
            } else if (p > k) {
                end = p - 1;
            } else {
                start = p + 1;
            }
        }
        if (start == end && start == k) {
            return a[start];
        }
        return -1;
    }

    public int doQuickSort(int[] a, int start, int end) {
        int p = (start + end) / 2;
        int pivot = a[p];
        int j = start;
        for (int i = start; i <= end; i++) {
            if (a[i] < pivot) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                // 中心点被换走,记下最后中心点
                if (j == p) {
                    p = i;
                }
                j++;
            }
        }
        a[p] = a[j];
        a[j] = pivot;
        return j;
    }

    public void quickSort(int[] a) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        queue.offer(a.length - 1);

        int start;
        int end;
        do {
            start = queue.poll();
            end = queue.poll();

            if (start >= end) {
                continue;
            }
            int p = doQuickSort(a, start, end);
            queue.offer(start);
            queue.offer(p - 1);

            queue.offer(p + 1);
            queue.offer(end);
        } while (!queue.isEmpty());
    }


    public static void main(String[] args) {
        int[] a = {2, 1, 3, 1, 5, 6, 8, 7, 11, 4, 2};
        //System.out.println(new Practice1().findK(a, 3));
        //System.out.println(new Practice1().findK2(a, 4));

        new Practice1().quickSort(a);

        System.out.println(JSON.toJSONString(a));

    }

}
