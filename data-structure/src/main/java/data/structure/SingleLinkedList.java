package data.structure;

/**
 * @author chao
 * @since 2018-12-25
 */
public class SingleLinkedList<T> {

    public SingleLinkedList() {
        dummy = new Node();
    }

    private Node dummy;

    public Node getHead() {
        return dummy.next;
    }

    /**
     * insert head
     */
    public boolean insertHead(T value) {
        Node node = new Node(value);
        node.next = dummy.next;
        dummy.next = node;
        return true;
    }

    /**
     * insert tail
     */
    public boolean insertTail(T value) {
        Node node = new Node(value);
        Node tail = dummy;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        return true;
    }

    /**
     * insert index
     * @param value value
     * @param index index>0 正序插入,index<0 反序插入
     */
    public boolean insertIndex(T value, int index) {
        Node pre = null;
        if (index > 0) {
            pre = dummy;
            for (int i = 1; i < index && pre.next != null; i++) {
                pre = pre.next;
            }
        } else if (index < 0) {
            Node fast = dummy;
            for (int i = 0; i > index && fast.next != null; i--) {
                fast = fast.next;
            }
            if (fast.next == null) {
                pre = dummy;
            } else {
                Node slow = dummy;
                while (fast.next != null) {
                    fast.next = fast.next.next;
                    slow.next = slow.next.next;
                }
                pre = slow;
            }
        }
        if (pre != null) {
            Node node = new Node(value);
            node.next = pre.next;
            pre.next = node;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 反转
     */
    public boolean reverse() {
        /*
         * method1 直接反转
         * dummy->1->2->3->4
         * step1: dummy->2->1->3->4
         * step2: dummy->3->2->1->4
         * step3: dummy->4->3->2->1
         */
        Node pre = dummy.next;
        Node cur;
        while (pre.next != null) {
            cur = pre.next;
            pre.next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
        }
        /*
         * method2 重新插入
         * dummy->1->2->3->4
         * step1: dummy->1
         * step2: dummy->2->1
         * step3: dummy->3->2->1
         * step4: dummy->4->3->2->1
         */
//        Node head = dummy.next;
//        dummy.next = null;
//        while (head != null) {
//            head.next = dummy.next;
//            dummy.next = head;
//            head = head.next;
//        }

        return true;
    }

    /**
     * 中间节点
     */
    public Node middleNode() {
        Node fast = dummy.next, slow = dummy.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public class Node {

        Node() {
        }

        Node(T value) {
            this.value = value;
        }

        T value;
        Node next;

        public Node getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.insertTail(1);
        list.insertTail(2);
        list.insertTail(3);
        list.insertTail(4);

        SingleLinkedList.Node node = list.getHead();
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
        System.out.println("======middle node=======");
        System.out.println(list.middleNode().getValue());

        System.out.println("======= reverse ======");
        list.reverse();
        node = list.getHead();
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }
}
