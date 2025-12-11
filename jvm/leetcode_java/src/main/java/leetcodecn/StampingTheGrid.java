package leetcodecn;

// [2200]用邮票贴满网格图
class StampingTheGrid {


    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class Solution {
        public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
            int n = grid.length;
            int m = grid[0].length;

            int[][] sum = new int[n + 1][m + 1];
            for (var i = 0; i < n; i++) {
                System.arraycopy(grid[i], 0, sum[i + 1], 1, m);
            }
            build(sum);


            int[][] diff = new int[n + 2][m + 2];
            for (int a = 1, c = a + stampHeight - 1; c <= n; a++, c++) {
                for (int b = 1, d = b + stampWidth - 1; d <= m; b++, d++) {
                    if (sumRegion(sum,a,b,c,d) == 0){
                        add(diff,a,b,c,d);
                    }
                }
            }
            build(diff);

            for (var i =0;i<n;i++){
                for (var j = 0;j<m;j++) {
                    if (grid[i][j]==0 && diff[i+1][j+1] == 0){
                        return false;
                    }
                }
            }
            return true;
        }

        int sumRegion(int[][] grid, int a, int b, int c, int d) {
            return grid[c][d] - grid[c][b - 1] - grid[a - 1][d] + grid[a - 1][b - 1];
        }

        void add(int[][] grid,int a,int b,int c,int d){
            grid[a][b] += 1;
            grid[c+1][b] -= 1;
            grid[a][d+1] -= 1;
            grid[c+1][d+1] += 1;
        }

        void build(int[][] grid) {
            for (var i = 1; i < grid.length; i++) {
                for (var j = 1; j < grid[0].length; j++) {
                    grid[i][j] += grid[i][j - 1] + grid[i - 1][j] - grid[i - 1][j - 1];
                }
            }
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
