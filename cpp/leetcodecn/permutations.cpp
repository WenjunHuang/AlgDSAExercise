#include <vector>
using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        res.clear();

        vector<int> track;
        track.reserve(nums.size());
        vector<bool> used(nums.size(),false);
        backtrack(nums,track,used);
        return res;
    }

    void backtrack(const vector<int>& nums,vector<int>& track,vector<bool>& used){
        if(track.size() == nums.size()){
            res.push_back(track);
            return;
        }
        for(int i = 0;i < nums.size();i++){
            if(used[i]){
                continue;
            }
            track.push_back(nums[i]);
            used[i] = true;
            backtrack(nums,track,used);
            track.pop_back();
            used[i] = false;
        }
    }
private:
    vector<vector<int>> res;
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv,char *argc[]){
}