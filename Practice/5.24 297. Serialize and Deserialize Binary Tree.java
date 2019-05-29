297. Serialize and Deserialize Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 //The second trial use preorder traversal
 //have preorder traversal, when it is null, record null
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper(sb, root);
        System.out.println(sb.toString());
        return sb.toString();
    }

    private void helper(StringBuilder sb, TreeNode root) {
        if (root == null) {
            sb.append("null,");
            return;
        }
        sb.append(root.val);
        sb.append(',');
        helper(sb, root.left);
        helper(sb, root.right);
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] s = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(s));
        TreeNode root = new TreeNode(0);
        return build(root, queue);
    }
    
    private TreeNode build(TreeNode root, Queue<String> q) {
        String cur = q.poll();
        if (cur.equals("null")) {
            return null;
        }
        root = new TreeNode(Integer.parseInt(cur));
        root.left = build(root.left, q);
        root.right = build(root.right, q);
        return root;
    }
 
 
 
 //I use BFS to have level traversal and got TLE.
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //think this as a level order traversal
        //how to deal with null.
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean allNull = false;
        while (!q.isEmpty() && !allNull) {
            int size = q.size();
            allNull = true;
            for (int i = 0; i < size; i++) {
                TreeNode cur  = q.poll();
                if (cur == null) {
                    sb.append("null,");
                    q.offer(null);
                    q.offer(null);
                }
                else {
                    sb.append(cur.val);
                    sb.append(',');
                    q.offer(cur.left);
                    q.offer(cur.right);
                    if (cur.left != null || cur.right != null) {
                        allNull = false;
                    }
                }
            }
        }
        //System.out.println(sb.toString());
        return sb.toString();        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] s = data.split(",");
        // for (int i = 0; i < s.length; i++) {
        //     System.out.print(s[i] + " ");
        // }
        if (s[0].equals("null")) {
            return null;
        }
        TreeNode res = new TreeNode(Integer.parseInt(s[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(res);
        int i = 1;
        while (i < s.length) {
            //System.out.println(i + " " + s[i] + " " + s[i + 1]);
            TreeNode cur = q.poll();
            if (cur != null) {
                cur.left = set(s[i]);                    
                cur.right = set(s[i + 1]);
                q.offer(cur.left);
                q.offer(cur.right);
            }
            else {
                q.offer(null);
                q.offer(null);
            }
            i = i + 2;
        }
        return res;
    }
    
    private TreeNode set(String s) {
        if (s.equals("null")) {
            return null;
        }
        return new TreeNode(Integer.parseInt(s));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));