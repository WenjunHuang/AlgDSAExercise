package leetcodecn;

// [1239]最大的以 1 为边界的正方形
class Largest1BorderedSquare {


    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class Solution {
        public int largest1BorderedSquare(int[][] grid) {
            int row = grid.length;
            int col = grid[0].length;
            build(grid, row, col);
            if (sum(grid, 0, 0, row - 1, col - 1) == 0) return 0;

            int ans = 1;
            for (int a = 0; a < row; a++) {
                for (int b = 0; b < col; b++) {
                    for (int c = a + ans, d = b + ans, k = ans + 1; c < row && d < col; c++, d++, k++) {
                        if (sum(grid, a, b, c, d) - sum(grid, a + 1, b + 1, c - 1, d - 1) == (k - 1) * 4) {
                            ans = k;
                        }
                    }
                }
            }

            return ans * ans;
        }

        void build(int[][] grid, int row, int col) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    grid[i][j] += get(grid, i, j - 1) + get(grid, i - 1, j) - get(grid, i - 1, j - 1);
                }
            }
        }

        int get(int[][] grid, int row, int col) {
            return (row < 0 || col < 0) ? 0 : grid[row][col];
        }

        int sum(int[][] grid, int a, int b, int c, int d) {
            return get(grid, c, d) - get(grid, c, b - 1) - get(grid, a - 1, d) + get(grid, a - 1, b - 1);
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
