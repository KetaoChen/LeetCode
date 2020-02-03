// OJ: https://leetcode.com/problems/clone-graph/
// Author: github.com/lzl124631x
// Time: O()
// Space: O()
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {}
    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
class Solution {
    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (!map.containsKey(cur)) {
                map.put(cur, new Node(cur.val, new LinkedList<Node>()));
            }
            for (Node next : cur.neighbors) {
                if (!map.containsKey(next)) {
                    map.put(next, new Node(next.val, new LinkedList<Node>()));
                    q.offer(next);
                }
                map.get(cur).neighbors.add(map.get(next));
            }
        }
        return map.get(node);
    }
}