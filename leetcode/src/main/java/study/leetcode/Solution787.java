package study.leetcode;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <a href = "https://leetcode.com/problems/cheapest-flights-within-k-stops/">Cheapest Flights Within K Stops</a>
 * <pre>
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and the destination dst,
 * your task is to find the cheapest price from src to dst with up to k stops.
 * If there is no such route, output -1.
 * </pre>
 *
 * @author xiachao
 * @date 2020/11/11
 */
public class Solution787 {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        recurse(queue, 0, flights, src, dst, K + 1);

        if (queue.isEmpty()) {
            return -1;
        }

        return queue.peek();
    }

    // 深度优先
    private void recurse(PriorityQueue<Integer> queue, int value, int[][] flights, int src, int dst, int k) {
        if (k < 0) {
            return;
        }

        if (src == dst) {
            queue.add(value);
            return;
        }

        for (int[] flight : flights) {
            if (flight[0] == src) {
                // 提前结束
                Integer tmp = queue.peek();
                if (tmp != null && tmp < value + flight[2]) {
                    continue;
                }
                recurse(queue, value + flight[2], flights, flight[1], dst, k - 1);
            }
        }
    }

    //https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/115541/JavaPython-Priority-Queue-Solution

    // 动态规划
    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1], f[2]);
        }
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        pq.add(new int[] {0, src, k + 1});
        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet()) {
                    pq.add(new int[] {price + adj.get(a), a, stops - 1});
                }
            }
        }
        return -1;
    }

    // 3
    // [[0,1,100],[1,2,100],[0,2,500]]
    // 0
    // 2
    // 0

    public static void main(String[] args) {
        //String s = "[[0,12,28],[5,6,39],[8,6,59],[13,15,7],[13,12,38],[10,12,35],[15,3,23],[7,11,26],[9,4,65],[10,2,38],[4,7,7],[14,15,31],[2,12,44],[8,10,34],[13,6,29],[5,14,89],[11,16,13],[7,3,46],[10,15,19],[12,4,58],[13,16,11],[16,4,76],[2,0,12],[15,0,22],[16,12,13],[7,1,29],[7,14,100],[16,1,14],[9,6,74],[11,1,73],[2,11,60],[10,11,85],[2,5,49],[3,4,17],[4,9,77],[16,3,47],[15,6,78],[14,1,90],[10,5,95],[1,11,30],[11,0,37],[10,4,86],[0,8,57],[6,14,68],[16,8,3],[13,0,65],[2,13,6],[5,13,5],[8,11,31],[6,10,20],[6,2,33],[9,1,3],[14,9,58],[12,3,19],[11,2,74],[12,14,48],[16,11,100],[3,12,38],[12,13,77],[10,9,99],[15,13,98],[15,12,71],[1,4,28],[7,0,83],[3,5,100],[8,9,14],[15,11,57],[3,6,65],[1,3,45],[14,7,74],[2,10,39],[4,8,73],[13,5,77],[10,0,43],[12,9,92],[8,2,26],[1,7,7],[9,12,10],[13,11,64],[8,13,80],[6,12,74],[9,7,35],[0,15,48],[3,7,87],[16,9,42],[5,16,64],[4,5,65],[15,14,70],[12,0,13],[16,14,52],[3,10,80],[14,11,85],[15,2,77],[4,11,19],[2,7,49],[10,7,78],[14,6,84],[13,7,50],[11,6,75],[5,10,46],[13,8,43],[9,10,49],[7,12,64],[0,10,76],[5,9,77],[8,3,28],[11,9,28],[12,16,87],[12,6,24],[9,15,94],[5,7,77],[4,10,18],[7,2,11],[9,5,41]]";

        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};

        int src = 0;
        int dst = 2;
        int k = 0;

        System.out.println(new Solution787().findCheapestPrice(3, flights, src, dst, k));
    }

    // 2维数组形式
//    private void recurse(PriorityQueue<Integer> queue, int value, int[][] flights, int src, int dst, int k) {
//        if (k == 0) {
//            if (value > 0) {
//                queue.add(value + flights[src][dst]);
//            }
//            return;
//        }
//        if (src == dst) {
//            if (value > 0) {
//                queue.add(value);
//            }
//            return;
//        }
//
//        int i = src + 1;
//        for (; i < dst; i++) {
//            if (flights[src][i] == 0) continue;
//            recurse(queue, value + flights[src][i], flights, i, dst, k - 1);
//        }
//        recurse(queue, value + flights[src][i], flights, i, dst, k);
//    }

}
