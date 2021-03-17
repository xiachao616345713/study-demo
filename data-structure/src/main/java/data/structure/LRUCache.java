package data.structure;


import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * lru map
 *
 * @author xiachao
 * @date 2021/03/17
 */
public class LRUCache<T> {

    @Getter
    @Setter
    static class Node<T> {

        public Node(T value) {
            this.value = value;
        }

        private T value;
        private Node<T> pre;
        private Node<T> next;
    }

    private final Map<String, Node<T>> map;
    private Node<T> head;

    public LRUCache() {
        map = new HashMap<>();
        head = null;
    }

    public void put(String key, T item) {
        Node<T> node = map.get(key);
        if (node == null) {
            node = new Node<>(item);
        } else {
            node.value = item;
        }
        map.put(key, node);

        lru(node);
    }

    private void lru(Node<T> node) {
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        node.next = head;
        head = node;
    }

    public T get(String key) {
        Node<T> node = map.get(key);
        if (node == null) {
            return null;
        }

        lru(node);

        return node.value;
    }

    public T lastest() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    public static void main(String[] args) {
        LRUCache<String> cache = new LRUCache<>();
        cache.put("1", "2");
        cache.put("11", "22");
        cache.put("111", "222");
        cache.put("11", "22");

        System.out.println(cache.lastest());

        System.out.println(cache.get("111"));

        System.out.println(cache.lastest());

        cache.put("lastest", "lastest");
        System.out.println(cache.lastest());


    }

}
