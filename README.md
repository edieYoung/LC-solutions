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

### [Integer to Roman](https://leetcode.com/problems/integer-to-roman/description/)
#### Answer 1:
```
class Solution {
    public String intToRoman(int num) {
        String[] I = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
        String[] X = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] C = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        String[] M = {"","M","MM","MMM"};
        return(M[(num/1000)%10]+C[(num/100)%10]+X[(num/10)%10]+I[num%10]);
        
    }
}
```
#### Solution 1:
1. set up each permulation
2. module the num to get each part's representation of Roman char.

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


### [Valid Parenthese](https://leetcode.com/problems/valid-parentheses/description/)
#### Answer 1:
```
class Solution {
    Map<Character, Integer> bMap = new HashMap<>();
    
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(c =='('||c =='{'||c =='['){
                st.push(c);
            }else{
                if(st.size()>0){
                    if(!isMatch(st.peek(),c)){
                        return false;
                    }else{
                        st.pop();
                    }
                }else{
                    return false;
                }
            }
        }
        if(st.size()==0){
          return true;  
        }
        return false;
        
    }
    public boolean isMatch(char a, char b){
        if(a=='('&&b==')'){
            return true;
        }
        if(a=='['&&b==']'){
            return true;
        }
        if(a=='{'&&b=='}'){
            return true;
        }
        return false;
        
    }
    
}
```
#### Solution 1:
1. Use stack to match.


### [Palindrome Number](https://leetcode.com/problems/palindrome-number/description/)
#### Answer 1:
```
class Solution {
    public boolean isPalindrome(int x) {
        Stack<Character> st = new Stack<>();
        String s = String.valueOf(x);
        boolean out = false;
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(out){
                if(c!=st.pop()){
                    return false;
                }
            }else{
                st.push(c);
            }
            if(s.length()%2==0){
                if(i==s.length()/2-1){
                    out = true;     
                }
            }else{
                if(i==s.length()/2){
                    out = true;
                    st.pop();
                }
            }    
        }
        return true;
    }
}
```

#### Solution 1:
1. Use stack to match two parts
2. If the length is odd, start matching when i = length/2, remember pop out this i before matching.
3. If the length is even, start matching when i = length/2-1.


### [First Bad Version]()
#### Answer 1:
```
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;
        while(l<r){
            int mid = l+(r-l)/2;
            if(isBadVersion(mid)){
                r = mid;
            }else{
                l = mid+1;
            }
        }
        return l;
        
        
    }
}
```
#### Solution 1:
1. binary search O(logN)
2. the left point can move to mid+1(because mid has already checked is good version)
3. calculate the mid, need to be careful about exceed the integer limit(2^31). 



### [Path Sum](https://leetcode.com/problems/path-sum/description/)
#### Answer 1:
```
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
          return false;  
        }
        if(root.right==null&&root.left==null){
            if(root.val==sum){
                return true;
            }
        }
        boolean right = false;
        boolean left = false;
        if(root.right!=null){
            right = hasPathSum(root.right, sum-root.val);
        }
        if(root.left!=null){
            left = hasPathSum(root.left, sum-root.val);
        }
        if(!right&&!left){
            return false;
        }else{
            return true;
        }
        
    }
}
```
#### Solution 1:
1. DFS, find the root
2. Hierachical return whether finding to parent node



###[Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/description/)
### Answer 1:
```
class Solution {
    public ListNode swapPairs(ListNode head) {
        return swap(head);
 
    }
    public ListNode swap(ListNode head){
        ListNode nextNode=null;
        if(head!=null&&head.next!=null){
            nextNode = head.next;
            head.next = swap(head.next.next==null?null:head.next.next);
            nextNode.next = head;
        }else{
            if(head==null){
                return null;
            }
            if(head.next==null){
                return head;
            }
        }
        return nextNode;
    }
}
```
### Solution 1:
1. recursively find the new head.(head.next do not immediately = head.next.next, because the after node still will be swapped as well.)
2. function return the new head. and recursively make the head.next = swap(head.next.next).
3. keep the original "head.next" as nextNode, don't make nextNode.next = head before swap(head.next.next), otherwise, head.next.next will turn to be head itself!
4. the terminal condition should be separated, if head==null, no consider about head.next.


###[Remove Duplicates from Sorted List](https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/)
#### Answer 1:
```
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = head;
        delete(head);
        return newHead;
    }
    void delete(ListNode head){
        if(head!=null&&head.next!=null){
        if(head.next.val == head.val){
            head.next = head.next.next==null?null:head.next.next;
            delete(head);
        }else{
            delete(head.next);
        }
        
        }
    }
}
```
#### Solution 1:
1. a void return recursion function to delete the next duplicated element
2. if there is a duplicate element, delete the next, and keep do deleting to this head.
3. else, nothing happen to head, but do delete to head.next.


