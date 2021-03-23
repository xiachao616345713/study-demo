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
public class DynamicProgrammingTest {


    static class TestShortestPath {

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

            new TestShortestPath().shortestPath(list);
        }

    }


    static class TestChangeCoin {
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
        private int leastCoinDp() {
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
                        for (int coin : coins) {
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

        public static void main(String[] args) {
            TestChangeCoin test = new TestChangeCoin();
            test.leastCoinRecursion(0, 0);
            System.out.println(test.leastNum);

            System.out.println(test.leastCoinDp());
        }
    }


    static class TestCalMaxSequence {

        // 我们有一个数字序列包含 n 个不同的数字，如何求出这个序列中的最长递增子序列长度？
        // 比如 2, 9, 3, 6, 5, 1, 7 这样一组数字序列，它的最长递增子序列就是 2, 3, 5, 7，所以最长递增子序列的长度是 4。
        int[] nums = {2, 9, 3, 6, 5, 1, 7};
        int maxSequence = 0;

        void calMaxSubSequence(int sequence, int num, int index) {
            if (sequence > maxSequence) {
                maxSequence = sequence;
            }
            for (int i = index; i < nums.length; i++) {
                // 跳过当前，直接处理下一个
                calMaxSubSequence(sequence, num, i + 1);
                // 有序，可以有序+1，再处理下一个
                if (num <= nums[i]) {
                    calMaxSubSequence(sequence + 1, nums[i], i + 1);
                }
            }
        }

        // 状态转移   (1,2)表示字串长度为1，字串尾数为2
        // (0, 0)
        // -> (0, 0),(1, 2)
        // -> (0, 0),(1, 9),(1,2),(2,9)
        // (1,9)和(1,2)重复，去掉尾数大(1,9)的留下(1,2)最后剩下
        // (0, 0),(1,2),(2,9)
        // -> (0, 0),(1,3),(1,2),(2,3),(2,9)
        // (1,3)和(1,2)重复，(2,3)和(2,9)重复，去掉尾数大大

        int calMaxSubSequenceDp() {
            int[] phase = new int[nums.length + 1];

            for (int i = 1; i <= nums.length; i++) {
                phase[i] = -1;
            }

            for (int num : nums) {
                // 最后一个num处理完，phase[nums.length]中才可能有值
                for (int j = nums.length - 1; j >= 0; j--) {
                    // 有状态
                    if (phase[j] >= 0) {
                        // 尾数大于当前值，直接跳过
                        if (phase[j] > num) {
                            // 跳过处理
                            continue;
                        }
                        // 尾数小于当前值 temp <= nums[i]，同时下一阶段尾数取小的
                        if (phase[j + 1] < 0 || phase[j + 1] > num) {
                            // 序列+1，尾数变更
                            phase[j + 1] = num;
                        }
                    }
                }
            }

            for (int i = nums.length; i > 0; i--) {
                if (phase[i] > 0) {
                    return i;
                }
            }
            return 1;
        }

        int calMaxSubSequenceDpTest() {
            int[] phase = new int[nums.length + 1];

            for (int num : nums) {
                // 最后一个num处理完，phase[nums.length]中才可能有值
                for (int j = nums.length - 1; j >= 0; j--) {
                    // 无状态
                    if (phase[j] == 0 && j != 0) {
                        continue;
                    }
                    // 尾数大于当前值，直接跳过
                    if (phase[j] > num) {
                        // 跳过处理
                        continue;
                    }
                    // 尾数小于当前值 temp <= nums[i]，同时下一阶段尾数取小的
                    if (phase[j + 1] == 0 || phase[j + 1] > num) {
                        // 序列+1，尾数变更
                        phase[j + 1] = num;
                    }
                }
            }

            for (int i = nums.length; i > 0; i--) {
                if (phase[i] > 0) {
                    return i;
                }
            }
            return 1;
        }

        public static void main(String[] args) {
            TestCalMaxSequence test = new TestCalMaxSequence();
            test.calMaxSubSequence(0, -1, 0);

            System.out.println(test.maxSequence);

            System.out.println("最长递增子序列的长度为" + test.calMaxSubSequenceDp());

            System.out.println("最长递增子序列的长度为" + test.calMaxSubSequenceDpTest());
        }
    }

    static class TestLongestPalindromeSubStr {
        // 最长回文字串
        String longestPalindromeSubStr(String str) {
            int length = str.length();
            boolean[][] dp = new boolean[length][length];
            char[] chars = str.toCharArray();
            String ret = null;

            for (int i = length - 1; i >= 0; i--) {
                for (int j = i; j < length; j++) {
                   dp[i][j] = chars[i] == chars[j] && (j - i < 3 || dp[i + 1][j - 1]);
                   if (dp[i][j] && (ret == null || (j - i + 1) > ret.length())) {
                       ret = str.substring(i, j + 1);
                   }
                }
            }

            return ret;
        }

        public static void main(String[] args) {
            TestLongestPalindromeSubStr test = new TestLongestPalindromeSubStr();

            System.out.println(test.longestPalindromeSubStr("bgcabcbacbaxacgb"));
        }

    }


}
