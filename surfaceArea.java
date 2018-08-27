class Solution {
    public int surfaceArea(int[][] grid) {
        int surface = 0;
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid.length;j++){ 
                if(grid[i][j]!=0){
                    surface+=grid[i][j]*6-(grid[i][j]-1)*2;
                }else{
                    continue;
                }
                
                if(i-1>=0){
                    if(grid[i-1][j]>grid[i][j]){
                        surface-=grid[i][j];
                    }else{
                        surface-=grid[i-1][j];
                    }
                }
                if(i<grid.length-1){
                    if(grid[i+1][j]>grid[i][j]){
                        surface-=grid[i][j];
                    }else{
                        surface-=grid[i+1][j];
                    }
                }
                if(j<grid.length-1){
                    if(grid[i][j+1]>grid[i][j]){
                        surface-=grid[i][j];
                    }else{
                        surface-=grid[i][j+1];
                    }
                }
                if(j-1>=0){
                    if(grid[i][j-1]>grid[i][j]){
                        surface-=grid[i][j];
                    }else{
                        surface-=grid[i][j-1];
                    }
                }
                //System.out.print(surface+",");
            }
            
        
        }
        return surface;
    }
}