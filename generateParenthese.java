class Solution {
    List<String> parentheseList = new ArrayList<>();
    Stack<Character> pstack = new Stack<>();
    public List<String> generateParenthesis(int n) {
        generateString(n, 0, 0);
        return parentheseList;
    }
    public void generateString(int n, int left, int right) {
        
        if(pstack.size()<2*n&&left<n){
            pstack.push('(');
            left++;
            generateString(n, left, right);
            pstack.pop();
            left--;
        }
        if(left>right){
            pstack.push(')');
            right++;
            generateString(n, left, right);
            pstack.pop();
            right--;        
        }
        if(pstack.size()==2*n&&right==left){
            String p = "";
            for(Character c:pstack){
                p+=c;
            }
            parentheseList.add(p);
        }
        
    }
    
    
}