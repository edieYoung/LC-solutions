/**
/* Given a string, find the length of the longest substring without repeating characters.
**/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        String ex = "";
        int max = 0;
        if(s.length()==1){
            return 1;
        }
        for(int i = 0;i<s.length();i++){
            String cs1 = Character.toString(s.charAt(i));
            if(!ex.contains(cs1)){
                ex+=s.charAt(i);
                
            }else{
                if(ex.length()>max){
                   max = ex.length(); 
                }
                ex = ex.substring(ex.indexOf(cs1)+1)+s.charAt(i);
            }
            if(i==s.length()-1&&max<ex.length()){
               max = ex.length();
            }
        }
        return max;
    }
}