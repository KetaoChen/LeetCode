# Array

### 798. Smallest Rotation with Highest Score

1. Consider the range of integer in the array. If num <= 0 || num >= l, we do not need to consider those numbers.
2. Understand that the points that make it count and uncount is continuous. 
3. Calculate the number of rotate time for each number to make it count and uncount.
4. Pay attention to the index. Especially when the current position is the start of bad position, need to turn len times.



## Subarray Sum

1. Maximum Subarray (LC 53) (Greedy)

   ```java
   public int maxSubArray(int[] nums) {
       int res = nums[0], sum = res;
       for (int i = 1; i < nums.length; i++) {
           if (sum < 0) {
               sum = 0;
           }
           sum += nums[i];
           res = Math.max(res, sum);
       }
       return res;
   }
   ```

   

   

2. Maximum Sum Circular Subarray (LC 918) (Circular extend to double length)

## Sorted Chunk

1. Shortest Unsorted Continuous Subarray (LC 581) 

   ```java
   public int findUnsortedSubarray(int[] nums) {
       if (nums.length == 0) {
           return 0;
       }    
       int l = nums.length, left = l, min = nums[l - 1];
       for (int i = l - 2; i >= 0; i--) {
           min = Math.min(nums[i], min);
           if (nums[i] > min) {
               left = i;
           }
       }
   
       int right = -1, max = nums[0];
       for (int i = 1; i < l; i++) {
           max = Math.max(nums[i], max);
           if (nums[i] < max) {
               right = i;
           }
       }
   
       return Math.max(0, right - left + 1);
   }
   ```

   

2. Max Chunks To Make Sorted (LC 769 & LC 1735 Bulb Switcher III)

   ```java
   public int maxChunksToSorted(int[] arr) {
       int res = arr[0] == 0 ? 1 : 0, max = arr[0];
       for (int i = 1; i < arr.length; i++) {
           max = Math.max(max, arr[i]);
           if (max == i) {
               res++;
           }
       }
       return res;
   }
   ```



3. Max Chunks To Make Sorted II (LC 768)

   ```java
   public int maxChunksToSorted(int[] arr) { 
       int l = arr.length;
       // find the end point of each point
       int[] indexSmallerThanCurFromRight = new int[l];
       Arrays.fill(indexSmallerThanCurFromRight, -1);
       for (int i = 0; i < l; i++) {
           for (int j = l - 1; j >= i; j--) {
               if (arr[j] < arr[i]) {
                   indexSmallerThanCurFromRight[i] = j;
                   break;
               }
           }
           if (indexSmallerThanCurFromRight[i] == -1) {
               indexSmallerThanCurFromRight[i] = i;
           }
       }
   
       // frog jump.
       int res = 0;
       int index = 0, max = 0;
       while (index < l) {
           max = Math.max(max, indexSmallerThanCurFromRight[index]);
           if (index == max) {
               res++;
           }
           index++;
       }
   
       return res;
   }
   ```

   

