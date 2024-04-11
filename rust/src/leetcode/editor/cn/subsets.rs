struct Solution;
//leetcode submit region begin(Prohibit modification and deletion)
impl Solution {
    pub fn subsets(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut res = vec![];
        let mut track = vec![];
        Self::backtrack_num_perspective(&nums,0,&mut res,&mut track);
        res
    }

    fn backtrack_num_perspective(nums: &Vec<i32>,start:usize,res:&mut Vec<Vec<i32>>,track:&mut Vec<i32>) {
        if start == nums.len() {
            res.push(track.clone());
            return;
        }

        Self::backtrack_num_perspective(nums,start + 1,res,track);

        track.push(nums[start]);
        Self::backtrack_num_perspective(nums,start + 1,res,track);
        track.pop();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

