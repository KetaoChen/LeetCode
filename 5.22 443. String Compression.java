//443. String Compression
class Solution {
    public int compress(char[] chars) {
        int l = chars.length;
        if (l <= 1) {
            return l;
        }
        int res = 1;
        int index = 1;
        //loop the array
        //res record the index of the final res, before start checking the repeated chars, chars[res - 1] = the val of char
        //index record the current index of the array in the loop
        //after counting the number of repeated chars
        //if (there is only one char for) index++, res++,
        //if more than one, chars[res] == count, then ,index++, res++,
        //chars[res] == new val of the char
        
        while (index < l) {
            int count = 1;
            while (index < l && chars[index] == chars[index - 1]) {
                count++;
                index++;
            }            
            if (count > 1) {
                char[] counts = Integer.toString(count).toCharArray();
                for (char c : counts) {
                    chars[res++] = c;
                }
            }
            if (index < l) {
                chars[res] = chars[index];
                res++;
            }
            index++;
        }
        //System.out.println(res);
        return res;
    }
}