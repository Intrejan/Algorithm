package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SetSolution {

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
        if(r<0||c<0||r>=R||c>=C || grid[r][c]=='0'){
            return;
        }
        grid[r][c]='0';
        dfs(grid,r-1,c);
        dfs(grid,r+1,c);
        dfs(grid,r,c+1);
        dfs(grid,r,c-1);
    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length==0){
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            int L = interval[0];
            int R = interval[1];
            if (result.size() == 0 || result.get(result.size() - 1)[1] < L) {
                result.add(new int[]{L, R});
            } else {
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], R);
            }
        }
        return result.toArray(new int[result.size()][]);
    }


    public static void main(String[] args){
        SetSolution setSolution = new SetSolution();

    }
}
