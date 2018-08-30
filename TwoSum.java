class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int i = 0; i< nums.length; i++){
                if(numMap.containsKey(target-nums[i])){
                    ans[0]=i;
                    ans[1]=numMap.get(target-nums[i]);
                    break;
                }else{
                    numMap.put(nums[i], i);
                }
            }
        
        
        return ans;
    }
}
