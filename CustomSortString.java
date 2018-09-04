class Solution {
    
    Map<Character, Integer> smap = new LinkedHashMap<>();
    public String customSortString(String S, String T) {
        int[] count = new int[26];
        for(int i = 0;i<S.length();i++){
            smap.put(S.charAt(i), i);
        }
        
        StringBuilder sb = new StringBuilder();
        StringBuilder last = new StringBuilder();
        StringBuilder s = new StringBuilder();
        for(int i = 0;i<T.length();i++){
            
           if(!smap.containsKey(T.charAt(i))){
               last.append(T.charAt(i));
           }else{
               count[T.charAt(i)-'a']++;
           } 
        }
        for(char key : smap.keySet()){
            for(int j = 0; j<count[key-'a'];j++){
                sb.append(key);
                
            }
        }
       
        return sb.toString()+last.toString();
    }
 
}
