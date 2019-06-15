---
tags: ["leetcode","depth-first search"]
created: "2019/6/13 下午4:54:56"
difficulty: "medium"
---
 
# [1080-insufficient-nodes-in-root-to-leaf-paths](https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/)

## Problem
<div><p>Given the <code>root</code>&nbsp;of a binary tree, consider all <em>root to leaf paths</em>: paths from the root&nbsp;to any leaf.&nbsp; (A leaf is a node with no children.)</p><br><br><p>A <code>node</code> is <em>insufficient</em> if&nbsp;<strong>every</strong> such root to leaf path intersecting this <code>node</code> has sum strictly less than&nbsp;<code>limit</code>.</p><br><br><p>Delete all insufficient nodes simultaneously, and return the root of the resulting&nbsp;binary tree.</p><br><br><p>&nbsp;</p><br><br><p><strong>Example 1:</strong></p><br><br><pre><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/06/05/insufficient-11.png" style="width: 482px; height: 200px;"><br>Input: </strong>root = <span id="example-input-1-1">[1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14]</span>, limit = <span id="example-input-1-2">1</span><br><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/06/05/insufficient-2.png" style="width: 258px; height: 200px;"><br>Output: </strong><span id="example-output-1">[1,2,3,4,null,null,7,8,9,null,14]</span><br></pre><br><br><div><br><p><strong>Example 2:</strong></p><br><br><pre><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/06/05/insufficient-3.png" style="width: 292px; height: 200px;"><br>Input: </strong>root = <span id="example-input-2-1">[5,4,8,11,null,17,4,7,1,null,null,5,3]</span>, limit = <span id="example-input-2-2">22</span><br><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/06/05/insufficient-4.png" style="width: 264px; height: 200px;"><br>Output: </strong><span id="example-output-2">[5,4,8,11,null,17,4,7,null,null,null,5]</span></pre><br><br><p>&nbsp;</p><br><br><p><strong>Example 3:</strong></p><br><br><pre><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/06/11/screen-shot-2019-06-11-at-83301-pm.png" style="width: 188px; height: 150px;"><br>Input: </strong>root = <span>[1,2,-3,-5,null,4,null]</span>, limit = -1<br><img alt="" src="https://assets.leetcode.com/uploads/2019/06/11/screen-shot-2019-06-11-at-83517-pm.png" style="width: 122px; height: 150px;"><strong><br>Output: </strong><span>[1,null,-3,4]</span></pre><br></div><br><br><p>&nbsp;</p><br><br><p><strong>Note:</strong></p><br><br><ol><br>	<li>The given tree will have between <code>1</code> and <code>5000</code> nodes.</li><br>	<li><code>-10^5&nbsp;&lt;= node.val &lt;= 10^5</code></li><br>	<li><code>-10^9 &lt;= limit&nbsp;&lt;= 10^9</code></li><br></ol><br><br><div><br><div>&nbsp;</div><br></div><br></div>

## Solution

java
```java

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
    public TreeNode sufficientSubset(TreeNode root, int limit) {        
        //things we need to do: 1. is to find the sum from root to each leaf node. 
        //2. check whether the sum of all the paths to leaf is larger than limit.
            //if all the sum is smaller than limit, let root.left = null or root. right = null
        //so the limit for the root is limit
        //the limit for the root.left is limit - root.val, which is a subproblem
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root.val >= limit ? root : null;
        }
        TreeNode res = root;
        res.left = sufficientSubset(root.left, limit - root.val);
        res.right = sufficientSubset(root.right, limit - root.val);
        return res.left == null && res.right == null ? null : res;
    }
    
    //fint the largest path sum of a TreeNode
    // private int helper(TreeNode root, int sum) {
    //     if (root == null) {
    //         return Integer.MIN_VALUE;
    //     }
    //     if (root.left == null && root.right == null) {
    //         return root.val + sum;
    //     }
    //     int left = Integer.MIN_VALUE;
    //     int right = Integer.MIN_VALUE;
    //     if (root.left != null) {
    //         left = helper(root.left, sum + root.val);
    //     }
    //     if (root.right != null) {
    //         right = helper(root.right, sum  + root.val);
    //     }
    //     return Math.max(left, right);
    // }
}
​
```
