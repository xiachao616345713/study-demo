package data.sort;

import java.util.Arrays;

/**
 * @author xiachao
 * @date 2021/03/25
 */
public class SortPractice {

    public static void main(String[] args) {
        int[] num = new int[]{3, 5, 1, 7, 2, 3, 9, 10, 22, 21, 10, 3, 2};

        SortPractice test = new SortPractice();
        //test.maopaoSort(num);
        //test.selectSort(num);
        //test.insertSort(num);
        //test.mergeSort(num, 0, num.length - 1);
        test.quickSort(num, 0, num.length - 1);

        System.out.println(Arrays.toString(num));

        System.out.println(test.binarySearch(num, 9, 0, num.length));

    }

    void maopaoSort(int[] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = num.length - 1; j > i; j--) {
                if (num[j] < num[j - 1]) {
                    swap(num, j, j - 1);
                }
            }
        }
    }

    void selectSort(int[] num) {
        int minIndex;
        for (int i = 0; i < num.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < num.length; j++) {
                if (num[minIndex] > num[j]) {
                    minIndex = j;
                }
            }
            swap(num, i, minIndex);
        }
    }

    void insertSort(int[] num) {
        for (int i = 1; i < num.length; i++) {
            for (int j = 0; j < i; j++) {
                if (num[i] < num[j]) {
                    int temp = num[i];
                    for (int k = i; k > j; k--) {
                        num[k] = num[k - 1];
                    }
                    num[j] = temp;
                }
            }
        }
    }

    void mergeSort(int[] num, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (end - start) / 2 + start;
        mergeSort(num, start, middle);
        mergeSort(num, middle + 1, end);
        merge(num, start, middle, middle + 1, end);
    }

    void merge(int[] num, int afrom, int ato, int bfrom, int bto) {
        int[] ret = new int[ato - afrom + 1 + bto - bfrom + 1];
        int i = 0;
        int j = afrom;
        int k = bfrom;
        while (j <= ato && k <= bto) {
            if (num[j] <= num[k]) {
                ret[i++] = num[j++];
            } else {
                ret[i++] = num[k++];
            }
        }
        while (j <= ato) {
            ret[i++] = num[j++];
        }
        while (k <= bto) {
            ret[i++] = num[k++];
        }
        i = 0;
        while (afrom <= ato) {
            num[afrom++] = ret[i++];
        }
        while (bfrom <= bto) {
            num[bfrom++] = ret[i++];
        }
    }

    void quickSort(int[] num, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = num[end];
        int j = start;
        for (int i = start; i <= end; i++) {
            if (num[i] < pivot) {
                swap(num, i, j);
                j++;
            }
        }
        swap(num, end, j);
        quickSort(num, start, j - 1);
        quickSort(num, j + 1, end);
        merge(num, start, j, j + 1, end);
    }

    int binarySearch(int[] num, int value, int start, int end) {
        int middle = start + (end - start) / 2;
        if (num[middle] == value) {
            return middle;
        }
        if (num[middle] > value) {
            return binarySearch(num, value, start, middle - 1);
        }
        return binarySearch(num, value, middle + 1, end);
    }

    private void swap(int[] num, int source, int target) {
        int tmp = num[source];
        num[source] = num[target];
        num[target] = tmp;
    }

}
