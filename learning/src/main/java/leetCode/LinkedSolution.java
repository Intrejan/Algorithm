package leetCode;

import java.util.*;

public class LinkedSolution {
    /**
     * K 个一组翻转链表
     * @param head 头节点
     * @param k K
     * @return 结果
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode prev = hair;
        while(head!=null){
            ListNode tail = prev;
            for(int i=0;i<k;i++){
                tail = tail.next;
                if(tail==null){
                    return hair.next;
                }
            }
            ListNode next = tail.next;
            ListNode[] nodes = reverseNode(head,tail);
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
        while (prev!=tail){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return new ListNode[]{tail,head};
    }

    /**
     * 反转链表
     * @param head 头节点
     * @return 新链表头节点
     */
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr!=null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    /**
     * 合并两个有序链表
     * @param l1 链表1
     * @param l2 链表2
     * @return 结果链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        ListNode hair = new ListNode(0);
        ListNode prev = hair;
        while (curr1!=null && curr2!=null){
            if(curr1.val<=curr2.val){
                ListNode temp1 = curr1.next;
                prev.next = curr1;
                curr1 = temp1;
            }else {
                ListNode temp2 = curr2.next;
                prev.next = curr2;
                curr2 = temp2;
            }
        prev = prev.next;
        }
        if(curr2!=null){
            prev.next = curr2;
        }else {
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
     * @param lists 链表数组
     * @return 结果链表的头节点
     */
    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode node: lists) {
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
     * @param l1 链表1
     * @param l2 链表2
     * @return 和
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public static void main(String[] args){
        LinkedSolution linkedSolution = new LinkedSolution();
        //System.out.println(linkedSolution.generateParenthesis(2));
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