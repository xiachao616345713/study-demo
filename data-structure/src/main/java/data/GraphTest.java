package data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图
 *
 * @author xiachao
 * @date 2020/06/21
 */
public class GraphTest {

    // 顶点个数
    private int v;

    // 邻接表
    private LinkedList<Integer> adj[];

    public GraphTest(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    public void bfs(int s, int t) {
        int[] pre = new int[v];
        for (int i = 0; i < v; i++) {
            pre[i] = -1;
        }

        boolean[] visited = new boolean[v];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        boolean flag = false;
        while (!queue.isEmpty() && !flag) {
            int tmp = queue.remove();
            for (Integer item : adj[tmp]) {
                if (item == t) {
                    flag = true;
                    pre[item] = tmp;
                    break;
                }
                if (!visited[item]) {
                    queue.add(item);
                    visited[item] = true;
                    pre[item] = tmp;
                }
            }
        }

        while (t != s) {
            System.out.print(t + "<-");
            t = pre[t];
        }
        System.out.println(s);
    }

    public void dfs(int s, int t) {
        int[] pre = new int[v];
        for (int i = 0; i < v; i++) {
            pre[i] = -1;
        }

        boolean[] visited = new boolean[v];

        visited[s] = true;

        dfs(pre, visited, s, t);

        while (t != s) {
            System.out.print(t + "<-");
            t = pre[t];
        }
        System.out.println(s);
    }

    private boolean dfs(int[] pre, boolean[] visited, int s, int t) {
        for (Integer item : adj[s]) {
            if (item == t) {
                pre[item] = s;
                return true;
            }
            if (!visited[item]) {
                visited[item] = true;
                pre[item] = s;
                if (dfs(pre, visited, item, t)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <pre>
     *     0 —— 1 —— 2
     *     ｜   ｜   ｜
     *     3 —— 4 —— 5 —— 8
     *          ｜   ｜   ｜
     *          6 —— 7 —— 9
     *          ｜        ｜
     *          10 — 11 — 12
     * </pre>
     */
    public static void main(String[] args) {
        GraphTest test = new GraphTest(13);
        test.addEdge(0, 1);
        test.addEdge(0, 3);
        test.addEdge(1, 2);
        test.addEdge(1, 4);
        test.addEdge(3, 4);
        test.addEdge(2, 5);
        test.addEdge(4, 4);
        test.addEdge(4, 6);
        test.addEdge(5, 7);
        test.addEdge(5, 8);
        test.addEdge(6, 7);
        test.addEdge(6, 10);
        test.addEdge(7, 9);
        test.addEdge(7, 9);
        test.addEdge(10, 11);
        test.addEdge(11, 12);
        test.addEdge(9, 12);

        System.out.println("========= bfs ==========");

        test.bfs(1, 11);
        test.bfs(1, 12);
        test.bfs(2, 11);
        test.bfs(2, 12);

        System.out.println("========= dfs ==========");

        test.dfs(1, 11);
        test.dfs(1, 12);
        test.dfs(2, 11);
        test.dfs(2, 12);

        System.out.println("========= ==========");
    }

}
