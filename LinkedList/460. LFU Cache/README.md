# [460. LFU Cache (Hard)](https://leetcode.com/problems/lfu-cache/submissions/)

<p>Design and implement a data structure for <a href="https://en.wikipedia.org/wiki/Least_frequently_used" target="_blank">Least Frequently Used (LFU)</a> cache. It should support the following operations: <code>get</code> and <code>put</code>.</p>

<p><code>get(key)</code> - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.<br>
<code>put(key, value)</code> - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least <b>recently</b> used key would be evicted.</p>

<p>Note that the number of times an item is used is the number of calls to the&nbsp;<code>get</code>&nbsp;and&nbsp;<code>put</code>&nbsp;functions for that item since it was inserted. This number is set to zero when the item is removed.</p>

<p>&nbsp;</p>

<p><b>Follow up:</b><br>
Could you do both operations in <b>O(1)</b> time complexity?</p>

<p>&nbsp;</p>

<p><b>Example:</b></p>

<pre>LFUCache cache = new LFUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
</pre>

<p>&nbsp;</p>


**Companies**:  
[Amazon](https://leetcode.com/company/amazon), [Microsoft](https://leetcode.com/company/microsoft), [Google](https://leetcode.com/company/google)

**Related Topics**:  
[Design](https://leetcode.com/tag/design/)

**Similar Questions**:
* [LRU Cache (Medium)](https://leetcode.com/problems/lru-cache/)
* [Design In-Memory File System (Hard)](https://leetcode.com/problems/design-in-memory-file-system/)

## Solution 

```java
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
```