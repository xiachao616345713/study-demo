package data.sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author xiachao
 * @date 2020/05/25
 */
public class HeapSort {

    private static void buildHeap(int[] a) {
        int capacity = 1;
        while (true) {
            capacity = capacity << 1;
            if (capacity > a.length) {
                break;
            }
        }
        // 从倒数第二层在最后一个root节点开始堆化，大顶堆
        for (int i = capacity/2 - 1; i >= 0; i--) {
            heapify(a, a.length, i);
        }
    }

    private static void heapify(int[] a, int length, int index) {
        int maxPos = index;
        while (true) {
            // 左节点比对root，找出最大的
            int child = (index << 1) + 1;
            if (child < length && a[child] > a[index]) {
                maxPos = child;
            }
            // 左节点和root中大大对比又节点，找出最大的
            if (child + 1 < length && a[child + 1] > a[maxPos]) {
                maxPos = child + 1;
            }
            // root最大不动，中止
            if (maxPos == index) {
                break;
            }
            // 交换root和最大的字节点位置
            swap(a, index, maxPos);
            index = maxPos;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void main(String[] args) {
        int[] a = {3, 2, 7, 5, 4, 6, 1};

        buildHeap(a);

        System.out.println(Arrays.toString(a));

        // heapSort-每次取堆顶元素和未排序最一个堆元素交换，重新堆化堆顶元素，重复
        for (int i = a.length - 1; i > 0; i--) {
            swap(a, 0, i);

            heapify(a, i, 0);
        }

        System.out.println(Arrays.toString(a));
    }

}
