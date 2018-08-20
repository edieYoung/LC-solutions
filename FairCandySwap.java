/**
/* Fair Candy Swap
**/
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int Alice = 0;
        for(int i = 0;i<A.length;i++){
            Alice+=A[i];
        }
        int Bob = 0;
        for(int j = 0;j<B.length;j++){
            Bob+=B[j];
        }
        int dif = Alice-Bob;
        int[] ans = new int[2];
           for(int i = 0;i<A.length;i++){
              for(int j = 0;j<B.length;j++){
                  if((A[i]-B[j])*2==dif){
                      ans[0] = A[i];
                      ans[1] = B[j];
                      
                  }
              }
           }
        return ans;
        
    }
}