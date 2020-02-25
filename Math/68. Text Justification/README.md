# [68. Text Justification (Hard)](https://leetcode.com/problems/text-justification/)

<p>Given an array of words and a width&nbsp;<em>maxWidth</em>, format the text such that each line has exactly <em>maxWidth</em> characters and is fully (left and right) justified.</p>

<p>You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces <code>' '</code> when necessary so that each line has exactly <em>maxWidth</em> characters.</p>

<p>Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.</p>

<p>For the last line of text, it should be left justified and no <strong>extra</strong> space is inserted between words.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>A word is defined as a character sequence consisting&nbsp;of non-space characters only.</li>
	<li>Each word's length is&nbsp;guaranteed to be greater than 0 and not exceed <em>maxWidth</em>.</li>
	<li>The input array <code>words</code>&nbsp;contains at least one word.</li>
</ul>

<p><strong>Example 1:</strong></p>

<pre><strong>Input:</strong>
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
<strong>Output:</strong>
[
&nbsp; &nbsp;"This &nbsp; &nbsp;is &nbsp; &nbsp;an",
&nbsp; &nbsp;"example &nbsp;of text",
&nbsp; &nbsp;"justification. &nbsp;"
]
</pre>

<p><strong>Example 2:</strong></p>

<pre><strong>Input:</strong>
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
<strong>Output:</strong>
[
&nbsp; "What &nbsp; must &nbsp; be",
&nbsp; "acknowledgment &nbsp;",
&nbsp; "shall be &nbsp; &nbsp; &nbsp; &nbsp;"
]
<strong>Explanation:</strong> Note that the last line is "shall be    " instead of "shall     be",
&nbsp;            because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
</pre>

<p><strong>Example 3:</strong></p>

<pre><strong>Input:</strong>
words = ["Science","is","what","we","understand","well","enough","to","explain",
&nbsp;        "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
<strong>Output:</strong>
[
&nbsp; "Science &nbsp;is &nbsp;what we",
  "understand &nbsp; &nbsp; &nbsp;well",
&nbsp; "enough to explain to",
&nbsp; "a &nbsp;computer. &nbsp;Art is",
&nbsp; "everything &nbsp;else &nbsp;we",
&nbsp; "do &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"
]
</pre>


**Companies**:  
[Intuit](https://leetcode.com/company/intuit), [Google](https://leetcode.com/company/google), [LinkedIn](https://leetcode.com/company/linkedin), [Microsoft](https://leetcode.com/company/microsoft), [Airbnb](https://leetcode.com/company/airbnb), [Pinterest](https://leetcode.com/company/pinterest), [Uber](https://leetcode.com/company/uber), [Amazon](https://leetcode.com/company/amazon), [Indeed](https://leetcode.com/company/indeed), [Apple](https://leetcode.com/company/apple), [Twilio](https://leetcode.com/company/twilio)

**Related Topics**:  
[String](https://leetcode.com/tag/string/)

## Solution 1: Good Coding Style: OOD

```java
// OJ: https://leetcode.com/problems/text-justification/
// Author: https://leetcode.com/charlesna/
// Time: O(n)
// Space: O(n)
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        for (int left = 0; left < words.length; ) {
            int[] right = findRight(words, left, maxWidth);
            res.add(justify(words, left, right, maxWidth));
            left = right[0] + 1;
        }
        return res;
    }
    private int[] findRight(String[] words, int cur, int maxWidth) {
        int count = words[cur++].length();
        while (cur < words.length && count + words[cur].length() + 1 <= maxWidth) {
            count += words[cur++].length() + 1;
        }
        return new int[]{cur - 1, count};
    }
    private String justify(String[] words, int left, int[] right, int maxWidth) {
        if (left == right[0] || right[0] == words.length - 1) {
            return leftJustify(words, left, right, maxWidth);
        }
        int allSpace = maxWidth - right[1];
        int avg = allSpace / (right[0] - left);
        int extra = allSpace % (right[0] - left);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= right[0] - left; i++) {
            sb.append(words[left + i]);
            if (i == right[0] - left) {
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
        return sb.toString();
    }
    private String leftJustify(String[] words, int left, int[] right, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        while (left <= right[0]) {
            sb.append(words[left++] + " "); 
        }
        sb.deleteCharAt(sb.length() - 1);
        while (sb.length() < maxWidth) {
            sb.append(" ");
        }        
        return sb.toString();
    }
}
```


## Solution 2: My Method

```java
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
```