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
//        List<Integer[]> list = new ArrayList<>();
//        list.add(new Integer[]{5});
//        list.add(new Integer[]{7, 8});
//        list.add(new Integer[]{2, 3, 4});
//        list.add(new Integer[]{4, 9, 6, 1});
//        list.add(new Integer[]{2, 7, 9, 4, 5});
//
//        new DongtaiguihuaTest().shortestPath(list);

        DongtaiguihuaTest test = new DongtaiguihuaTest();
        test.leastCoinRecursion(0,0);
        System.out.println(test.leastNum);

        System.out.println(test.leastCoinPhase());
    }


    // 回溯算法实现 - 定义状态 - 画递归树 - 找重复子问题 - 画状态转移表 - 根据递推关系填表 - 将填表过程翻译成代码。

    // 硬币找零问题。假设我们有几种不同币值的硬币 v1，v2，……，vn（单位是元）。如果我们要支付 w 元，求最少需要多少个硬币。
    // 比如，我们有 3 种不同的硬币，1 元、3 元、5 元，我们要支付 9 元，最少需要 3 个硬币（3 个 3 元的硬币）。

    // 回溯
    int[] coins = {1, 3, 5};
    int pay = 9;
    int leastNum;

    // 重复的剔除
    boolean[][] hasCal = new boolean[pay + 1][pay + 1];

    private void leastCoinRecursion(int v, int count) {// 调用f(0, 0)
        hasCal[v][count] = true;
        if (v == pay) {
            if (leastNum == 0 || leastNum > count) {
                leastNum = count;
            }
            return;
        }
        int tmp;
        for (int coin : coins) {
            tmp = v + coin;
            if (tmp <= pay) {
                if (!hasCal[tmp][count + 1]) {
                    leastCoinRecursion(tmp, count + 1);
                }
            }
        }
    }

    // 动态规划

    private int leastCoinPhase() {
        // 第0阶段
        boolean[] phase = new boolean[pay + 1];

        phase[0] = true;

        int k = 0;

        int tmp;
        while (!phase[pay] && k <= pay) {
            for (int i = pay - 1; i >= 0; i--) {
                if (phase[i]) {
                    // clear before phase
                    phase[i] = false;
                    // fill current phase
                    for(int coin : coins) {
                        tmp = i + coin;
                        // Integer Max value
                        if (tmp <= pay && tmp > 0) {
                            phase[tmp] = true;
                        }
                    }
                }
            }
            k++;
        }

        if (k > pay) {
            return -1;
        }

        return k;
    }


}
