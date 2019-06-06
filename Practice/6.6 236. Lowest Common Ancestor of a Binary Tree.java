236. Lowest Common Ancestor of a Binary Tree

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 //答案。。我的天。我真的好蠢。。努力分析子问题！！
 class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
}



class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //check if root.left || root.right contains both node
        if (isRoot(root.left, p) && isRoot(root.left, q)) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (isRoot(root.right, p) && isRoot(root.right, q)) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
    
    private boolean isRoot(TreeNode root, TreeNode target) {
        if (root == null) {
            return false;
        }
        if (root == target) {
            return true;
        }
        return isRoot(root.left, target) || isRoot(root.right, target);
    }
}