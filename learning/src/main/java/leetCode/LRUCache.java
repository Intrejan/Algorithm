package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存机制
 */
public class LRUCache {
    //当前容量
    private int size;
    //最大容量
    private int capacity;
    //开始节点
    private LinkNode head;
    //最终节点
    private LinkNode tail;
    //缓存哈希map
    private Map<Integer,LinkNode> cache = new HashMap<>();

    //初始化，参数为最大容量
    public LRUCache(int capacity) {
        this.capacity = capacity;
        //当前容量为0
        this.size = 0;
        this.head = new LinkNode();
        this.tail = new LinkNode();
        //初始化使用伪头部和伪尾部节点
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        LinkNode node = cache.get(key);
        //该键不存在
        if(node== null){
            return -1;
        }else {
            //将该节点移动到头部
            moveToHead(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        LinkNode node = cache.get(key);
        //该键不存在
        if(node == null){
            //创建新的节点
            LinkNode newNode = new LinkNode(key,value);
            //添加缓存
            cache.put(key,newNode);
            //添加队列
            addToHead(newNode);
            ++size;
            //如果超出阈值
            if(size>capacity){
                // 如果超出容量，删除双向链表的尾部节点
                LinkNode tail = removeTail();
                //删除缓存
                cache.remove(tail.key);
                --size;
            }
        }
        //该值存在
        else{
            //修改值，并移动到头部
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 删除尾部节点
     * @return node
     */
    private LinkNode removeTail() {
        LinkNode node = tail.prev;
        removeNode(node);
        return node;
    }

    /**
     * 新添加一个节点到头部
     * @param node 要添加的节点
     */
    private void addToHead(LinkNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }


    /**
     * 将节点移动到链表头部表示最近被使用
     * @param node 被访问的节点
     */
    private void moveToHead(LinkNode node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 删除指定节点
     * @param node 要删除的节点
     */
    private void removeNode(LinkNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    public static void main(String[] args){
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));      // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));      // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));      // 返回 3
        System.out.println(lRUCache.get(4));     // 返回 4
    }
}

class LinkNode{
    int key;
    int value;
    LinkNode prev;
    LinkNode next;
    public LinkNode(int key,int value){
        this.key = key;
        this.value = value;
    }

    public LinkNode() { }
}