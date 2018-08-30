class Solution {
    public int reverse(int x) {
        String s = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        long l;
        if(s.charAt(0)=='-'){
            s = s.substring(1);
            sb.append(s);
            sb.reverse();
            l = 0-Long.parseLong(sb.toString());
            if(l<Integer.MIN_VALUE){
                return 0;
            }else{
                return (int) (long) l;
            }
        }else{
            sb.append(s);
            sb.reverse();
            l = Long.parseLong(sb.toString());
            if(l>Integer.MAX_VALUE){
                return 0;
            }else{
                return (int) (long) l;
            }
        }
    
        
        
        
    }
}
