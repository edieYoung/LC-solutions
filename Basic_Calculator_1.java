/**
* Implement a basic calculator to evaluate a simple expression string.
* The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
* The integer division should truncate toward zero.
**/

class Solution {
    int offset1 = 0;
    
    public int calculate(String s) {
        for(int i = 0;i<s.length();i++){
            
    
            if(s.charAt(i)=='*'){
                int fac1 = readNumber(s.substring(i+1));
                int fac2 = readNumberFromBack(s.substring(0, i));
                
                    s = s.substring(0, i-String.valueOf(fac2).length())+fac1*fac2+s.substring(i+String.valueOf(fac1).length()+1+offset1);
                
                i= i+String.valueOf(fac1*fac2).length()-String.valueOf(fac2).length()-1;
                offset1 = 0;
            }
        
            if(s.charAt(i)=='/'){
                int div = readNumber(s.substring(i+1));
                //System.out.println(offset1);
                int den = readNumberFromBack(s.substring(0, i));
                
                    s = s.substring(0, i-String.valueOf(den).length())+den/div+s.substring(i+String.valueOf(div).length()+1+offset1);
                
                i= i+String.valueOf(den/div).length()-String.valueOf(den).length()-1;
                offset1 = 0;
            }
            
            if(s.charAt(i)==' '){
                s = s.substring(0,i)+s.substring(i+1);
                i--;
                //System.out.print(s);
            }
        
        }
        //System.out.print(s);
        return calculateSubString(s);               
    }
    public int calculateSubString(String s){
        int number = readNumberFromBack(s);
        if(s.length() == String.valueOf(number).length()){
            return number;
        }
        
        if(s.charAt(s.length()-1-String.valueOf(number).length())=='+'){
            return number+calculateSubString(s.substring(0, s.length()-String.valueOf(number).length()-1));
        }
        
        if(s.charAt(s.length()-1-String.valueOf(number).length())=='-'){
            return calculateSubString(s.substring(0, s.length()-String.valueOf(number).length()-1)) - number;
        }
        return 0;
        
    }
    public int readNumber(String s) {
        String number = "";
        offset1 = 0;
        for(int i = 0; i<s.length();i++){
            
            if(s.charAt(i)!='*'&&s.charAt(i)!='/'&&s.charAt(i)!='+'&&s.charAt(i)!='-'&&s.charAt(i)!=' '){
                number+=s.charAt(i);
            }else if(s.charAt(i)==' '){
                offset1++;
            }else{
                return Integer.valueOf(number);
            }
            
        }
        return Integer.valueOf(number);
    }
    
    public int readNumberFromBack(String s) {
        String number = "";
        for(int i = s.length()-1; i>=0;i--){
            
            if(s.charAt(i)!='*'&&s.charAt(i)!='/'&&s.charAt(i)!='+'&&s.charAt(i)!='-'){
                number = s.charAt(i) + number;
            }else{
                return Integer.valueOf(number);
            }
        }
        return Integer.valueOf(number);
    }
}