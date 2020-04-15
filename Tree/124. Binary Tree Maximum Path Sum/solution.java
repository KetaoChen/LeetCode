// OJ: https://leetcode.com/problems/binary-tree-maximum-path-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
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
    public int maxPathSum(TreeNode root) {
        int[] res = new int[]{Integer.MIN_VALUE};
        getMaxFromRoot(root, res);
        return res[0];
    }
    private int getMaxFromRoot(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int left = getMaxFromRoot(root.left, res);
        int right = getMaxFromRoot(root.right, res);
        res[0] = Math.max(res[0], Math.max(0, left) + root.val + Math.max(0, right));
        return Math.max(Math.max(left, right), 0) + root.val;
    }
}