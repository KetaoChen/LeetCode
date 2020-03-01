# [148. Sort List (Medium)](https://leetcode.com/problems/sort-list/)

<p>Sort a linked list in <em>O</em>(<em>n</em> log <em>n</em>) time using constant space complexity.</p>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> 4-&gt;2-&gt;1-&gt;3
<strong>Output:</strong> 1-&gt;2-&gt;3-&gt;4
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> -1-&gt;5-&gt;3-&gt;4-&gt;0
<strong>Output:</strong> -1-&gt;0-&gt;3-&gt;4-&gt;5</pre>


**Companies**:  
[Microsoft](https://leetcode.com/company/microsoft), [Facebook](https://leetcode.com/company/facebook)

**Related Topics**:  
[Linked List](https://leetcode.com/tag/linked-list/), [Sort](https://leetcode.com/tag/sort/)

**Similar Questions**:
* [Merge Two Sorted Lists (Easy)](https://leetcode.com/problems/merge-two-sorted-lists/)
* [Sort Colors (Medium)](https://leetcode.com/problems/sort-colors/)
* [Insertion Sort List (Medium)](https://leetcode.com/problems/insertion-sort-list/)

## Solution : Merge Sort

```java
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
```