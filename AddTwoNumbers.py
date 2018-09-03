# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def __init__(self):
        self.point = ListNode(0)
        self.head = self.point
    
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        remain = 0
        self.add(l1, l2, remain)
        return self.head.next
        
    def add(self, l1, l2, remain):
        value = (l1.val + l2.val + remain)%10
        self.point.next = ListNode(value)
        self.point = self.point.next
        # print(value)
        remain = int((l1.val + l2.val + remain)/10)
        
        if l1.next and l2.next:
            self.add(l1.next, l2.next, remain)
        elif l1.next:
            self.add(l1.next, ListNode(0), remain)
        elif l2.next:
            self.add(ListNode(0), l2.next, remain)
        elif remain>0:
            self.point.next = ListNode(remain)
