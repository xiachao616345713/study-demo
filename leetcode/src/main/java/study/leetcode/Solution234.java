package study.leetcode;

/**
 * 单链表回文
 *
 * @author xiachao
 * @date 2020/12/21
 */
public class Solution234 {

    // Definition for singly-linked list.
    private static class ListNode {

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

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // reverse
        ListNode midHead = slow;
        ListNode tmp;
        while (slow != null && slow.next != null) {
            tmp = slow.next;
            slow.next = slow.next.next;
            tmp.next = midHead;
            midHead = tmp;
        }
        // compare
        while (midHead != null) {
            if (head.val != midHead.val) {
                return false;
            }
            head = head.next;
            midHead = midHead.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode list = new ListNode();
        list.val = 0;
        list.next = new ListNode(0);

        System.out.println(new Solution234().isPalindrome(list));
    }

}
