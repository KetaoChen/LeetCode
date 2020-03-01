// OJ: https://leetcode.com/problems/sort-list/
// Author: https://leetcode.com/charlesna/
// Time: O(nlogn)
// Space: O(logn)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        // merge sort
        if (head == null || head.next == null) {
            return head;
        }
        // devide into half
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        slow.next = null;
        slow = head;
        // recursion
        slow = sortList(slow);
        fast = sortList(fast);
        // merge
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (slow != null || fast != null) {
            Long s = slow == null ? Long.MAX_VALUE : (long) slow.val;
            Long f = fast == null ? Long.MAX_VALUE : (long) fast.val;
            if (s <= f) {
                cur.next = slow;
                slow = slow.next;
            }
            else {
                cur.next = fast;
                fast = fast.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}