# Leet Code
## Facebook:
###[Roman2Integer](https://github.com/Blankj/awesome-java-leetcode/blob/master/note/013/README.md)
#### Answer:

```
class Solution {
  Map<Character, Integer> char2Num = new HashMap<>(); 
  
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.print(s.Roman2Integer("LVIII"));
  }
  public int Roman2Integer(String s){
    char2Num.put('I', 1);
    char2Num.put('V', 5);
    char2Num.put('X', 10);
    char2Num.put('L', 50);
    char2Num.put('C', 100);
    char2Num.put('D', 500);
    char2Num.put('M', 1000);
    int sum = 0;
    int part = 0;
    int pre = 0;
    for (int i=0; i<s.length();i++){
      char c = s.charAt(i);
      if(char2Num.get(c)>char2Num.get(s.charAt(pre))){
        pre = i;
        part = char2Num.get(c) - part;
      }else{
        pre = i;
        sum += part;
        part = char2Num.get(c);
      }
    }
    sum+=part;
    return sum;
  }
}
```
#### Solution: 
1. Use a map to quickly find responding numbers for each char.
2. map can be instantiated inside method. 
3. In static main method, need to new a class and call its function.
4. Think this Roman string as a bunch of climbing numbers connected part by part, which means in each part, the pre char is smaller than the next char. And finally add up each part, this can be tracked by a "sum".
5. Don't forget to add the last part into sum when the loop finished!

### [Max Substring](https://github.com/Blankj/awesome-java-leetcode/blob/master/note/053/README.md)
#### Answer 1:

```
class Solution {
    public int maxSubArray(int[] nums) {    
    int sum = 0;
    int max = nums[0];
    for(int i = 0;i<nums.length;i++){
      sum += nums[i];
      if(sum>max){
         max = sum; 
      }
      if(sum<0){
        sum = 0;
      }
    }
    return max;
    }
}
```

#### Solution 1:
1. it can be done in O(n), if now the sum is larger than 0, so adding the next one will still give a possible more larger sum. However, if it is less than 0, then the previous part is negative, so drop it, and start with this new element holding now.
2. Besides, judge if the sum now is larger than max, and keep it as max if it is larger. This judge logic should be separated from 1, because even if now the sum is less than 0, it could still be the max subarray in this particular array.
3. Be sure make max as first element of the array at first, since it could be the only one element in array. maxSubArray([1]) -> 1
4. The sum can be optimized since it doesn't need to be stored, by directly judge it while adding:
5. Using Math.max() to judge and give value to maxNum will enhance the speed! 

```
class Solution {
    public int maxSubArray(int[] nums) {    
    int sum = 0;
    int max = nums[0];
    for(int i = 0;i<nums.length;i++){
      sum = nums[i] + (sum<0? 0:sum);
      max = Math.max(sum, max);
    }
    return max;
    }
}
```

#### Answer 2:
```
class Solution:
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        maxSum = nums[0]
        return self.de(nums, 0, 0, maxSum)
    
    def de(self, nums, i, nowSum, maxSum):
        if i>=len(nums):
            return maxSum
        nowSum+=nums[i]
        if nowSum>maxSum:
            maxSum = nowSum
        if nowSum < 0:
            return self.de(nums,i+1, 0, maxSum)
        else:
            return self.de(nums,i+1, nowSum, maxSum)
```
#### Solution 2: 
1. Use Divide and Conquer. 
2. Using index to "slice" the list instead of directly use nums[0:], can make less memory use and avoid stack overflow.


### [3Sum](https://leetcode.com/problems/3sum/description/)
#### Answer 1
```
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        
        if(nums.length<3){
            return new ArrayList(ans);
        }
        Map<Integer, Integer> numMap = new HashMap<>();
        Arrays.sort(nums);
        for(int i = 0;i<nums.length-1;i++){
            int j = i+1;
            while(j<nums.length){
                if(j<nums.length-1&&nums[j]==nums[j+1]){
                    j++;
                    continue;  
                }
                int target = 0-nums[i]-nums[j];
                if(numMap.containsKey(target)){
                    int[] a = {nums[i], nums[j], target};
                    Arrays.sort(a);
                    ans.add(Arrays.asList(a[0],a[1],a[2]));             
                }
                j++;
            }
            numMap.put(nums[i],1);
        }
        return new ArrayList(ans);
    }
}
```

