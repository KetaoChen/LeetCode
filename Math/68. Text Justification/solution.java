// OJ: https://leetcode.com/problems/text-justification/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        // 1. count how many words can be put in this line, and how many characters.
        // 2. calculate the number of extra space to need to put. 
              // if this is the first word, do not add space, else we need to add a space first
        int count = 0, left = 0, right = 0;
        while (right < words.length) {
            String cur = words[right];
            //first word
            if (count == 0) {
                count += cur.length();
                right++;
                continue;
            }
            if (count + cur.length() + 1 <= maxWidth) {
                count += cur.length() + 1;
                right++;
                continue;
            }
            if (right != words.length) {
                //if we can not add any words, and this is not the last line, add this line to the output
                StringBuilder sb = new StringBuilder();
                //only have one word
                if (right - left == 1) {
                    sb.append(words[left]);
                    while (sb.length() < maxWidth) {
                        sb.append(" ");
                    }
                }
                else {
                    //have more than one word
                    int allSpace = maxWidth - count;
                    int avg = allSpace / (right - left - 1);
                    int extra = allSpace % (right - left - 1);
                    for (int i = 0; i < right - left; i++) {
                        sb.append(words[left + i]);
                        if (i == right - left - 1) {
                            continue;
                        }
                        sb.append(" ");
                        for (int add = 0; add < avg; add++) {
                            sb.append(" ");
                        }
                        if (i < extra) {
                            sb.append(" ");
                        }
                    }
                }
                count = 0;
                left = right;
                res.add(sb.toString());
            }
        } 
        StringBuilder sb = new StringBuilder();
        while (left < right) {
            sb.append(words[left++] + " "); 
        }
        sb.deleteCharAt(sb.length() - 1);
        while (sb.length() < maxWidth) {
            sb.append(" ");
        }
        res.add(sb.toString());
        return res;
    }
}