package luogu;
//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
// add your imports here

import java.io.*;
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public class P3397 {

    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class Main {

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            var in = new StreamTokenizer(br);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
            int n, m;
            in.nextToken();
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            int[][] grid = new int[n+2][n+2];

            for (int i = 0; i < m; i++) {
                in.nextToken();
                int a = (int) in.nval;
                in.nextToken();
                int b = (int) in.nval;
                in.nextToken();
                int c = (int) in.nval;
                in.nextToken();
                int d = (int) in.nval;

                add(grid, a, b, c, d, 1);
            }

            build(grid, n);

            for (int i = 1; i <= n; i++) {
                out.print(grid[i][1]);
                for (int j = 2; j <= n; j++) {
                    out.print(" " + grid[i][j]);
                }
                out.println();
            }

            // add your code here
            out.flush();
            out.close();
            br.close();
        }

        static void build(int[][] grid, int n) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    grid[i][j] += grid[i][j - 1] + grid[i - 1][j] - grid[i - 1][j - 1];
                }
            }

        }


        static void add(int[][] grid, int a, int b, int c, int d, int val) {
            grid[a][b] += val;
            grid[c + 1][b] -= val;
            grid[a][d + 1] -= val;
            grid[c + 1][d + 1] += val;
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }
}
