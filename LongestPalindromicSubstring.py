class Solution:
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        res = ""
        for i in range(0,len(s)):
            temp = self.check(s, i, i)
            if len(temp)>len(res):
                res = temp
            temp = self.check(s,i,i+1)
            if len(temp)>len(res):
                res = temp    
        return res
    
    def check(self, s, l, r):
        while l>=0 and r < len(s) and s[l] is s[r]:
            l-=1
            r+=1
        return s[l+1:r]
        