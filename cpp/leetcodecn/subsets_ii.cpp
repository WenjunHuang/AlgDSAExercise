#include <vector>
#include <algorithm>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int> &nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> res;
        res.reserve(1 << nums.size());

        vector<int> track;
        track.reserve(nums.size());
        backtrack(nums,0,track,res);

        return res;
    }

    void backtrack(const vector<int>& nums,int start,vector<int>&track,vector<vector<int>>& res){
        res.push_back(track);

        for (int i = start;i < nums.size();i++){
            //剪枝
            if (i > start && nums[i] == nums[i-1])
                continue;

            // 选择策略
            track.push_back(nums[i]);
            backtrack(nums,i+1,track,res);
            track.pop_back();
        }
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}