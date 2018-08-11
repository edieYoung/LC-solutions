/**
 * 257
 * Given a binary tree, return all root-to-leaf paths. 
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<String> answerList = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if(root!=null){
            String route = String.valueOf(root.val);
            search(root, route);
            return answerList;
        }else{
            return answerList;
        }
           
    }
    
    public void search(TreeNode root, String route) {
        if(root.left!=null){
            route = route + "->" + String.valueOf(root.left.val);
            search(root.left, route);
            route = route.substring(0,route.length()-String.valueOf(root.left.val).length()-2);
        }
        
        if(root.right!=null){
            route = route + "->" + String.valueOf(root.right.val);
            search(root.right, route);
            route = route.substring(0,route.length()-String.valueOf(root.right.val).length()-2);
        }
            
        if(root.right==null&&root.left==null){
            answerList.add(route);
            
        }
        
    }
}