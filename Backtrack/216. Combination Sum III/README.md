# [216. Combination Sum III (Medium)](https://leetcode.com/problems/combination-sum-iii/)

<div>
<p>Find all possible combinations of <i><b>k</b></i> numbers that add up to a number <i><b>n</b></i>, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>All numbers will be positive integers.</li>
	<li>The solution set must not contain duplicate combinations.</li>
</ul>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> <i><b>k</b></i> = 3, <i><b>n</b></i> = 7
<strong>Output:</strong> [[1,2,4]]
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> <i><b>k</b></i> = 3, <i><b>n</b></i> = 9
<strong>Output:</strong> [[1,2,6], [1,3,5], [2,3,4]]
</pre>
</div>

**Companies**:  
[Microsoft](https://leetcode.com/company/microsoft)

**Related Topics**:  
[Array](https://leetcode.com/tag/array/), [Backtracking](https://leetcode.com/tag/backtracking/)

**Similar Questions**:
* [Combination Sum (Medium)](https://leetcode.com/problems/combination-sum/)

## Solution 

```java
// OJ: https://leetcode.com/problems/combination-sum-iii/
// Author: https://leetcode.com/charlesna/
// Time: O()
// Space: O()
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[10];
        helper(res, new ArrayList<>(), 1, k, n, used);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, int start, int k, int target, boolean[] used) {
        if (target == 0 && k == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (k == 0 || start > 9) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (used[i]) {
                continue;
            }
            if (i > target) {
                break;
            }
            used[i] = true;
            temp.add(i);
            helper(res, temp, i + 1, k - 1, target - i, used);
            used[i] = false;
            temp.remove(temp.size() - 1);
        }
    }
}
```