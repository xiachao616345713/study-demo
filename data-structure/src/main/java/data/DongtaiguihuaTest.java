package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 动态规划
 *
 * @author xiachao
 * @date 2020/06/01
 */
public class DongtaiguihuaTest {

    /**
     * 求最短路径
     * <pre>
     *          5
     *         ↙ ↘
     *        7   8
     *       ↙ ↘ ↙ ↘
     *      2   3   4
     *     ↙ ↘ ↙ ↘ ↙ ↘
     *    4   9   6   1
     *   ↙ ↘ ↙ ↘ ↙ ↘ ↙ ↘
     *  2   7   9   4   5
     * </pre>
     */
    private void shortestPath(List<Integer[]> list) {
        // 最后一个数组长度
        int[] allPath = new int[list.get(list.size() - 1).length];

        // 第一个
        Iterator<Integer[]> it = list.iterator();
        allPath[0] = it.next()[0];

        Integer[] tmp;
        while (it.hasNext()) {
            tmp = it.next();

            // 最后一个(先放后面的，再放前面的)
            allPath[tmp.length - 1] = allPath[tmp.length - 2] + tmp[tmp.length - 1];
            // 其他的2选1，取路径短的
            for (int i = tmp.length - 2; i > 0; i--) {
                allPath[i] = Math.min(allPath[i], allPath[i - 1]) + tmp[i];
            }
            // 第一个
            allPath[0] = allPath[0] + tmp[0];
        }

        int shortest = allPath[0];
        int tmpPath;
        for (int i = 1; i < allPath.length; i++) {
            tmpPath = allPath[i];
            if (tmpPath < shortest) {
                shortest = tmpPath;
            }
        }

        System.out.println(shortest);
    }

    public static void main(String[] args) {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{5});
        list.add(new Integer[]{7, 8});
        list.add(new Integer[]{2, 3, 4});
        list.add(new Integer[]{4, 9, 6, 1});
        list.add(new Integer[]{2, 7, 9, 4, 5});

        new DongtaiguihuaTest().shortestPath(list);
    }

}
