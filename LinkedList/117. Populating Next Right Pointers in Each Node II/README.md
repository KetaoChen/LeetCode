# [117. Populating Next Right Pointers in Each Node II (Medium)](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/)

<p>Given a binary tree</p>

<pre>struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
</pre>

<p>Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to <code>NULL</code>.</p>

<p>Initially, all next pointers are set to <code>NULL</code>.</p>

<p>&nbsp;</p>

<p><strong>Follow up:</strong></p>

<ul>
	<li>You may only use constant extra space.</li>
	<li>Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/02/15/117_sample.png" style="width: 640px; height: 218px;"></p>

<pre><strong>Input:</strong> root = [1,2,3,4,5,null,7]
<strong>Output:</strong> [1,#,2,3,#,4,5,7,#]
<strong>Explanation: </strong>Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the given tree is less than <code>6000</code>.</li>
	<li><code>-100&nbsp;&lt;= node.val &lt;= 100</code></li>
</ul>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Microsoft](https://leetcode.com/company/microsoft), [Bloomberg](https://leetcode.com/company/bloomberg), [Google](https://leetcode.com/company/google), [Facebook](https://leetcode.com/company/facebook)

**Related Topics**:  
[Tree](https://leetcode.com/tag/tree/), [Depth-first Search](https://leetcode.com/tag/depth-first-search/)

**Similar Questions**:
* [Populating Next Right Pointers in Each Node (Medium)](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/)

## Solution 1: LinkedList BFS (concise)

```java
    //O(1)
    //iterate, use Node to realize LinkedList
    public Node connect(Node root) {
        if (root == null) return root;
        // initial our linkedlist to keep this leval
        Node cur = root;
        int size = 1;
        // creat another linkedlist to track the next level
        Node head = new Node(0), tail = head;
        int newSize = 0;
        while (size > 0) {
            size--;
            if (cur.left != null) {
                tail.next = cur.left;
                tail = tail.next;
                newSize++;
            }
            if (cur.right != null) {
                tail.next = cur.right;
                tail = tail.next;
                newSize++;
            }
            if (size == 0) {
                cur = head;
                size = newSize;
                head = tail = new Node(0);
                newSize = 0;
            }
            cur = cur.next;
        }
        return root;
    }
```


## Solution 2: LinkedList BFS (Verbose + Explanation)

```java
//O(1)
    //iterate, use Node to realize LinkedList
    public Node connect(Node root) {
        if (root == null) return root;
        // initial our linkedlist to keep cur leval
        Node cur = root, head = root, tail = root;
        int size = 1;
        // creat another linkedlist to track the next level
        Node newHead = new Node(0), newTail = newHead;
        int newSize = 0;
        
        // 只要还有node..
        while (size > 0) {
            size--; // 把当前的node pop出来，把儿子们放到链表结尾，并且连上。
            // add left child
            if (cur.left != null) {
                newTail.next = cur.left;
                newTail = newTail.next;
                newSize++;
            }
            // add right child
            if (cur.right != null) {
                newTail.next = cur.right;
                newTail = newTail.next;
                newSize++;
            }
            // if size = 0, move to the next level. 
            if (size == 0) {
                cur = newHead; //把当前指针换到下一个level的头
                size = newSize; // 更新size
                newHead = newTail = new Node(0); //再建一个新的下一个level的链表
                newSize = 0;
            }
            cur = cur.next;
        }
        return root;
    }
```


## Solution 3: Recursion

```java
// O(1) space, since we have next node.
    public Node connect(Node root) {
        if (root == null) return root;
        
        // find the next node, my children should connect.
        Node parentNext = root.next;
        Node next = null;
        
        while (next == null && parentNext != null) {
            if (parentNext.right != null) next = parentNext.right;
            if (parentNext.left != null) next = parentNext.left;
            parentNext = parentNext.next;
        }
        
        if (root.right != null) {
            root.right.next = next;
            connect(root.right);
        }
        if (root.left != null) {
            root.left.next = root.right == null ? next : root.right;
            connect(root.left);
        }
        return root;
    }
```


## Solution 4: Brute force BFS

```java
// brute force, bfs, level traversal
    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                Node cur = q.poll();
                // if not the last one, connect to the next one
                if (i != s - 1) {
                    cur.next = q.peek();
                }
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }
        return root;
    }
```