package data;

import java.util.Arrays;

/**
 * 排序test
 *
 * @author xiachao
 * @date 2020/06/23
 */
public class SortPractice {

    // 冒泡
    public void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    // 选择
    public void sort2(int[] a) {
        int max;
        for (int i = 0; i < a.length; i++) {
            max = 0;
            int j = 1;
            for (; j < a.length - i; j++) {
                if (a[j] > a[max]) {
                    max = j;
                }
            }
            swap(a, max, j - 1);
        }
    }

    // 插入
    public void sort3(int[] a) {
        int value;
        for (int i = 1; i < a.length; i++) {
            value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
    }

    // 快排
    public void sort4(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = quickSort(a, start, end, end);
        if (p > 0) {
            sort4(a, start, p - 1);
            sort4(a, p + 1, end);
        }
    }

    private int quickSort(int[] a, int start, int end, int p) {
        if (start >= end) {
            return -1;
        }
        int pivot = a[p];
        int j = start;
        for (int i = start; i <= end; i++) {
            if (a[i] < pivot) {
                swap(a, i, j);
                j++;
            }
        }
        a[p] = a[j];
        a[j] = pivot;
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {3, 5, 6, 3, 2, 1, 2, 7};
        //new SortPractice().sort(a);
        //new SortPractice().sort2(a);
        //new SortPractice().sort3(a);
        new SortPractice().sort4(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

}
