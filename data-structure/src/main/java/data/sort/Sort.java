package data.sort;

import java.util.Arrays;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author chao
 * @since 2018-12-26
 */
public class Sort {

    /**
     * 冒泡排序
     * @param a 数组
     */
    public void bubbleSort(int[] a) {
        if (a.length <= 1)
            return;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i -1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * @param a 数组
     */
    public void insertSort(int[] a) {
        if (a.length <= 1)
            return;
        for (int i = 1; i < a.length; i++) {
            int value = a[i];
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

    public void mergeSort(int[] a, int start, int end) {
        int len = end - start;
        if (len <= 0) return;
        if (len == 1) {
            if (a[start] > a[end]) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            return;
        }
        int middle = start + len/2;
        mergeSort(a, start, middle);
        mergeSort(a, middle + 1, end);

//        for (int i = middle + 1; i <= end; i++) {
//            int value = a[i];
//            int j = i - 1;
//            for (; j >= start; j--) {
//                if (a[j] > value) {
//                    a[j + 1] = a[j];
//                } else {
//                    break;
//                }
//            }
//            a[j + 1] = value;
//        }
        int[] c = new int[len + 1];
        int i = start, j = middle + 1, k = 0;
        for (; i <= middle && j <= end; k++) {
            if(a[i] < a[j]) {
                c[k] = a[i++];
            } else {
                c[k] = a[j++];
            }
        }
        while (i <= middle) {
            c[k++] = a[i++];
        }
        while (j <= end) {
            c[k++] = a[j++];
        }
        for (int l = 0; l <= len; l++) {
            a[start + l] = c[l];
        }
    }


    public void quickSort(int[] a, int start, int end) {
        if (end <= start) return;
        //int q = swap(a, start, end, start + (end - start)/2);
        int q = swap(a, start, end, end);
        if (q > 0) {
            quickSort(a, start, q - 1);
            quickSort(a, q + 1, end);
        } else {
            quickSort(a, 0 - q + 1, end);
        }
    }

    /**
     * <p>
     *     6,11,3,9,8  a[p]=pivot=8,p=4
     *     step1:6,11,3,9,8  a[i]=a[j]=6 (a[j]<8,swap a[i] a[j])
     *     step2:6,11,3,9,8  a[i]=a[j]=11
     *     step3:6,11,3,9,8  a[i]=11,a[j]=3 (a[j]<8,swap a[i] a[j])
     *     step4:6,3,11,9,8  a[i]=11,a[j]=9
     *     step5:6,3,11,9,8  a[i]=11,a[j]=8
     *     final: swap a[i] a[p]  6,3,8,9,11
     *     以8作为区分点，小于i坐标的数据为处理过的数据(即小于8的数据)，
     *     最后交换a[i]和a[p]，pivot左侧数据小于pivot，pivot右侧数据大于等于pivot,
     *     返回i为区分点位置
     * </p>
     */
    private int swap(int[] a, int start, int end, int p) {
        //int i = start, j = start;
        int pivot = a[p];
        int i = start;
        for (int j = start; j <= end; j++) {
            if (a[j] < pivot) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
            }
        }
        if (p > i) {
            a[p] = a[i];
            a[i] = pivot;
            return i;
        } else {
            // 小于i的数据全部已经排序好了，为已经处理区间
            return 0 - i;
        }
    }

    /**
     * 计数排序，不适合max-min太大，min为非正整数排序（需要转换，比如负数最小值为-100可以先加100,）
     */
    public void countSort(int[] a) {
        if (a.length <= 1) return;
        /*
         * 找出a的min和max
         */
        int min = a[0];
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            } else if (a[i] > max) {
                max = a[i];
            }
        }
        /*
         * c放入index对应元素个数
         */
        int[] c = new int[max - min + 1];
        for (int i = 0; i < a.length; i++) {
            c[a[i] - min]++;
        }
        /*
         * c放入小于等于index对应元素个数
         */
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i] + c[i - 1];
        }
        /*
         * 倒叙查询a，a元素在c中位置
         * 如：c[a[5]] = 9表示小于等于a[5]的值数据有9个，则将a[5]放到结果r中第9位即index=8的位置，同时将c[a[5]]减1
         */
        int[] r = new int[a.length];
        for (int i = a.length - 1; i >= 0; i++) {
            int cIndex = a[i] - min;
            int rIndex = c[cIndex] - 1;
            r[rIndex] = a[i];
            c[cIndex] = rIndex;
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = r[i];
        }
    }

    public static void main(String[] args) {
        //int[] a = {3,5,8,2,3,1,8,9,11,3,5,5,5,5,5};

//        Sort sort = new Sort();
//        //sort.bubbleSort(a);
//        //sort.insertSort(a);
//        //sort.mergeSort(a, 0, a.length - 1);
//        //sort.quickSort(a, 0, a.length - 1);
//        sort.quickSort(a, 0, a.length - 1);

        int[] a = new int[64];
        for (int i = 0; i < a.length; i++) {
            a[i] = RandomUtils.nextInt();
        }
        /**
         * DualPivotQuicksort.sort方法
         * 1.length小于286
         *      1)小于47使用插入排序
         *      2)使用快排，围绕中点平均取5个数据，使用快排排好
         *      如果5个数全部相等使用中间数作为轴pivot,小于pivot的放左边，大于pivot的放右边
         *      如果5个数不全部相等，使用5个数中的2、4作为2个轴pivot1、pivot2，区间分3个小于pivot1，大于pivot1小于pivot2，大于pivot2
         * 2.length大于286
         *      1)检查是否已经有序，检查过程中同时对数据排序，数据相等使用快排
         *      如果有序度不高，使用快排
         *      最后归并已排序好的段
         */
        Arrays.sort(a);

        for (int s : a) {
            System.out.println(s);
        }
    }
}
