// OJ: https://leetcode.com/problems/lfu-cache/
// Author: https://leetcode.com/charlesna/
// Time: O(1)
// Space: O(n)
class LFUCache {
    class Node {
        Node prev, next;
        int key, val, f;
        public Node(int k, int v, int f) {
            key = k;
            val = v;
            this.f =f;
        }
    }
    Map<Integer, Node> map;
    // store the head of each freq
    Map<Integer, Node> freq;
    Node head, tail;
    int cap;
    public LFUCache(int capacity) {
        map = new HashMap<>();
        freq = new HashMap<>();
        head = new Node(0, 0, -1);
        tail = new Node(0, 0, -1);
        head.next = tail;
        tail.prev = head;
        cap = capacity;
    }
    private void remove(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    // insert this in front of pos
    private void insert(Node add, Node pos) {
        add.prev = pos.prev;
        add.next = pos;
        pos.prev.next = add;
        pos.prev = add;
    }
    public int get(int key) {
        if (cap == 0) return -1;
        if (!map.containsKey(key)) return -1;
        Node cur = map.get(key);
        // situation, we do not need to change the list.
        // no freq at f+1 and cur is the head of f.
        if (freq.get(cur.f) == cur && !freq.containsKey(cur.f + 1)) {
            if (cur.next.f == cur.f) {
                freq.put(cur.f, cur.next);
            }
            else {
                freq.remove(cur.f);
            }
            cur.f++;
            freq.put(cur.f, cur);
            return cur.val;
        }
        // need to change the list. Three situations.
        if (freq.get(cur.f) == cur) {
            if (cur.next.f == cur.f) {
                freq.put(cur.f, cur.next);
            }
            else {
                freq.remove(cur.f);
            }
        }
        remove(cur);
        // add in front of the head of next freq
        if (freq.containsKey(cur.f + 1)) {
            insert(cur, freq.get(cur.f + 1));
        }
        // add in front of the head of current freq
        else {
            insert(cur, freq.get(cur.f));
        }
        freq.put(cur.f + 1, cur);
        cur.f++;
        return cur.val;
    }
    public void put(int key, int value) {
        Node test = head.next;
        // System.out.println("now we are putting " + key + " " + value);
        // while (test != tail) {
        //     System.out.println(test.key + " " +  test.val + " " + test.f);
        //     test = test.next;
        // }
        if (cap == 0) return;
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            get(key);
            cur.val = value;
            return;
        }
        if (map.size() == cap) {
            Node remove = tail.prev;
            map.remove(remove.key);
            remove(remove);
            if (freq.get(remove.f) == remove) {
                freq.remove(remove.f);
            }
        }
        Node add = new Node(key, value, 1);
        map.put(key, add);
        if (freq.containsKey(1)) {
            insert(add, freq.get(1));
        }
        else {
            insert(add, tail);
        }
        freq.put(1, add);
    }
}
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */