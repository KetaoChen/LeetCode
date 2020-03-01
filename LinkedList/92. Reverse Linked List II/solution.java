// OJ: https://leetcode.com/problems/reverse-linked-list-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(1)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0), cur = head, prev = dummy;
        dummy.next = head;
        int count = 1;
        while (count < m) {
            prev = cur;
            cur = cur.next;
            count++;
        }
        ListNode start = prev;
        prev = cur;
        cur = cur.next;
        count++;
        while (count <= n) {
            prev.next = cur.next;
            cur.next = start.next;
            start.next = cur;
            cur = prev.next;
            count++;
        }
        return dummy.next;
    }
}