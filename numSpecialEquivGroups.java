class Solution {
    public int numSpecialEquivGroups(String[] A) {
        List<String> conf = new ArrayList<>();
        for(int i = 0;i<A.length;i++){
            String newString1 = "";
            String newString2 = "";
            for(int j = 0;j<A[i].length();j++){
                if(j%2==0){
                    //奇数
                    if(newString1.length()==0){
                        newString1 += A[i].charAt(j);
                    }else{
                        if(Integer.valueOf(A[i].charAt(j)) <= Integer.valueOf(newString1.charAt(0))){
                               newString1= A[i].charAt(j)+newString1;
                            }else if(Integer.valueOf(A[i].charAt(j))>=Integer.valueOf(newString1.charAt(newString1.length()-1))){
                            newString1= newString1+A[i].charAt(j);
                        }else{
                        for(int k = 0;k<newString1.length();k++){
                            
                            if(Integer.valueOf(A[i].charAt(j))<Integer.valueOf(newString1.charAt(k))){
                                newString1 = newString1.substring(0,k)+ A[i].charAt(j)+newString1.substring(k);
                                
                            break;
                        } 
                    }
                        }
                    }
                    
                }else{
                    if(newString2.length()==0){
                        newString2+=A[i].charAt(j);
                    }else{
                       if(A[i].charAt(j)<=newString2.charAt(0)){
                               newString2= A[i].charAt(j) + newString2;
                            }else if(A[i].charAt(j)>=newString2.charAt(newString2.length()-1)){
                            newString2= newString2+A[i].charAt(j);
                        }else{ 
                    for(int k = 1;k<newString2.length();k++){
                       if(A[i].charAt(j)<newString2.charAt(k)){
                            newString2 = newString2.substring(0,k)+A[i].charAt(j)+newString2.substring(k);
                           
                           break;
                           
                        } 
                    }
                    }
                    
                }
                
                }
            }
            if(!conf.contains(newString1+newString2)){
                conf.add(newString1+newString2); 
                
            }
              
        }
        System.out.print(conf);
        return conf.size();
    }
}