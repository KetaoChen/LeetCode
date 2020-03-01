# [92. Reverse Linked List II (Medium)](https://leetcode.com/problems/reverse-linked-list-ii/)

<p>Reverse a linked list from position <em>m</em> to <em>n</em>. Do it in one-pass.</p>

<p><strong>Note:&nbsp;</strong>1 ¡Ü <em>m</em> ¡Ü <em>n</em> ¡Ü length of list.</p>

<p><strong>Example:</strong></p>

<pre><strong>Input:</strong> 1-&gt;2-&gt;3-&gt;4-&gt;5-&gt;NULL, <em>m</em> = 2, <em>n</em> = 4
<strong>Output:</strong> 1-&gt;4-&gt;3-&gt;2-&gt;5-&gt;NULL
</pre>


**Companies**:  
[Microsoft](https://leetcode.com/company/microsoft), [Amazon](https://leetcode.com/company/amazon), [Bloomberg](https://leetcode.com/company/bloomberg), [Docusign](https://leetcode.com/company/docusign)

**Related Topics**:  
[Linked List](https://leetcode.com/tag/linked-list/)

**Similar Questions**:
* [Reverse Linked List (Easy)](https://leetcode.com/problems/reverse-linked-list/)

## Solution 

```java
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
```