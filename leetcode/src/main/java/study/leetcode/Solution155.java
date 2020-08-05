package study.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * @author xiachao
 * @date 2020/08/05
 */
public class Solution155 {

    private static class MinStack {

        private Deque<Integer> elements;

        private TreeMap<Integer, Integer> sortedMap;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            elements = new ArrayDeque<>();
            sortedMap = new TreeMap<>();
        }

        public void push(int x) {
            elements.addLast(x);
            sortedMap.compute(x, (k, v) -> {
                if (v == null) {
                    return 1;
                }
                return v + 1;
            });
        }

        public void pop() {
            Integer x = elements.removeLast();
            sortedMap.compute(x, (k, v) -> {
                if (v == null || v == 1) {
                    return null;
                }
                return v - 1;
            });
        }

        public int top() {
            return elements.getLast();
        }

        public int getMin() {
            return sortedMap.firstKey();
        }

    }

    /**
     * <ul>抄别人的</ul>
     * <li>node 保存val, min, next</li>
     * <li>插入每次放到head</li>
     * <li>插入head时，每次和当前head最小对比，保存最小的(因为每次删除只能删除head，所以不会出现当前保存最小的节点被删了还存在)</li>
     */
    private static class MinStack1 {

        private Node head;

        /**
         * initialize your data structure here.
         */
        public MinStack1() {
        }

        public void push(int x) {
            if (head == null) {
                head = new Node(x, x, null);
            } else {
                head = new Node(x, Math.min(head.min, x), head);
            }
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }

        private class Node {
            private int val;
            private int min;
            private Node next;

            Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }

    public static void main(String[] args) {
        //MinStack minStack = new MinStack();
        MinStack1 minStack = new MinStack1();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }

}
