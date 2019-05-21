class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1; 
        int j = b.length() - 1;
        int add = 0;
        
        //check starting from the last char
        //another way to write the merge!!! to avoid repeated code.
        while (i >= 0 || j >= 0) {
            int first = 0;
            int second = 0;
            if (i >= 0) {
                first = a.charAt(i) - '0';
            }
            if (j >= 0) {
                second = b.charAt(j) - '0';
            }
            int sum = first + second + add;
            add = 0;
            if (sum >= 2) {
                add = 1;
            }
            sb.append(sum % 2);
            i--;
            j--;
        }
        if (add == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }
}