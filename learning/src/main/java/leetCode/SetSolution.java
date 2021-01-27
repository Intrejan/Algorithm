package leetCode;

import java.lang.reflect.Array;
import java.util.*;

public class SetSolution {
    /**
     * 岛屿数量
     * @param grid 矩阵
     * @return 岛屿个数
     */
    public int numIslands(char[][] grid) {
        if(grid ==null ||grid.length == 0 ){
            return 0;
        }
        int R = grid.length;
        int C = grid[0].length;
        int result = 0;
        for(int r=0;r<R;r++){
            for(int c = 0;c<C;c++){
                if(grid[r][c]=='1'){
                    ++result;
                    dfs(grid,r,c);
                }
            }
        }
        return result;
    }

    /**
     * 深度优先遍历
     * @param grid 矩阵
     * @param r row
     * @param c column
     */
    private void dfs(char[][] grid, int r, int c) {
        int R = grid.length;
        int C = grid[0].length;
        //递归终止条件
        if(r<0||c<0||r>=R||c>=C || grid[r][c]=='0'){
            return;
        }
        grid[r][c]='0';
        dfs(grid,r-1,c);
        dfs(grid,r+1,c);
        dfs(grid,r,c+1);
        dfs(grid,r,c-1);
    }

    /**
     * 等式方程的可满足性
     * @param equations 方程组
     * @return 是否满足
     */
    public boolean equationsPossible(String[] equations) {
       int[] parents = new int[26];
        for (int i = 0; i < 26; i++) {
            parents[i] = i;
        }
       for(String s:equations){
           if(s.charAt(1)=='='){
               int index1 = s.charAt(0)-'a';
               int index2 = s.charAt(3)-'a';
               union(parents,index1,index2);
           }
       }
       for(String s:equations){
           if(s.charAt(1)=='!'){
               int index1 = s.charAt(0)-'a';
               int index2 = s.charAt(3)-'a';
               if(find(parents,index1) == find(parents,index2)){
                   return false;
               }
           }
       }
       return true;
    }

    private void union(int[] parents, int index1, int index2) {
        parents[find(parents,index1)] = find(parents,index2);
    }

    private int find(int[] parents, int index) {
        while (parents[index]!=index){
            parents[index] = parents[parents[index]];
            index = parents[index];
        }
        return index;
    }

    public static void main(String[] args){
        SetSolution setSolution = new SetSolution();

    }
}
