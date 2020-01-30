---
tags:
  - leetcode
created: '2019/6/23 下午9:40:05'
difficulty: medium
---

# 1093-statistics-from-a-large-sample

## Problem

We sampled integers between `0` and `255`, and stored the results in an array `count`:  `count[k]` is the number of integers we sampled equal to `k`.  
  


Return the minimum, maximum, mean, median, and mode of the sample respectively, as an array of **floating point numbers**.  The mode is guaranteed to be unique.  
  


_\(Recall that the median of a sample is:_  
  


* * _The middle element, if the elements of the sample were sorted and the number of elements is odd;_
* * _The average of the middle two elements, if the elements of the sample were sorted and the number of elements is even.\)_
* 
**Example 1:**  


```text
Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
```

**Example 2:**  


```text
Input: count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: [1.00000,4.00000,2.18182,2.00000,1.00000]
```

**Constraints:**  
  


1. 2. `count.length == 256`
3. 4. `1 <= sum(count) <= 10^9`
5. 6. The mode of the sample that count represents is unique.
7. 8. Answers within `10^-5` of the true value will be accepted as correct.
9. 
## Solution

java

```java
class Solution {
    public double[] sampleStats(int[] count) {
        double[] res = new double[5];
        for (int i = 0; i < 5; i++) {
            res[i] = -1.0;
        }
        long sum = 0;
        int num = 0;
        int mode = -1;
        int maxCount = 0;

        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                continue;
            }
            if (res[0] == -1.0) {
                res[0] = i;
            }
            res[1] = i;

            sum += i * count[i];
            num += count[i];

            if (count[i] > maxCount) {
                mode = i;
                maxCount = count[i];
            }
        }

        res[2] = (double)sum / (double)num;
        res[3] = getMedian(count, num);
        res[4] = mode;
        return res;
    }

    private double getMedian(int[] count, int num) {
        if (num % 2 == 1) {
            return get(count, num / 2);
        }
        return (get(count, num / 2) + get(count, num / 2 + 1)) / 2.0;
    }

    private int get(int[] count, int k) {
        int c = 0;
        int index = 0;
        while (index < count.length) {
            c += count[index];
            if (c >= k) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
​
```

