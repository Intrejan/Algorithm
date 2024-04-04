package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存机制
 */
public class LRUCache {
    Map<Integer, LinkedNode> map;
    LinkedNode start;
    LinkedNode end;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        start = new LinkedNode();
        end = new LinkedNode();
        start.next = end;
        end.prev = start;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            LinkedNode node = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        LinkedNode node;
        if (map.containsKey(key)) {
            node = map.get(key);;
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            node = new LinkedNode();
            node.key = key;
            node.val = value;
            map.put(key, node);
            addToHead(node);
            if (map.size() > capacity) {
                map.remove(end.prev.key);
                removeNode(end.prev);
            }
        }
    }

    void removeNode(LinkedNode node) {
        LinkedNode prev = node.prev;
        LinkedNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    void addToHead(LinkedNode node) {
        LinkedNode head = start.next;
        start.next = node;
        node.prev = start;
        node.next = head;
        head.prev = node;
    }


    public static void main(String[] args){
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // 缓存是 {1=1}
        lRUCache.put(1, 2); // 缓存是 {1=1, 2=2}
        lRUCache.put(2, 3);
        lRUCache.put(4, 1); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(1));      // 返回 -1 (未找到)
        System.out.println(lRUCache.get(2));      // 返回 3
    }
}

class LinkedNode{
    int key;
    int val;
    LinkedNode prev;
    LinkedNode next;
    public LinkedNode(){

    }
}