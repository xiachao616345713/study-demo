package data.structure;

/**
 * @author chao
 * @since 2018-12-25
 */
public class SingleLinkedList<T> {

    public SingleLinkedList() {
    }

    private Node head;

    public Node getHead() {
        return head;
    }

    /**
     * insert head
     */
    public boolean insertHead(T value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
        return true;
    }

    /**
     * insert tail
     */
    public boolean insertTail(T value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            return true;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
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
            pre = head;
            for (int i = 1; i < index && pre != null; i++) {
                pre = pre.next;
            }
        } else if (index < 0) {
            Node fast = head;
            for (int i = 0; i > index && fast != null; i--) {
                fast = fast.next;
            }
            if (fast == null) {
                return false;
            }
            Node slow = head;
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            pre = slow;
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
         * 1->2->3->4
         * step1: 2->1->3->4
         * step2: 3->2->1->4
         * step3: 4->3->2->1
         */
        Node temp;
        Node cur = head;
        while (cur.next != null) {
            temp = cur.next;
            cur.next = temp.next;
            temp.next = head;
            head = temp;
        }
        /*
         * method2 重新插入
         * 1->2->3->4
         * step1: 1
         * step2: 2->1
         * step3: 3->2->1
         * step4: 4->3->2->1
         */
//        Node temp = head;
//        SingleLinkedList newList = new SingleLinkedList();
//        while (temp != null) {
//            newList.insertHead(temp);
//            temp = temp.next;
//        }
//        head = newList.getHead();

        return true;
    }

    public void reverse1() {
        Node node = head;
        Node tmp;
        while (node.next != null) {
            tmp = node.next;
            node.next = node.next.next;
            tmp.next = head;
            head = tmp;
        }
    }

    /**
     * 中间节点
     */
    public Node middleNode() {
        Node fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 是否回文
     */
    public boolean isPalindrome() {
        if (head == null || head.next == null) {
            return false;
        }
        Node fast = head.next.next, slow = head, temp;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            // 反转
            temp = slow.next;
            slow.next = temp.next;
            temp.next = head;
            head = temp;
        }
        if (fast == null) {
            // 偶数，直接对比
            return palindromeCompare(head, slow.next);
        } else {
            // 基数，从下一个开始对比
            return palindromeCompare(head, slow.next.next);
        }
    }

    public boolean isPalindrome1() {
        Node slow = head, fast = head;
        Node tmp;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            tmp = slow.next;
            slow.next = slow.next.next;
            tmp.next = head;
            head = tmp;
        }
        slow = slow.next;
        if (fast != null) {
            // 奇数
            head = head.next;
        }
        while (slow != null) {
            if (!slow.value.equals(head.value)) {
                return false;
            }
            slow = slow.next;
            head = head.next;
        }
        return true;
    }

    public void deleteReverseNode(int n) {
        Node node = head;
        while (n > 0 && node != null) {
            node = node.next;
            n--;
        }
        if (n > 0) {
            return;
        }
        if (node == null) {
            head = head.next;
            return;
        }
        Node tmp = head;
        while (node.next != null) {
            node = node.next;
            tmp = tmp.next;
        }

    }

    // 回文对比
    private boolean palindromeCompare(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if (!head1.value.equals(head2.value)) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
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
        list.insertTail(9);
        list.insertTail(4);
        list.insertTail(3);
        list.insertTail(2);
        list.insertTail(1);

        SingleLinkedList.Node node = list.getHead();
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
        System.out.println("======middle node=======");
        System.out.println(list.middleNode().getValue());

        System.out.println("======= reverse ======");
        list.reverse1();
        node = list.getHead();
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }

        System.out.println("======= palindrome ======");
        System.out.println(list.isPalindrome());

        System.out.println("======= travel ======");
        node = list.getHead();
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }
}
