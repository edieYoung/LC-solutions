/**
/*Find and Replace Pattern
**/
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        String p = "";
        for(int j = 0;j<pattern.length();j++){
            
        }
        for(int i = 0; i<words.length; i++){
            
            if(match(words[i], pattern)){
                ans.add(words[i]);
            }
        }
        return ans;
    }
    public boolean match(String word, String p){
        if(word.length()!=p.length()){
            return false;
        }else{
            HashMap<Character, Character> permulation = new HashMap<Character, Character>();
            for(int i = 0;i<word.length();i++){
                if(permulation.containsKey(p.charAt(i))){
                    if(permulation.get(p.charAt(i))!=word.charAt(i)){
                        return false;
                    }else{
                        if(i==word.length()-1){
                            return true;
                        }else{
                            continue;
                        }
                    }
                }else{
                    if(permulation.containsValue(word.charAt(i))){
                        return false;
                    }
                    permulation.put(p.charAt(i), word.charAt(i));
                    if(i==word.length()-1){
                        return true;
                    }else{
                        continue;
                    }
                }
            }
            return false;
            
        }
    }
}