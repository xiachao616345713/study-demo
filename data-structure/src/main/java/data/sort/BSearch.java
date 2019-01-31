package data.sort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * 2分查找问题
 *
 * @author chao
 * @since 2019-01-19
 */
public class BSearch {

    private static int bSearch(int[] a, int low, int high, int number) {
        if (low > high) {
            return -1;
        }
        int middle = (low + high) / 2;
        int value = a[middle];
        if (value == number) {
            return middle;
        } else if (value > number) {
            return bSearch(a, low, middle - 1, number);
        } else {
            return bSearch(a, middle + 1, high, number);
        }
    }

    /**
     * 2分查找第一个给定的值
     */
    private static int bSearchFirstNumber(int[] a, int low, int high, int number) {
        if (low > high) {
            return -1;
        }
        int middle = (low + high) / 2;
        int value = a[middle];
        if (value == number) {
            if (a[low] == value) {
                return low;
            } else {
                return bSearchFirstNumber(a, low + 1, middle, number);
            }
        } else if (value > number) {
            return bSearchFirstNumber(a, low, middle - 1, number);
        } else {
            return bSearchFirstNumber(a, middle + 1, high, number);
        }
    }

    /**
     * 2分查找最后一个给定的值
     */
    private static int bSearchLastNumber(int[] a, int low, int high, int number) {
        if (low > high) {
            return -1;
        }
        int middle = (low + high) / 2;
        int value = a[middle];
        if (value == number) {
            if (a[high] == value) {
                return high;
            } else {
                return bSearchLastNumber(a, middle, high - 1, number);
            }
        } else if (value > number) {
            return bSearchLastNumber(a, low, middle - 1, number);
        } else {
            return bSearchLastNumber(a, middle + 1, high, number);
        }
    }

    /**
     * 2分查找第一个大于给定的值
     */
    private static int bSearchGreatNumberFirst(int[] a, int low, int high, int number) {
        if (low > high) {
            return -1;
        } else if (low == high) {
            return low;
        }
        int middle = (low + high) / 2;
        int value = a[middle];
        if (value > number) {
            if (a[low] > number) {
                return low;
            } else {
                return bSearchGreatNumberFirst(a, low + 1, middle, number);
            }
        } else {
            return bSearchGreatNumberFirst(a, middle + 1, high, number);
        }
    }

    /**
     * 2分查找最后一个小于给定的值
     */
    private static int bSearchLessNumberLast(int[] a, int low, int high, int number) {
        if (low > high) {
            return -1;
        } else if (low == high) {
            return low;
        }
        int middle = (low + high) / 2;
        int value = a[middle];
        if (value < number) {
            if (a[high] < number) {
                return high;
            } else {
                return bSearchLessNumberLast(a, middle, high - 1, number);
            }
        } else {
            return bSearchLessNumberLast(a, low, middle - 1, number);
        }
    }

    /**
     * 求平方根，二分法
     * <p>
     * 1，number小于1，low为number，high为1 2，number等于1，return 1 3，number大于1，low为1，high为number
     * </p>
     *
     * @param number 数
     * @param precision 精度
     */
    private static double sqrt(double number, int precision) {
        if (number < 0) {
            return Double.NaN;
        }
        BigDecimal low;
        BigDecimal high;
        BigDecimal numb = new BigDecimal(String.valueOf(number));
        if (number == 1) {
            return 1d;
        } else if (number > 1) {
            low = BigDecimal.ONE;
            high = numb;
        } else {
            low = numb;
            high = BigDecimal.ONE;
        }

        BigDecimal two = new BigDecimal("2");
        BigDecimal threshold = BigDecimal.ONE;
        for (int i = 0; i < precision + 1; i++) {
            threshold = threshold.divide(BigDecimal.TEN, precision + 1, RoundingMode.HALF_UP);
        }

        BigDecimal mid = high.subtract(low).divide(two, precision + 1, RoundingMode.HALF_UP).add(low);
        while (high.subtract(low).compareTo(threshold) > 0) {
            mid = high.subtract(low).divide(two, precision + 1, RoundingMode.HALF_UP).add(low);
            int compareR = mid.multiply(mid).compareTo(numb);
            if (compareR == 0) {
                return mid.doubleValue();
            } else if (compareR > 0) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return mid.setScale(precision, RoundingMode.HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        int[] a = {3, 5, 8, 2, 43, 1, 8, 9, 25, 25, 25, 25, 11, 33, 25, 22, 15, 43, 35, 25, 91, 99};

        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println("==============");

        System.out.println(bSearch(a, 0, a.length - 1, 25));
        System.out.println(bSearchFirstNumber(a, 0, a.length - 1, 25));
        System.out.println(bSearchLastNumber(a, 0, a.length - 1, 25));
        System.out.println(bSearchGreatNumberFirst(a, 0, a.length - 1, 25));
        System.out.println(bSearchLessNumberLast(a, 0, a.length - 1, 25));

//        System.out.println(sqrt(3, 1));
    }

}