###[Remove Duplicates from Sorted List II](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/)
#### Answer 1:
```
class Solution {
    public ListNode deleteDuplicates(ListNode head){
        ListNode newHead = head;
        if(head!=null){
            if(head.next==null){
                return newHead;
            }
            if(head.next.val!=head.val){
                newHead.next = deleteDuplicates(head.next);
                return newHead;
            }
            //delete the original head
            while(head.next!=null&&head.next.val==head.val){
                head = head.next;
            }
            newHead = deleteDuplicates(head.next);
        }
        return newHead;
    } 
}
```
#### Solution 1:
1. firstly decide whether head and head.next is repeat, if it's not, newHead.next = delete(head.next);
2. if it is, while loop to keep finding a head.next is not equal with head(head = head.next). Then newHead = delete(head.next);(newHead will be replaced).
3. terminal condition: if head=null(return null), if head.next=null(return head)


### Single
#### Answer:
```
public class solution(){
	public static void main(String[] args){
		solution s = new solution();
		System.out.print(s.single());
	}
	public int single(List<Integer> a){
		int sum=0;
		for(int i:a){
			sum ^= i;
		}
		return sum;
	}
}
```

### NUM1BITS
#### Answer:
```
public class Solution(){
	public static void main(String[] args){
		Solution s = new Solution();
		System.out.print(onebit());
	}
	public int onebit(long a){
		int count = 0;
		for(int i = 1;i<33;i++){
			count += a&1;
			a/=2;
		}
		return count;
	}
}
```

### [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/description/)
#### Answer 1:
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
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return syme(root.left,root.right);
    }
   
  public boolean syme(TreeNode left, TreeNode right){
    if(right==null&&left==null){
      return true;
    }else if(right==null||left==null){
      return false;
    }
    if(right.val!=left.val){
      return false;
    }
    if(!syme(left.right,right.left)||!syme(left.left,right.right)){
      return false;
    }
    return true;
  }
}
```
#### Solution 1:
1. use recursion to find that one's right is equal to another's left.
2. careful about the null root, and one child is null.

### [Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/description/)
#### Answer 1:
```
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        for(int i = 0;i<s.length();i++){
            count++;
            int j=1;
            if(i+1<s.length()&&s.charAt(i+1)==s.charAt(i)){
                count++;
                while(i-j>=0&&i+1+j<s.length()){
                if(s.charAt(i-j)==s.charAt(i+1+j)){
                    count++;   
                }else{
                    break;
                }
                    j++;
                }
            }
            j = 1;
            while(i-j>=0&&i+j<s.length()){
                if(s.charAt(i-j)==s.charAt(i+j)){
                    count++;   
                }else{
                    break;
                }
                j++;
            }
        }
    return count;  
    }
}
```
#### Solution 1:
1. for every i, set the half range j, find if two sides are equal.
2. for every pair of equal s(i) and s(i+1), also set the range j to find two sides for them.('aa' is also Palindromic Substrings)
3. if two sides are not equal, need to break!!!

### [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)
#### Answer 1:
```
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int count = 0;
        String temp = "";
        
        for(int i = 0;i<s.length();i++){
            String a = String.valueOf(s.charAt(i));
            if(!temp.contains(a)){
                temp+=a;
                count = temp.length();
                maxLength = Math.max(count, maxLength);
            }else{
                temp+=a;
                int end=temp.indexOf(s.charAt(i))+1;
                temp = temp.substring(end);
                count = temp.length();   
            }
        }
        return maxLength;
    }
}
```
#### Solution 1:
1. string has contains(), indextOf(), substring(), contains() needs a String instead of Character.
2. find the first occurred repeating one's index j, substring from j+1;
3. max the count;

### [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/)
#### Answer 1:
```
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length==0){
           return 0; 
        }
        int pre = nums[0];
        int ind = 0;
        int count = 1;
        for(int i = 1;i<nums.length;i++){
            if(nums[i]==pre){
                continue;  
            }else{
                count++;
                nums[ind+1] = nums[i];
                pre = nums[i];
                ind = ind+1;
            }
        }
        return count;
    }
}
```
#### Solution 1:
1. just replace the next repeating element with a new not repeating element.
2. Corner case! empty array!!!!!!!!!!


### [Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/description/)
#### Answer:
```
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
      
    //boundary check
    if(headA == null || headB == null) return null;
    
    ListNode a = headA;
    ListNode b = headB;
    
    //if a & b have different len, then we will stop the loop after second iteration
    while( a != b){
    	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
        a = a == null? headB : a.next;
        b = b == null? headA : b.next;    
    }
    
    return a;

    }
}
```
#### Solution:
1. two list's diff can be fixed (end to here)on the longer one in first iteration of the shorter one.
2. find the longer one's number diff 's node by moving the fixed one to the end, and head follows.
3. Now iterate together from the longer list' number diff node and the head of the shorter list, the common node must occur.
4. if the diff is 0, first iteration can find the common one.

