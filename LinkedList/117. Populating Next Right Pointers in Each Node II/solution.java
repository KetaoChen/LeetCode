// OJ: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public Node() {}
    public Node(int _val) {
        val = _val;
    }
    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    
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
}