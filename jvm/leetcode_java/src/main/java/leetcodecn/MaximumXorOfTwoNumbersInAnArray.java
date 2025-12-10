package leetcodecn;

import java.util.Arrays;

// [421]数组中两个数的最大异或值
class MaximumXorOfTwoNumbersInAnArray {


    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class Solution {
        public int findMaximumXOR(int[] nums) {
            build(nums);
            int ans = 0;
            for (int num:nums) {
                ans = Math.max(ans,maxXor(num));
            }
            clear();
            return ans;
        }

        public static int MAX = 3 * 1000000 + 1;
        public static int[][] tree = new int[MAX][2];
        public static int cnt;
        public static int left;

        public static void build(int[] nums) {
            cnt = 1;
            var max = 0;
            for (var num : nums) {
                max = Math.max(num, max);
            }
            left = 31 - Integer.numberOfLeadingZeros(max);
            for (var num:nums){
                insert(num);
            }
        }

        public static void insert(int num) {
            int cur = 1;
            for (int i = left, path; i >= 0; i--) {
                path = (num >> i) & 1;
                if (tree[cur][path] == 0) {
                    tree[cur][path] = ++cnt;
                }
                cur = tree[cur][path];
            }
        }

        public static int maxXor(int num) {
            int ans = 0;
            int cur = 1;
            for (int i=left,status,want;i >= 0;i--) {
                status = (num >> i) & 1;
                want = status ^ 1;
                if (tree[cur][want] == 0) {
                    want ^= 1;
                }

                ans |= (status ^ want) << i;
                cur = tree[cur][want];
            }
            return ans;
        }

        public static void clear() {
            for (var i = 0; i <= cnt; i++) {
                Arrays.fill(tree[i], 0);
            }
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)

    public static void main(String[] args) {
        // add your test code
    }
}
