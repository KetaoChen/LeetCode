class Solution {
	//most important is how to add the space between each word. 
	//devide the problem into several levels. 
	//== 0		return "" with no space. because all higher level have space.
	//<20		this is the lowest level, return lessTwenty[num];
	//<100		this is tens[num % 10] + " " + helper(num / 10);
	//<1000		this is [num % 100] + " Hundred " + helper(num % 100);
	//Becasue all the values end with a space, so before each thousands level, do not need a space.
	//>= 1000	repeat the whole method, helper(num % 1000) + thousands[level] + " " + res;
	
    private final String[] lessTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] thousands = {"", "Thousand", "Million", "Billion"};
    
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        
        String res = "";
        int level = 0;
        
        while (num > 0) {
            if (num % 1000 != 0) {
                res = helper(num % 1000) + thousands[level] + " " + res;
            }
            num = num / 1000;
            level++;
        }
        return res.trim();
    }
    
    private String helper(int num) {
        if (num == 0) {
            return "";
        }
        else if (num < 20) {
            return lessTwenty[num] + " ";
        }
        else if (num < 100) {
            return tens[num / 10] + " " + helper(num % 10);
        }
        else if (num < 1000) {
            return lessTwenty[num / 100] + " Hundred " + helper(num % 100);
        }
        return "";
    }
}