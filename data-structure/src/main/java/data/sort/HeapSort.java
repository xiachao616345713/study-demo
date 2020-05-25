package data.sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author xiachao
 * @date 2020/05/25
 */
public class HeapSort {

    private void buildHeap(int[] a) {
        int capacity = 1;
        while (true) {
            capacity = capacity << 1;
            if (capacity > a.length) {
                break;
            }
        }
        for (int i = 0; i < capacity/2 - 1; i++) {
            heapify(a, i);
        }
    }

    private void heapify(int[] a, int index) {
        int length = a.length;
        int key = a[index];
        int maxPos = index;

        while (true) {
            int child = (index << 1) + 1;
            if (child < length && a[child] > key) {
                maxPos = child;
            }
            if (child + 1 < length && a[child + 1] > a[maxPos]) {
                maxPos = child + 1;
            }
            if (maxPos == index) {
                break;
            }
            int temp = a[maxPos];
            a[maxPos] = key;
            a[index] = temp;
            key = temp;
            index = maxPos;
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 7, 5, 4, 6, 1};

        HeapSort heapSort = new HeapSort();
        heapSort.buildHeap(a);

        System.out.println(Arrays.toString(a));
    }

}
