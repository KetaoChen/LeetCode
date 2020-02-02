# [39. Combination Sum (Medium)](https://leetcode.com/problems/combination-sum/)

<p>Given a <strong>set</strong> of candidate numbers (<code>candidates</code>) <strong>(without duplicates)</strong> and a target number (<code>target</code>), find all unique combinations in <code>candidates</code>&nbsp;where the candidate numbers sums to <code>target</code>.</p>

<p>The <strong>same</strong> repeated number may be chosen from <code>candidates</code>&nbsp;unlimited number of times.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>All numbers (including <code>target</code>) will be positive integers.</li>
	<li>The solution set must not contain duplicate combinations.</li>
</ul>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong> candidates = <code>[2,3,6,7], </code>target = <code>7</code>,
<strong>A solution set is:</strong>
[
  [7],
  [2,2,3]
]
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong> candidates = [2,3,5]<code>, </code>target = 8,
<strong>A solution set is:</strong>
[
&nbsp; [2,2,2,2],
&nbsp; [2,3,3],
&nbsp; [3,5]
]
</pre>


**Companies**:  
[Airbnb](https://leetcode.com/company/airbnb), [Amazon](https://leetcode.com/company/amazon), [Facebook](https://leetcode.com/company/facebook), [Google](https://leetcode.com/company/google), [Microsoft](https://leetcode.com/company/microsoft), [Walmart Labs](https://leetcode.com/company/walmart-labs), [Bloomberg](https://leetcode.com/company/bloomberg), [LinkedIn](https://leetcode.com/company/linkedin)

**Related Topics**:  
[Array](https://leetcode.com/tag/array/), [Backtracking](https://leetcode.com/tag/backtracking/)

**Similar Questions**:
* [Letter Combinations of a Phone Number (Medium)](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)
* [Combination Sum II (Medium)](https://leetcode.com/problems/combination-sum-ii/)
* [Combinations (Medium)](https://leetcode.com/problems/combinations/)
* [Combination Sum III (Medium)](https://leetcode.com/problems/combination-sum-iii/)
* [Factor Combinations (Medium)](https://leetcode.com/problems/factor-combinations/)
* [Combination Sum IV (Medium)](https://leetcode.com/problems/combination-sum-iv/)

## Solution 

```java
// OJ: https://leetcode.com/problems/combination-sum/
// Author: https://leetcode.com/charlesna/
// Time: O(2^n)
// Space: O(2^n)
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(res, new ArrayList<>(), target, candidates, 0);
        return res;
    }
	
	//since we can pick number repeatedly, so we do not need to increase index for next level.
    private void helper(List<List<Integer>> res, List<Integer> temp, int target, int[] arr, int index) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
		
        for (int i = index; i < arr.length; i++) {
            if (arr[i] > target) {
                break;
            }
            temp.add(arr[i]);
            helper(res, temp, target - arr[i], arr, i);
            temp.remove(temp.size() - 1);
        }
    }
}
```