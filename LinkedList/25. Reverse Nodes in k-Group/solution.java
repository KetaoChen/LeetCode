// OJ: https://leetcode.com/problems/reverse-nodes-in-k-group/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur != null) {
            cur.next = reverse(cur.next, k);
            int t = k;
            while (cur != null && t > 0) {
                cur = cur.next;
                t--;
            }
        }
        return dummy.next;
    }
    private ListNode reverse(ListNode head, int k) {
        int count = 0;
        ListNode cur = head;
        while (count < k && cur != null) {
            count++;
            cur = cur.next;
        }
        if (count < k) return head;
        cur = head;
        ListNode next = head.next;
        int change = k - 1;
        while (change > 0) {
            ListNode temp = next.next;
            next.next = cur;
            cur = next;
            next = temp;
            change--;
        }
        head.next = next;
        return cur;
    }
}