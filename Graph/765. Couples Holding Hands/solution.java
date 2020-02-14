// OJ: https://leetcode.com/problems/couples-holding-hands/
// Author: https://leetcode.com/charlesna/
// Time: O(n^2)
// Space: O(1)
class Solution {
    public int minSwapsCouples(int[] row) {
        //[0, 2, 1, 3]
        //if after a switch, there are two pairs of couple, we swap their positions.
        //else : how to swap? 
        //
        int res = 0;
        int l = row.length;
        int index = 0;
        while (index < l) {
            int first = row[index];
            int second = row[index + 1];
            if (first / 2 == second / 2) {
                index += 2;
                continue;
            }
            res++;
            int target = first / 2;
            for (int i = index + 2; i < l; i++) {
                if (row[i] / 2 == target) {
                    int temp = row[index + 1];
                    row[index + 1] = row[i];
                    row[i] = temp;
                    break;
                }
            }
            index += 2;
        }
        return res;
    }
}


class Solution {
    //union find
    ///for each union with k people, we need k - 1 swap.
    //if there are n people, with m unions, the total swap is n - m
    
    class UnionFind {
        int[] parent;
        int count;
        
        public UnionFind(int num) {
            parent = new int[num];
            for (int i = 0; i < num; i++) {
                parent[i] = i % 2 == 0 ? i : i - 1;
            }
            count = num / 2;
        }
        
        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }
        
        public void union(int x, int y) {
            int fatherX = find(x);
            int fatherY = find(y);
            // System.out.println(x + " " + y);
            // System.out.println(fatherX + " " + fatherY);
            if (fatherX != fatherY) {
                System.out.println(fatherX + " " + fatherY);
                parent[fatherX] = fatherY;
                count--;
            }
        }
    }
    public int minSwapsCouples(int[] row) {
        int l = row.length;
        UnionFind uf = new UnionFind(l);
        // for (int i = 0; i < l; i++) {
        //     System.out.print(uf.parent[i] + " ");
        // }
        // System.out.println();
        for (int i = 0; i < l; i += 2) {
            uf.union(row[i], row[i + 1]);
            //System.out.println(i + " " + uf.count);
        }
        return l / 2 - uf.count;
    }
}