#### Solution 1
1. Use set and add sorted list to avoid repeating.
2. sort the nums array at first(unnecessary here if j need not to accelerate). Note here accelerating j(sorted first) can already speed up somehow.
3. Use a map to find the last target.(only put element after each i)


#### Answer 2
```
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        
        if(nums.length<3){
            return new ArrayList(ans);
        }
        Map<Integer, Integer> numMap = new HashMap<>();
        Arrays.sort(nums);
        for(int i = 0;i<nums.length-1;i++){
            int j = i+1;
            while(j<nums.length){
                
                int target = 0-nums[i]-nums[j];
                if(numMap.containsKey(target)){
                    int[] a = {nums[i], nums[j], target};
                    Arrays.sort(a);
                    ans.add(Arrays.asList(a[0],a[1],a[2]));             
                }
                j++;
            }
            numMap.put(nums[i],1);
        }
        return new ArrayList(ans);
    }
}
```
#### Solution 2:
1. Use Map to keep every element once(the max index)
2. i and j from 0 and i+1 to nums.length
3. target should be found in map and map.get(target)>j
4. every increase for i and j should bring i and j to map.get(nums[i or j])+1


#### Faster Answer
```
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        if(nums == null || nums.length < 3) return result;
        for(int i = 0; i < nums.length - 1; i++){
            if (i > 0 && nums[i] == nums[i - 1]) 
                continue;
            helper(-nums[i], nums, i + 1);
        }
        return result;
    }
    private void helper(int target, int[] nums, int i){
        int j = nums.length - 1;
        while(i < j){
            if(nums[i] + nums[j] == target) {
                result.add(Arrays.asList(nums[i], nums[j], -target));
                i++; 
                j--;
                while (i < j && nums[i] == nums[i - 1]) i++;  
                while (i < j && nums[j] == nums[j + 1]) j--;
            }
            else if(nums[i] + nums[j] < target) i++;
            else j--;    
        }
    }
}
```
#### Solution
1. Sort nums at first to make clear the information(target >= j >= i)
2. If i+j < target, i++, else j--. (i++ will make i+j larger)
3. If i+j==target, i and j jump to next not the same as previous.(i++,j--)
4. A target should be at first made(no repeat), then find 2 sum in nums after it.
5. Remember corner case!! if(nums == null || nums.length < 3) return result;

### [Minimum-depth-of-binary-tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/)

