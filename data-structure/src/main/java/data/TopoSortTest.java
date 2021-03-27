package data;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author xiachao
 * @date 2020/06/04
 */
public class TopoSortTest {

    // 顶点个数
    private int v;

    // 邻接表
    private LinkedList<Integer>[] adj;

    // 依赖表（邻接表反转）
    private LinkedList<Integer>[] reverseAdj;

    private Map<Integer, String> map;

    public TopoSortTest(Map<Integer, String> map) {
        this(map.size());
        this.map = map;
    }

    public TopoSortTest(int v) {
        this.v = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
    }

    // 邻接表返转，为依赖表
    private void convertToReverseAdj() {
        reverseAdj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            reverseAdj[i] = new LinkedList<>();
        }
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                reverseAdj[adj[i].get(j)].add(i);
            }
        }
    }

    // 依赖表反转为邻接表
    public void convertToAdj() {
        LinkedList[] tmp = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            tmp[i] = new LinkedList<>();
        }
        for (int i = 0; i < reverseAdj.length; i++) {
            for (int j = 0; j < reverseAdj[i].size(); j++) {
                tmp[reverseAdj[i].get(j)].add(i);
            }
        }
        adj = tmp;
    }

    // 拓补排序kahn算法，贪心算法
    public void topoSortKahn() {
        // 每个节点的入度
        int[] indeep = new int[v];
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int k = adj[i].get(j);
                indeep[k]++;
            }
        }

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indeep.length; i++) {
            if (indeep[i] == 0) {
                // 入度为0没有依赖节点
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int w = queue.remove();
            for (int i = 0; i < adj[w].size(); i++) {
                int k = adj[w].get(i);
                if (--indeep[k] == 0) {
                    // 依赖节点移除，入度减少1，入度为0没有依赖节点后也可以移除
                    queue.add(k);
                }
            }
            if (map != null) {
                System.out.print("->" + map.get(w));
            } else {
                System.out.print("->" + w);
            }
        }
    }

    // 拓补排序，dfs算法
    public void topoSortDfs() {
        boolean[] visited = new boolean[v];

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                outputDfs(i, visited);
            }
        }
    }

    private void outputDfs(int i, boolean[] visited) {
        // 先输出节点i的依赖节点，如果节点i依赖节点reverseAdj[i]有依赖节点，同样先输出
        for (int j = 0; j < reverseAdj[i].size(); j++) {
            int k = reverseAdj[i].get(j);
            if (!visited[k]) {
                visited[k] = true;
                outputDfs(k, visited);
            }
        }
        if (map != null) {
            System.out.print("->" + map.get(i));
        } else {
            System.out.print("->" + i);
        }
    }

    // 拓补排序，bfs算法
    public void topoSortBfs() {
        boolean[] visited = new boolean[v];

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                outputBfs(i, visited);
            }
        }
    }

    private void outputBfs(int i, boolean[] visited) {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < reverseAdj[i].size(); j++) {
            int k = reverseAdj[i].get(j);
            if (!visited[k]) {
                // bfs 先表示已经访问，后续直接跳过访问
                visited[k] = true;
                list.add(k);
            }
        }
        for (Integer item : list) {
            outputBfs(item, visited);
        }
        // out put
        if (map != null) {
            System.out.print("->" + map.get(i));
        } else {
            System.out.print("->" + i);
        }
    }

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "起床");
        map.put(1, "刷牙");
        map.put(2, "穿裤子");
        map.put(3, "穿上衣");
        map.put(4, "戴眼镜");
        map.put(5, "吃早餐");
        map.put(6, "点外卖");
        map.put(7, "穿鞋子");
        map.put(8, "打游戏");

        TopoSortTest test = new TopoSortTest(map);

        // 起床>其他（除点外卖和打游戏）
        test.addEdge(0, 1);
        test.addEdge(0, 2);
        test.addEdge(0, 3);
        test.addEdge(0, 4);
        test.addEdge(0, 5);
        test.addEdge(0, 7);
        // 刷牙>吃早餐
        test.addEdge(1, 5);
        // 点外卖>吃早餐
        test.addEdge(6, 5);
        // 穿裤子>穿鞋子
        test.addEdge(2, 7);
        // 戴眼镜>打游戏
        test.addEdge(4, 8);

        test.convertToReverseAdj();

        System.out.println("\r\n==============\r\n");
        test.topoSortKahn();

        System.out.println("\r\n==============\r\n");

        test.topoSortDfs();

        System.out.println("\r\n==============\r\n");

        test.topoSortBfs();

        System.out.println("\r\n==============\r\n");



    }

}
