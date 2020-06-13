// OJ: https://leetcode.com/problems/inorder-successor-in-bst/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
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

// OJ: https://leetcode.com/problems/inorder-successor-in-bst/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
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