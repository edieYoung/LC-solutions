/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
       ListNode ans = new ListNode(0);
        ListNode head = ans;
        if(l1==null&&l2!=null){
            return l2;
        }else if(l2==null&&l1!=null){
            return l1;
        }else if(l1==null&&l2==null){
            return null;
        }
       while(l1!=null&&l2!=null){
           if(l1.val<=l2.val){
            ans.next = l1;
            ans = ans.next;
            if(l1.next!=null){
                l1 = l1.next;
            }else{
                ans.next = l2;
                break;
            }
            
        }else{
            ans.next = l2;
            ans = ans.next;
               if(l2.next!=null){
                   l2 = l2.next;
               }else{
                   ans.next = l1;
                   break;
               }
            
         }
      }
    return head.next;    
        
    }
    
}
