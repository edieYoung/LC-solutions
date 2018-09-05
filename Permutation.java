class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        Integer[] what = Arrays.stream( nums ).boxed().toArray( Integer[]::new );
        List<Integer> newList = Arrays.asList(what);
        List<Integer> temp = new ArrayList<>();
        sit(newList, temp);
        return ans;
    }
    public void sit(List<Integer> nums, List<Integer> temp){
        if(nums.size()==1){
            temp.add(nums.get(0));
            ans.add(new ArrayList(temp));
            temp.remove(nums.get(0));
        }else{
            for(Integer i:nums){
                temp.add(i);
                List<Integer> tempList = new ArrayList<>();
                tempList.addAll(nums);
                tempList.remove(i);
                sit(tempList, temp); 
                temp.remove(i);
            }
        }
    }
}
