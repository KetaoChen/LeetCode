143. Reorder List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        //seperate the list into first half and last half
        //reverse the last half
        //insert the last half node into the first half
        //have two pointer, slow 1 node/time, fast 2nodes/time --> the slow is the start of the last half.
        if (head == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHead = slow.next;
//This is so important!!!!!! Or this will go into an infinite loop!
        slow.next = null;
        secondHead = reverse(secondHead);
        ListNode index = head;
        while (secondHead != null && index != null) {
            ListNode insert = secondHead;
            secondHead = secondHead.next;
            insert.next = index.next;
            index.next = insert;
            index = index.next.next;
        }
        // while (head != null) {
        //     System.out.println(head.val);
        //     head = head.next;
        // }
        // System.out.println("finish");
    }
    
    private ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;
        first.next = null;
        while (second != null) {
            ListNode temp = second.next;
            second.next = first;
            first = second;
            second = temp;
        }
        return first;
    }
}