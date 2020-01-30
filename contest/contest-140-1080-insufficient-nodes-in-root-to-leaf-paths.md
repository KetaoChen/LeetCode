---
tags:
  - leetcode
  - depth-first search
created: '2019/6/13 下午4:54:56'
difficulty: medium
---

# 1080-insufficient-nodes-in-root-to-leaf-paths

## Problem

Given the `root` of a binary tree, consider all _root to leaf paths_: paths from the root to any leaf.  \(A leaf is a node with no children.\)  
  


A `node` is _insufficient_ if **every** such root to leaf path intersecting this `node` has sum strictly less than `limit`.  
  


Delete all insufficient nodes simultaneously, and return the root of the resulting binary tree.  
  


**Example 1:**  
  


```text

Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1

Output: [1,2,3,4,null,null,7,8,9,null,14]
```

**Example 2:**  
  


```text

Input: root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22

Output: [5,4,8,11,null,17,4,7,null,null,null,5]
```

**Example 3:**  
  


```text

Input: root = [1,2,-3,-5,null,4,null], limit = -1

Output: [1,null,-3,4]
```

**Note:**  
  


1. 2. The given tree will have between `1` and `5000` nodes.
3. 4. `-10^5 <= node.val <= 10^5`
5. 6. `-10^9 <= limit <= 10^9`
7. 
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

