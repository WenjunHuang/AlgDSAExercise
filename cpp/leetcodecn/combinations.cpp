#include <vector>
using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> res;
        vector<int> track;
        track.reserve(k);
        backtrack(n,k,res,track,0);
        return res;
    }
private:
    void backtrack(int n,int k,vector<vector<int>>& result,vector<int>& track,int start) {
        if (track.size() == k){
            // 遍历到第k层，将结果添加
            result.push_back(track);
            return;
        }

        // 回溯算法基本框架
        for (int i = start;i< n;i++){
            // 选择策略
            track.push_back(i+1);

            backtrack(n,k,result,track,i+1);

            // 取消选择
            track.pop_back();
        }
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv,char *argc[]){
}