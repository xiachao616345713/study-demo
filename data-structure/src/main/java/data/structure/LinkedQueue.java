package data.structure;

/**
 * @author chao
 * @since 2018-12-25
 */
public class LinkedQueue<T> {

    private Node head;

    private Node tail;

    public boolean enqueue(T value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }

        return true;
    }

    public T dequeue() {
        if (head != null) {
            T value = head.value;
            head = head.next;
            return value;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public class Node {
        private T value;
        private Node next;

        Node(){}
        Node(T value) {this.value = value;}

        public T getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("aaa");
        queue.enqueue("bbb");
        queue.enqueue("ccc");
        queue.enqueue("ddd");
        System.out.println("queue is empty:" + queue.isEmpty());

        String queueValue = queue.dequeue();
        while (queueValue != null) {
            System.out.println(queueValue);
            queueValue = queue.dequeue();
        }

        System.out.println("queue is empty:" + queue.isEmpty());
    }
}
