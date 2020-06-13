# [285. Inorder Successor in BST (Medium)](https://leetcode.com/problems/inorder-successor-in-bst/)

<p>Given a binary search tree and a node in it, find the in-order successor of that node in the BST.</p>

<p>The successor of a node&nbsp;<code>p</code>&nbsp;is the node with the smallest key greater than&nbsp;<code>p.val</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2019/01/23/285_example_1.PNG" style="width: 122px; height: 117px;">
<pre><strong>Input: </strong>root = <span id="example-input-1-1">[2,1,3]</span>, p = <span id="example-input-1-2">1</span>
<strong>Output: </strong><span id="example-output-1">2</span>
<strong>Explanation: </strong>1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2019/01/23/285_example_2.PNG" style="width: 246px; height: 229px;">
<pre><strong>Input: </strong>root = <span id="example-input-2-1">[5,3,6,2,4,null,null,1]</span>, p = <span id="example-input-2-2">6</span>
<strong>Output: </strong><span id="example-output-2">null</span>
<strong>Explanation: </strong>There is no in-order successor of the current node, so the answer is <code>null</code>.
</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li>If the given node has no in-order successor in the tree, return <code>null</code>.</li>
	<li>It's guaranteed that the values of the tree are unique.</li>
</ol>


**Companies**:  
[Citadel](https://leetcode.com/company/citadel), [Microsoft](https://leetcode.com/company/microsoft), [Amazon](https://leetcode.com/company/amazon), [Facebook](https://leetcode.com/company/facebook)

**Related Topics**:  
[Tree](https://leetcode.com/tag/tree/)

**Similar Questions**:
* [Binary Tree Inorder Traversal (Medium)](https://leetcode.com/problems/binary-tree-inorder-traversal/)
* [Binary Search Tree Iterator (Medium)](https://leetcode.com/problems/binary-search-tree-iterator/)
* [Inorder Successor in BST II (Medium)](https://leetcode.com/problems/inorder-successor-in-bst-ii/)

## Solution 1: Iterative

```java
// OJ: https://leetcode.com/problems/inorder-successor-in-bst/
// Author: https://leetcode.com/charlesna/
// Time: O(h)
// Space: O(1)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while (root != null) {
            if (root.val <= p.val) {
                root = root.right;
            }
            else {
                res = root;
                root = root.left;
            }
        }
        return res;
    }
}
```

## Solution 2: Recursive

```java
// OJ: https://leetcode.com/problems/inorder-successor-in-bst/
// Author: https://leetcode.com/charlesna/
// Time: O(h)
// Space: O(h)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return root;
        if (p.val >= root.val) {
            return inorderSuccessor(root.right, p);
        }     
        TreeNode res = inorderSuccessor(root.left, p);
        return res == null ? root : res;
    }
}
```