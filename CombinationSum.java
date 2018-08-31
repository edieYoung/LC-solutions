class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] cand, int target) {
    
        List<Integer> temp = new ArrayList<>();
        int cur = 0;
        Arrays.sort(cand);
        dfs(cur, temp, cand, target);
        return ans;
        
    }
    public void dfs(int cur, List<Integer> temp, int[] cand, int target){
        if(target==0){
            
            ans.add(new ArrayList(temp));
            //temp.remove(temp.size()-1);
            return;
        }else if(target<0){
           // temp.remove(temp.size()-1);
            return;
        }else{
            
            for(int i = cur; i<cand.length;i++){
                if(i>cur&&cand[i]==cand[i-1]){
                    continue;          
                }else{
                    temp.add(cand[i]);
                    dfs(i+1, temp, cand, target-cand[i] );
                    temp.remove(temp.size()-1);
                    
                }
            }
        }
       
    }
}
