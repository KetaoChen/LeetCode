# [25. Reverse Nodes in k-Group (Hard)](https://leetcode.com/problems/reverse-nodes-in-k-group/)

<p>Given a linked list, reverse the nodes of a linked list <em>k</em> at a time and return its modified list.</p>

<p><em>k</em> is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of <em>k</em> then left-out nodes in the end should remain as it is.</p>

<ul>
</ul>

<p><strong>Example:</strong></p>

<p>Given this linked list: <code>1-&gt;2-&gt;3-&gt;4-&gt;5</code></p>

<p>For <em>k</em> = 2, you should return: <code>2-&gt;1-&gt;4-&gt;3-&gt;5</code></p>

<p>For <em>k</em> = 3, you should return: <code>3-&gt;2-&gt;1-&gt;4-&gt;5</code></p>

<p><strong>Note:</strong></p>

<ul>
	<li>Only constant extra memory is allowed.</li>
	<li>You may not alter the values in the list's nodes, only nodes itself may be changed.</li>
</ul>


**Companies**:  
[Microsoft](https://leetcode.com/company/microsoft), [Amazon](https://leetcode.com/company/amazon), [Facebook](https://leetcode.com/company/facebook), [ByteDance](https://leetcode.com/company/bytedance), [Mathworks](https://leetcode.com/company/mathworks), [Apple](https://leetcode.com/company/apple), [Adobe](https://leetcode.com/company/adobe), [Cisco](https://leetcode.com/company/cisco)

**Related Topics**:  
[Linked List](https://leetcode.com/tag/linked-list/)

**Similar Questions**:
* [Swap Nodes in Pairs (Medium)](https://leetcode.com/problems/swap-nodes-in-pairs/)

## Solution 

```java
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
```