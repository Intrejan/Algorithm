package leetCode;

import java.util.*;

public class LinkedSolution {

    /**
     * K 个一组翻转链表
     *
     * @param head 头节点
     * @param k    K
     * @return 结果
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode prev = hair;
        while (head != null) {
            ListNode tail = prev;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode next = tail.next;
            ListNode[] nodes = reverseNode(head, tail);
            head = nodes[0];
            tail = nodes[1];
            prev.next = head;
            tail.next = next;
            prev = tail;
            head = tail.next;
        }
        return hair.next;
    }

    private ListNode[] reverseNode(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode curr = head;
        while (prev != tail) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return new ListNode[]{tail, head};
    }

    /**
     * 反转链表
     *
     * @param head 头节点
     * @return 新链表头节点
     */
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    /**
     * 合并两个有序链表
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 结果链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        ListNode hair = new ListNode(0);
        ListNode prev = hair;
        while (curr1 != null && curr2 != null) {
            if (curr1.val <= curr2.val) {
                ListNode temp1 = curr1.next;
                prev.next = curr1;
                curr1 = temp1;
            } else {
                ListNode temp2 = curr2.next;
                prev.next = curr2;
                curr2 = temp2;
            }
            prev = prev.next;
        }
        if (curr2 != null) {
            prev.next = curr2;
        } else {
            prev.next = curr1;
        }
        return hair.next;
    }

    static class Status implements Comparable<Status> {

        int val;
        ListNode node;

        Status(int val, ListNode node) {
            this.val = val;
            this.node = node;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    /**
     * 合并K个链表
     *
     * @param lists 链表数组
     * @return 结果链表的头节点
     */
    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.node;
            tail = tail.next;
            if (f.node.next != null) {
                queue.offer(new Status(f.node.next.val, f.node.next));
            }
        }
        return head.next;
    }

    /**
     * 两链表相加
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 和
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode p3 = pre;
        int addition = 0;
        while (p1 != null || p2 != null || addition > 0) {
            int c1 = p1 == null ? 0 : p1.val;
            int c2 = p2 == null ? 0 : p2.val;
            int curr_sum = (c1 + c2 + addition) % 10;
            addition = (c1 + c2 + addition) / 10;
            p3.next = new ListNode(curr_sum);
            p3 = p3.next;
            p1 = p1 == null ? null : p1.next;
            p2 = p2 == null ? null : p2.next;
        }
        return pre.next;
    }

    public static ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode pre = new ListNode();
        ListNode l1 = list1;
        ListNode l2 = list2;
        ListNode l3 = pre;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                l3.next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                l3.next = l1;
                l1 = l1.next;
            } else {
                if (l1.val < l2.val) {
                    l3.next = l1;
                    l1 = l1.next;
                } else {
                    l3.next = l2;
                    l2 = l2.next;
                }
            }
            l3 = l3.next;
        }
        return pre.next;
    }

    public static Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node node = new Node(0);
        Node pre = node;
        Node curr = head;
        while (curr != null) {
            Node n = new Node(curr.val);
            map.put(curr, n);
            node.next = n;
            node = node.next;
            curr = curr.next;
        }
        node = pre.next;
        curr = head;
        while (node != null) {
            if (curr.random == null) {
                node.random = null;
            } else {
                node.random = map.get(curr.random);
            }
            node = node.next;
            curr = curr.next;
        }
        return pre.next;
    }

    public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode start = new ListNode();
        start.next = head;
        ListNode pre = start;
        ListNode curr = head;
        while (curr != null) {
            for (int i = 0; i < k; i++) {
                if (curr == null) {
                    return start.next;
                }
                curr = curr.next;
            }
            pre = reverseK(pre, pre.next, k - 1);
        }
        return start.next;
    }

    public static ListNode reverseK(ListNode pre, ListNode curr, int count) {
        for (int i = 0; i < count; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return curr;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode();
        start.next = head;
        ListNode curr = head;
        Map<Integer, ListNode> map = new HashMap<>();
        map.put(0, start);
        int index = 1;
        while (curr != null) {
            map.put(index, curr);
            index++;
            curr = curr.next;
        }
        index--;
        map.get(index - n).next = map.get(index - n + 1) == null ? null : map.get(index - n + 1).next;
        return start.next;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        ListNode curr = head;
        int length = 0;
        while (curr != null) {
            length++;
            if (curr.next == null) {
                curr.next = head;
                break;
            }
            curr = curr.next;
        }
        k = length == 0 ? 0 : k % length;
        curr = new ListNode();
        curr.next = head;
        for (int i = 0; i < length - k; i++) {
            curr = curr.next;
        }
        head = curr.next;
        curr.next = null;
        return head;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }
        head = null;
        ListNode next = null;
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            if (stack.isEmpty()) {
                node.next = next;
                head = node;
                break;
            }
            if (stack.peek().val != node.val) {
                node.next = next;
                next = node;
                head = node;
                continue;
            }
            while (!stack.isEmpty() && stack.peek().val == node.val) {
                stack.pop();
            }
        }
        return head;
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode curr = head;
        ListNode c1 = new ListNode();
        ListNode c2 = new ListNode();
        ListNode h1 = c1;
        ListNode h2 = c2;
        while (curr != null) {
            if (curr.val < x) {
                c1.next = curr;
                c1 = c1.next;
            } else {
                c2.next = curr;
                c2 = c2.next;
            }
            curr = curr.next;
        }
        c1.next = h2.next;
        c2.next = null;
        return h1.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = null;
        partition(n1, 3);
    }
}

class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}