package study.test1.nowcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author xiachao
 * @date 2021/03/31
 */
public class NowCoderPractice4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        // 注意 while 处理多个 case
        while (in.hasNextLine()) {
            String s = in.nextLine();
            if (s.isEmpty()) {
                return;
            }
            String[] strArray = s.split(" ");
            int n = Integer.parseInt(strArray[0]) / 10;
            int m = Integer.parseInt(strArray[1]);
            Goods[] goods = new Goods[m];
            for (int i = 0; i < m; i++) {
                strArray = in.nextLine().split(" ");
                Goods tmp = new Goods(Integer.parseInt(strArray[2]),
                    Integer.parseInt(strArray[0]) / 10,
                    Integer.parseInt(strArray[1]), new ArrayList<>());
                if (tmp.master > 0) {
                    goods[tmp.master - 1].list.add(tmp);
                }
                goods[i] = tmp;
            }
            // dfs
            //boolean[] visit = new boolean[goods.length];
            //shop(goods, n, goods.length, 0, -1, visit);
            //System.out.println(max);

            // dp
            System.out.println(shopDp(goods, n) * 10);
        }
    }

    static class Goods {

        Goods(int master, int price, int value, List<Goods> list) {
            this.master = master;
            this.price = price;
            this.value = value;
            this.list = list;
        }
        int master;
        int value;
        int price;
        List<Goods> list;
    }

    static int max = 0;

    private static void shop(Goods[] goods, int n, int num, int value, int argMaster, boolean[] visit) {
        if (n < 0) {
            return;
        }
        max = Math.max(max, value);
        if (num <= 0) {
            return;
        }
        for (int i = 0; i < goods.length; i++) {
            if (visit[i]) {
                continue;
            }
            Goods item = goods[i];
            if (item.master > 0 && item.master != argMaster) {
                continue;
            }
            visit[i] = true;
            shop(goods, n - item.price, num - 1, value + item.value * item.price,
                item.master > 0 ? item.master : i + 1, visit);
            visit[i] = false;
        }
    }

    private static int shopDp(Goods[] goods, int n) {
        int[] dp = new int[n + 1];
        for (Goods item : goods) {
            if (item.master > 0) {
                continue;
            }
            // 主件和依赖的的同价格下最大价值
            int[] tmp = new int[n + 1];
            tmp[item.price] = item.price * item.value;
            for (Goods child : item.list) {
                // 最大价值不超过n，max(i) = n - child.price
                for (int i = n - child.price; i >= 0; i--) {
                    if (tmp[i] > 0) {
                        int price = child.price + i;
                        tmp[price] = Math.max(tmp[i] + child.price * child.value, tmp[price]);
                    }
                }
            }
            // dp
            for (int i = n; i >= 0; i--) {
                if (dp[i] == 0 && i != 0) {
                    continue;
                }
                // 选择购买不能超过n，max(j) = n-i
                for (int j = n - i; j >= 0; j--) {
                    if (tmp[j] != 0) {
                        dp[i + j] = Math.max(dp[i] + tmp[j], dp[i + j]);
                    }
                }
            }
        }
        int ret = -1;
        for (int i = n; i >= 0; i--) {
            if (dp[i] != 0) {
                ret = Math.max(ret, dp[i]);
            }
        }
        return ret;
    }

}
