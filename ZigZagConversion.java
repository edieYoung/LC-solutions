ass Solution {
    public String convert(String s, int numRows) {
        if(numRows==1){
          return s;  
        }
        
        String[] temp = new String[numRows];
        int j = 0;
        boolean down = true;
        for(int i = 0; i<s.length();i++){
            if(down){
                while(j<numRows){
                    
                    if(temp[j]==null){
                        temp[j] = Character.toString(s.charAt(i));
                    }else{
                        temp[j] +=  s.charAt(i);
                    }
                    System.out.print(j);
                    j++;
                    
                    break;
                }
                if(j == numRows){
                    down = false;
                    j--;
                    j--;
                }
            }else{
                while(j>=0){
                    if(temp[j]==null){
                        temp[j] = Character.toString(s.charAt(i));
                    }else{
                        temp[j] +=  s.charAt(i);
                    }
                    System.out.print(j);
                    j--;
                    break;
                }
                if(j==-1){
                    down = true;
                    j++;
                    j++;
                }
            }  
        }
        String ans = new String();
        
        for(int i = 0;i<numRows;i++){
            if(temp[i]!=null){
                ans+=temp[i];
            }
        }
        return ans;
        
    }
}
