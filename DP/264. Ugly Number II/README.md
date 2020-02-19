# [264. Ugly Number II (Medium)](https://leetcode.com/problems/ugly-number-ii/)

<p>Write a program to find the <code>n</code>-th ugly number.</p>

<p>Ugly numbers are<strong> positive numbers</strong> whose prime factors only include <code>2, 3, 5</code>.&nbsp;</p>

<p><strong>Example:</strong></p>

<pre><strong>Input:</strong> n = 10
<strong>Output:</strong> 12
<strong>Explanation: </strong><code>1, 2, 3, 4, 5, 6, 8, 9, 10, 12</code> is the sequence of the first <code>10</code> ugly numbers.</pre>

<p><strong>Note: </strong>&nbsp;</p>

<ol>
	<li><code>1</code> is typically treated as an ugly number.</li>
	<li><code>n</code> <b>does not exceed 1690</b>.</li>
</ol>

**Companies**:  
[Oracle](https://leetcode.com/company/oracle)

**Related Topics**:  
[Math](https://leetcode.com/tag/math/), [Dynamic Programming](https://leetcode.com/tag/dynamic-programming/), [Heap](https://leetcode.com/tag/heap/)

**Similar Questions**:
* [Merge k Sorted Lists (Hard)](https://leetcode.com/problems/merge-k-sorted-lists/)
* [Count Primes (Easy)](https://leetcode.com/problems/count-primes/)
* [Ugly Number (Easy)](https://leetcode.com/problems/ugly-number/)
* [Perfect Squares (Medium)](https://leetcode.com/problems/perfect-squares/)
* [Super Ugly Number (Medium)](https://leetcode.com/problems/super-ugly-number/)
* [Ugly Number III (Medium)](https://leetcode.com/problems/ugly-number-iii/)

## Solution 1: Dynamic Programming & Merge & Construct

```java
// OJ: https://leetcode.com/problems/ugly-number-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public int nthUglyNumber(int n) {
        List<Integer> ugly = new ArrayList<>();
        ugly.add(1);
        // use three indexes to record the index on the ugly sequence for each prime
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        for (int i = 1; i < n; i++) {
            int next = Math.min(ugly.get(index2) * 2, Math.min(ugly.get(index3) * 3, ugly.get(index5) * 5));
            // for example, if the next ugly number is 6. then index2++, index3++, which can prevent duplication. 
            if (next == ugly.get(index2) * 2) {
                index2++;
            }
            if (next == ugly.get(index3) * 3) {
                index3++;
            }
            if (next == ugly.get(index5) * 5) {
                index5++;
            }
            ugly.add(next);
        }
        return ugly.get(n - 1);
    }
}
```


## Solution 2: PriorityQueue: use more space

```java
// OJ: https://leetcode.com/problems/ugly-number-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(nlog)
// Space: O(n)
class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(1L);
        Set<Long> set = new HashSet<>();
        set.add(1L);
        int[] prime = {2, 3, 5};
        int count = 0;
        while (true) {
            long cur = pq.poll();
            count++;
            if (count == n) {
                return (int)cur;
            }
            for (int p : prime) {
                long next = cur * p;
                if (!set.contains(next)) {
                    pq.offer(next);
                    set.add(next);
                }
            }
        }        
    }
}
```

## Solution 3: Brute Force: TLE

```java
// OJ: https://leetcode.com/problems/ugly-number-ii/
// Author: https://leetcode.com/charlesna/
// Time: O(2^n*log(n))
// Space: O(1)
class Solution {
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }
        //worst way to check every number from 1, and find all the prime factor, and see whether it is ugly
        int res = 1;
        int num = 2;
        int[] prime = {2, 3, 5};
        while (true) {
            int temp = num;
            for (int p : prime) {
                while (temp % p == 0) {
                    temp /= p;
                }
            }
            if (temp == 1) {
                res++;
            }
            if (res == n) {
                return num;
            }
            num++;
        }
    }
}
```