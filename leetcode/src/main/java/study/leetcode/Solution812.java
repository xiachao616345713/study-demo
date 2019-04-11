package study.leetcode;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Largest Triangle Area
 *
 * <a href="https://leetcode.com/problems/largest-triangle-area/">Largest Triangle Area</a>
 *
 *
 * @author chao
 * @since 2019-04-10
 */
public class Solution812 {

    public static void main(String[] args) {
//        int[][] points = {{0,0},{0,1},{1,0},{0,2},{2,0}};
//        System.out.println(new Solution812().largestTriangleArea(points));
        BigDecimal value = new BigDecimal(new Solution812().calculateArea(new int[]{0,0}, new int[] {0, 2}, new int[] {2, 0}));
        //BigDecimal value = new BigDecimal(new Solution812().calculateArea(new int[]{4,6}, new int[] {6, 5}, new int[] {3, 1}));
        System.out.println(value.setScale(6, RoundingMode.DOWN).doubleValue());
    }

    /**
     * <p>
     *      3 <= points.length <= 50.
     *      No points will be duplicated.
     *      -50 <= points[i][j] <= 50.
     *      Answers within 10^-6 of the true value will be accepted as correct.
     * </p>
     */
    public double largestTriangleArea(int[][] points) {
        double max = 0;
        double temp;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    temp = calculateArea(points[i], points[j], points[k]);
                    if (temp > max) {
                        max = temp;
                    }
                }
            }
        }
        return new BigDecimal(max).setScale(6, RoundingMode.DOWN).doubleValue();
    }

    private double calculateArea(int[] point1, int[] point2, int[] point3) {
        if (point1[0] == point2[0] && point1[0] == point3[0] || point1[1] == point2[1] && point1[1] == point3[1]) {
            return 0.0D;
        }
        int flag = 1;
        double a = Math.pow(point1[0] - point2[0], 2) + Math.pow(point1[1] - point2[1], 2);
        double b = Math.pow(point1[0] - point3[0], 2) + Math.pow(point1[1] - point3[1], 2);
        double c = Math.pow(point2[0] - point3[0], 2) + Math.pow(point2[1] - point3[1], 2);
        if (b > a) {
            flag = 2;
            if (c > b) {
                flag = 3;
            }
        } else if (c > a) {
            flag = 3;
        }
        if (flag == 1 || flag == 3) {
            // max a || max c
            return Math.sqrt(4 * a * c - Math.pow(a + c - b, 2)) / 4;
        } else {
            // max b
            return Math.sqrt(4 * b * c - Math.pow(b + c - a, 2)) / 4;
        }
    }
}