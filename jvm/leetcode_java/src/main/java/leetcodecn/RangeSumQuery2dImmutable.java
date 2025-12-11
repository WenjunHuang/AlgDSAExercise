package leetcodecn;

// [304]二维区域和检索 - 矩阵不可变
class RangeSumQuery2dImmutable {


    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class NumMatrix {
        private int[][] sum;

        public NumMatrix(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            sum = new int[row + 1][col + 1];

            for (int a = 1, i = 0; i < row; i++, a++) {
                for (int b = 1, j = 0; j < col; j++, b++) {
                    sum[a][b] = matrix[i][j];
                }
            }

            for (int a = 1; a < row + 1; a++) {
                for (int b = 1; b < col + 1; b++) {
                    sum[a][b] += sum[a - 1][b] + sum[a][b - 1] - sum[a - 1][b - 1];
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            row2++;
            col2++;

            return sum[row2][col2] - sum[row2][col1] - sum[row1][col2] + sum[row1][col1];
        }
    }

    /**
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * int param_1 = obj.sumRegion(row1,col1,row2,col2);
     */
//IMPORTANT!! Submit Code Region End(Do not remove this line)
    public static void main(String[] args) {
        // add your test code
    }
}
