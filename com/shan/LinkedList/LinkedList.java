package com.LeetCode.com.shan.LinkedList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;


/**
 * @Author: SHAN
 * @Description:
 * @Date: created in 9:54 2022/6/15
 */
public class LinkedList {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        deleteDuplicates3(listNode);


    }

    /**
     * 381.将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public static ListNode mergeTwoLists(ListNode linked1, ListNode linked2) {
        if (linked1 == null) return linked2;
        if (linked2 == null) return linked1;
        ListNode temp = new ListNode(0);
        ListNode cur = temp;
        while (linked1 != null && linked2 != null) {
            if (linked1.val > linked2.val) {
                cur.next = linked2;
                linked2 = linked2.next;
            } else {
                cur.next = linked1;
                linked1 = linked1.next;
            }
            cur = cur.next;
        }
        cur.next = linked1 == null ? linked2 : linked1;
        return temp.next;
    }

    /**
     * 386.给一个链表，返回每个节点下一个比他大的值，如果不存在就返回0 。
     */
    public static int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> integers = new ArrayList<>();
        while (head != null) {
            integers.add(head.val);
            head = head.next;
        }
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            while (!stack.empty() && integers.get(stack.peek()) < integers.get(i)) {
                int index = stack.pop();
                res[index] = integers.get(i);
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 410.输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     */
    public static int[] reversePrint(ListNode head) {
        Stack<ListNode> listNodes = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            listNodes.push(temp);
            temp = temp.next;
        }
        int size = listNodes.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = listNodes.pop().val;
        }
        return res;
    }

    /**
     * 429.给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。 返回删除后的链表的头节点。
     */
    public static ListNode deleteNode(ListNode head, int val) {
        if (head == null) return head;
        if (head.val == val) return head.next;
        ListNode cur = head;
        while (cur.next != null && cur.next.val != val) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return head;
    }

    /**
     * 431.输入一个链表，输出该链表中倒数第 k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode first = head;
        ListNode last = head;
        while (k-- > 0) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            last = last.next;
        }
        return last;
    }

    /**
     * 432.定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode node = stack.pop();
        ListNode dummy = node;
        while (!stack.isEmpty()) {
            node.next = stack.pop();
            node = node.next;
        }
        node.next = null;
        return dummy;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    public static ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode reserve = reverseList3(next);
        next.next = head;
        head.next = null;
        return reserve;
    }

    /**
     * 459.给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode last = head;
        while (n-- > 0) {
            first = first.next;
        }
        while (first.next != null) {
            first = first.next;
            last = last.next;
        }
        last.next = last.next.next;
        return head;
    }


    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode pre = head;
        int last = length(head) - n;
        if (last == 0) return head.next;
        for (int i = 0; i < last - 1; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    private int length(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }


    /**
     * 449.给定一个链表，判断链表中是否有环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引 从 0 开始）。如果 pos 是 -1，则在该链表中没有环。
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    //存放集合 略

    //逐个删除
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        if (head == head.next) {
            return true;
        }
        ListNode temp = head.next;
        head.next = head;
        return hasCycle2(temp);
    }


    /**
     * 460.给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
     * 为了表示给定链表中的环，我们使用整数pos 来表示链表尾连接到链表中的位置（索引 从 0 开始）。
     * 如果pos是-1，则在该链表中没有环。
     * 注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     * 说明：不允许修改给定的链表。
     */
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * 461.给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second;
        ListNode third;
        ListNode last = null;
        ListNode newHead = head.next;
        while (first != null && first.next != null) {
            second = first.next;
            third = second.next;
            first.next = third;
            second.next = first;

            if (last != null) {
                last.next = second;
            }

            last = first;
            first = third;

        }
        return newHead;
    }

    //递归
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode third = swapPairs2(head.next.next);
        ListNode second = head.next;
        head.next = third;
        second.next = head;
        return second;
    }


    //直接交换节点值 略

    /**
     * 462.输入两个链表，找出它们的第一个公共节点。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode a = headA;
        ListNode b = headB;
        while (a != null) {
            set.add(a);
            a = a.next;
        }
        while (b != null) {
            if (set.add(b)) return b;
            b = b.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }


    /**
     * 请判断一个链表是否为回文链表。链表为单向无环链表
     */
    public static boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
            len++;
        }
        while (len-- > 0) {
            if (head.val != stack.pop().val) return false;
            head = head.next;
        }
        return true;
    }

    public static boolean isPalindrome2(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) slow = slow.next;
        slow = reverseList3(slow);
        fast = head;
        while (slow != null) {
            if (fast.val != slow.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    static ListNode temp;

    public static boolean isPalindrome3(ListNode head) {
        temp = head;
        return check(head);
    }

    private static boolean check(ListNode head) {
        if (head == null) return true;
        boolean res = check(head.next) && (temp.val == head.val);
        temp = temp.next;
        return res;
    }

    /**
     * 502.给你一个链表和一个特定值x，请你对链表进行分隔，使得所有小于x的节点都出现在大于或等于x的节点之前。
     * 你应当保留两个分区中每个节点的初始相对位置。
     */

    public ListNode partition(ListNode head, int x) {
        //头
        ListNode smallHead = new ListNode(0);
        ListNode bigHead = new ListNode(0);
        //尾
        ListNode small = smallHead;
        ListNode big = bigHead;

        while (head != null) {
            if (head.val < x) {
                small = smallHead.next = head;
            } else {
                big = bigHead.next = head;
            }
            head = head.next;
        }
        small.next = bigHead;
        big.next = null;
        return smallHead.next;
    }

    /**
     * 92.给你单链表的头指针head和两个整数left和right，其中left<=right。请你反转从位置 left到位置right的链表节点，返回反转后的链表
     */

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode cur = pre.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }


    /**
     * 595.存在一个按升序排列的链表，给你这个链表的头节点head，请你删除所有重复的元素，使每个元素只出现一次。
     * 返回同样按升序排列的结果链表。
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        head.next = deleteDuplicates2(head.next);
        return head.val == head.next.val ? head.next : head;
    }


    /**
     * 596.存在一个按升序排列的链表，给你这个链表的头节点head，
     * 请你删除链表中所有存在数字重复情况的节点，只保留原始链表中没有重复出现的数字。
     * 返回同样按升序排列的结果链表。
     */
    public static ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {
                pre = pre.next;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 617.给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     * 请尝试使用原地算法完成 。你的算法的空间复杂度应为 O(1) ，时间复杂度应为 O(nodes)，nodes为节点总数
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode oddHead = head;
        ListNode oddCur = oddHead;
        ListNode evenHead = head.next;
        ListNode evenCur = evenHead;
        while (evenCur != null && evenCur.next != null) {
            oddCur.next = oddCur.next.next;
            evenCur.next = evenCur.next.next;
            oddCur = oddCur.next;
            evenCur = evenCur.next;
        }
        oddCur.next = evenHead;
        return oddHead;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}