/**
* Implement a basic calculator to evaluate a simple expression string.
* The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
* The integer division should truncate toward zero.
**/
class Solution {
    
    public int calculate(String s) {
       int ans = 0;
        int prev = 0;
        int num = 0;
        char op = '+';
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(c==' '){
                continue;
            }
            if(Character.isDigit(c)){
                num = c - '0';
                
                
                    while(i!=s.length()-1&&Character.isDigit(s.charAt(i+1))){
                        num = num*10 + (s.charAt(i+1) - '0');
                        i++;
                    }
                
            
                if(op=='+'){
                    ans = ans+num;
                    prev = num;
                }
                if(op=='-'){
                    ans = ans-num;
                    prev = -num;
                }
                if(op=='*'){
                    ans = ans-prev+prev*num;
                    prev = prev*num;
                }
                if(op=='/'){
                    ans = ans-prev+prev/num;
                    prev = prev/num;
                }
            }else{
                op = c;
            }

        }
        return ans;
        }
    
}