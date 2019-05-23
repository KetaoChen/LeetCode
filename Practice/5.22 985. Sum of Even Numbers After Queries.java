985. Sum of Even Numbers After Queries
class Solution {
    
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int sum = 0;
        for (int i : A) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        //System.out.println(sum);
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int diff = query[0];
            int index = query[1];
            //System.out.println("the diff is " + diff + " and the index is " + index);
            if (diff % 2 == 0 && A[index] % 2 == 0) {
                sum += diff;
                //System.out.println("This is the " + i + "th query with res " + sum);
            }
            if (diff % 2 != 0) {
                sum = A[index] % 2 == 0 ? sum - A[index] : sum + A[index] + diff;
                //System.out.println("This is the " + i + "th query with res " + sum);
            }
            res[i] = sum;
            A[index] += diff;
            // for (int j = 0; j < A.length; j++) {
            //     System.out.print(A[j] + " ");
            // }
            // System.out.println(" the sum is " + sum);
        }
        return res;
    }
}