975. Odd Even Jump
//TreeSet自带排序，而且还有ceiling, floor的method.最好有时看下document.一个方法决定能不能搞定一道题
class Solution {
    public int oddEvenJumps(int[] A) {
        //boolean dp[i] represent whether this index is a good index, on the odd or even jump.
        //odd[i] represent at pos i, it will have its odd jump, same def for even
  //optimize      //for each index, we need to find out the index it jumps to, O(n) by brut force
        //use treeMap to record the number which has been checked
        //the key in the map is the value, and the val of the map is the index
        
        int l = A.length;
        boolean[] odd = new boolean[l];
        boolean[] even = new boolean[l];
        odd[l - 1] = true;
        even[l - 1] = true; 
        int res = 1;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[l - 1], l - 1);
        for (int i = l - 2; i >= 0; i--) {
            if (map.ceilingKey(A[i]) != null) {
                int oddNext = map.ceilingKey(A[i]);
                odd[i] = even[map.get(oddNext)];
            }
            if (map.floorKey(A[i]) != null) {
                int evenNext = map.floorKey(A[i]);
                even[i] = odd[map.get(evenNext)];
            }
            if (odd[i]) {
                res++;
            }
            map.put(A[i], i);
        }
        return res;
    }
}

class Solution {
    public int oddEvenJumps(int[] A) {
        //boolean dp[i] represent whether this index is a good index, on the odd or even jump.
        //odd[i] represent at pos i, it will have its odd jump, same def for even
        //for each index, we need to find out the index it jumps to, O(n)
        
        int l = A.length;
        boolean[] odd = new boolean[l];
        boolean[] even = new boolean[l];
        odd[l - 1] = true;
        even[l - 1] = true; 
        int res = 1;
        for (int i = l - 2; i >= 0; i--) {
            int oddNext = nextOdd(A, i);
            odd[i] = oddNext != 20001 ? even[oddNext] : false;
            int evenNext = nextEven(A, i);
            even[i] = evenNext != -1 ? odd[evenNext] : false;
            if (odd[i]) {
                res++;
            }
        }
        return res;
    }
    
    private int nextOdd(int[] nums, int i) {
        int cur = nums[i];
        int min = 100001;
        int res = 20001;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] >= cur && nums[j] < min) {
                min = nums[j];
                res = j;
            }
        }
        return res;
    }
    
    private int nextEven(int[] nums, int i) {
        int cur = nums[i];
        int max = -1;
        int res = -1;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] <= cur && nums[j] > max) {
                max = nums[j];
                res = j;
            }
        }
        return res;
    }
}