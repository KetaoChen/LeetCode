// OJ: https://leetcode.com/problems/satisfiability-of-equality-equations/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    int[] uf = new int[26];
    public boolean equationsPossible(String[] equations) {
        for (int i = 0; i < 26; ++i) uf[i] = i;
        for (String e : equations)
            if (e.charAt(1) == '=')
                uf[find(e.charAt(0) - 'a')] = find(e.charAt(3) - 'a');
        for (String e : equations)
            if (e.charAt(1) == '!' && find(e.charAt(0) - 'a') == find(e.charAt(3) - 'a'))
                return false;
        return true;
    }
    public int find(int x) {
        if (x != uf[x]) uf[x] = find(uf[x]);
        return uf[x];
    }
}


class Solution {
    
    int[] parents;
    Set<Integer>[] diff;
    public boolean equationsPossible(String[] equations) {
        //if they are equal, we do union, check, whether they are in the diff group
        //if not equal, we need to check, whether they are in the same group, if not, record the value in the set
        parents = new int[26];
        diff = new HashSet[26];
        for (int i = 0; i < 26; i++) {
            parents[i] = i;
            diff[i] = new HashSet<>();
        }
        
        for (String equation : equations) {
            int c1 = equation.charAt(0) - 'a';
            int c2 = equation.charAt(3) - 'a';
            int father1 = find(c1);
            int father2 = find(c2);
            
            if (equation.charAt(1) == '=') {
                if (diff[father1].contains(c2) || diff[father2].contains(c1)) {
                    return false;
                }
                if (father1 != father2) {
                    parents[father1] = father2;
                    for (int d : diff[father1]) {
                        diff[father2].add(d);
                    }
                }
            }
            else {
                if (father1 == father2) {
                    return false;
                }
                diff[father1].add(c2);
                diff[father2].add(c1);
            }
        }
        return true;
    }
    
    private int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        parents[x] = find(parents[x]);
        return parents[x];
    }
}