124. Binary Tree Maximum Path Sum

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 
 //为避免重复运算！！！在helper method中，每算到下面一个子问题，都已经更新过res.之后就不要再重复算了。
 //helper method干了两件事！
 //1. 计算了以root为端点，最大的path的和是多少，并返回。
 //2. 同时计算了左子树，和右子树最大Path的和是多少，并相加。这个就是通过root节点的path的最大和，并用这个值更新res.
 class Solution {
    int res;
    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        helper(root);
        return res;
    }
    
    //this is the maxSum of path that must go through this root.
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));
        res = Math.max(res, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}


class Solution {
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int res = getMaxSum(root);
        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);
        return Math.max(res, Math.max(left, right));
    }
    
    //this is the maxSum of path that must go through this root.
    private int getMaxSum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        return root.val + Math.max(getMaxPathSum(root.left), 0) + Math.max(0, getMaxPathSum(root.right));
    }
    
    private int getMaxPathSum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        return root.val + Math.max(0, Math.max(getMaxPathSum(root.left), getMaxPathSum(root.right)));
    }
}