#### Answer 1
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int minD = Integer.MAX_VALUE;
    TreeNode head;
    public int minDepth(TreeNode root) {
        head = root;
        int depth = findDepth(root);
        if(minD!=Integer.MAX_VALUE) return minD;
        return 0;
    }
    public int findDepth(TreeNode root){
        int depth =0;
        if(root!=null){
            int left = findDepth(root.left)+1;
            int right = findDepth(root.right)+1;
            if(left!=1&&right!=1){
                depth = Math.min(left,right);
            }else if(left==1&&right==1){
                depth = 1;
            }
            else{
                depth = left==1? right:left;
            }
            if(root==head){
                minD = Math.min(minD, depth);
            }
        }else if(root==null){
            depth = 0;
        }
        return depth;
    }
}
```

#### Solution 1:
1. Keep a global minD. Compare minD and depth only when the depth is root's.
2. DFS the tree, return the minimum depth of this node(findDepth+1)
3. Remember consider the null tree(minD won't change)!!!
4. The minimum depth of this node could be
   * min(leftDepth, rightDepth)+1
   * leftDepth(no right) or rightDepth(no left)
   * 1(the leaf)
   
   
#### Improvement
```
class Solution {
    public int minDepth(TreeNode root) {
        if (root==null) return 0;
        int r = minDepth(root.right);
        int l = minDepth(root.left);
        if(r==0&&l==0){
            return 1;
        }else if(r!=0&&l!=0){
            return Math.min(r,l)+1;
        }else{
            return r+l+1;
        }   
    }

}
```
#### Explaination:
1. Directly use minDepth as return min depth
2. root==null, return 0(the same as the leaf)
3. if r or l is 0, return r+l+1(is equal to r+1 or l+1)

   

#### Answer 2:
```
class Solution {
    int minD = Integer.MAX_VALUE;
    TreeNode head;
    public int minDepth(TreeNode root) {
        if(root==null){
          return 0;  
        }
        findDepth(root, 1);
        return minD;
    }
    public void findDepth(TreeNode root, int depth){
        if(root.left==null&&root.right==null){
            minD = Math.min(depth,minD);
        }
        if(root.right!=null){
            findDepth(root.right,depth+1);
        }
        if(root.left!=null){
            findDepth(root.left,depth+1);
        }

    }
}  
```



#### Solution 2

1. depth is sent to child, and change MinD when it is a leaf(no right & no left)
2. Judge whether the child node is null in this node.
3. No need to return depth. Just DFS.
4. If the root is null, return 0
  
### [Contain Most Water](https://leetcode.com/problems/container-with-most-water/description/)
  
#### Answer 1:
```
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length-1;
        while(l<r){
            int h = Math.min(height[r],height[l]);
            max = Math.max(max,h*(r-l));
            if(height[r]>=height[l]) l++;
            else if(height[r]<height[l]) r--;
        }
        return max;
        
    }
}
```

#### Solution 1:
1. Area = (l-r)*min(h(l),h(r))
2. Set up l and r from front and back, since r-l is less, if h(l)>h(r),move r-- can find a higher h(r), so Area can be larger. Else if h(r)>=h(l), move l++



### [3 Sum Closest](https://leetcode.com/problems/3sum-closest/description/)
#### Answer:
```
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int closeDif=Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(int i = 0;i<nums.length-2;i++){
            int dif = target-nums[i];
            int l = i+1;
            int r = nums.length-1;
            while(l<r){
            closeDif = Math.abs(dif-nums[r]-nums[l])<=Math.abs(closeDif)?dif-nums[r]-nums[l]:closeDif;
            if(nums[l]+nums[r]<dif){
                l++;
            }else if(nums[l]+nums[r]==dif){
                return target;
            }else{
                r--;
            }
            }
            
        }
        return target-closeDif;
    }
}
```

#### Solution: 
1. For loop i, and l and r from i+1 and nums.length-1
2. a closeDif to save the min difference from target
3. if nums[r]+nums[l]=target-nums[i],return target. if nums[r]+nums[l]<target-nums[i], then l++ to make a larger sum; else r--
4. return target-closeDif

### [Remove Nth Node From The End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/)
#### Answer 1:
```
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int order = findOrder(head, n);
        if(order==n){
            return head.next;
        }
        return head;
    }
    public int findOrder(ListNode node, int n){
        int order;
        if(node!=null){
            order = findOrder(node.next,n)+1;
        }else{
            order =  0;
        }
        if(order == n+1){
            node.next = node.next.next;     
        }
        return order;
    }
}
```

#### Solution 1:
1. Use recursion to find order of each node.
2. Delete: the node(order = n+1) do the action: node.next = node.next.next
3. If the node needed to be deleted is head.(no node's order can be n+1). return head.next to delete the head.


### [Pow(n,x)](https://leetcode.com/problems/powx-n/description/)
#### Answer:
```
class Solution {
    public double myPow(double x, int n) {
        double ans = 1;
        if(x==0){
           return 0; 
        }
        if(n==0){
            return 1;
        }
        if(n==1){
           return x; 
        }
        if(n<0){
           return 1/x *myPow(1/x,-(n+1)); 
        }else{
            if(n%2==0){
                ans = myPow(x*x,n/2);
            }else{
                ans = x*myPow(x*x,n/2);
            } 
        }
        return ans;
    }
}
```

#### Solution:
1. Recursion can do it.
2. But every time use Pow(x*x, n/2) to decrease recursion times
3. x*Pow(x,n-1) will Stack overflow
4. Check whether the n is an odd or even
5. When n<0, need to firstly 1/x*Pow(1/x,-(n+1)) -> new n will be a positive
Because when n = -2147483648, Pow(x,-n) will exceed int range

### [Length of Last Word](https://leetcode.com/problems/length-of-last-word/description/)
#### Answer 1:
```
class Solution:
    def lengthOfLastWord(self, s):
        """
        :type s: str
        :rtype: int
        """
        
        x = s.split()
        if(len(x)==0):
            return 0
        return len(x[len(x)-1])
```
#### Solution 1:
1. python split() is useful here(but split(' ') will add the ' ' into list as well)
2. Return 0, if the s is an empty or all blank string.

#### Answer 2:
```
class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length()-1;
        boolean start = false;
        int count = 0;
        while(i>=0){
            if(s.charAt(i)!=' '){
                count++;
                start = true;
            }
            if(start&&s.charAt(i)==' '){
                start = false;
                return count;
            }
            i--;
        }
        return count;
    }
}
```
#### Solution 2:
1. Find from the end, the first char is not empty will count
2. End until find a blank.
3. Corner case! 




