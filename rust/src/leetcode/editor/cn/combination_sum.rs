struct Solution;

//leetcode submit region begin(Prohibit modification and deletion)
impl Solution {
    pub fn combination_sum(mut candidates: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        let mut res = vec![];
        candidates.sort();
        let mut track = vec![];
        Self::backtrack(&candidates, 0, target, &mut track, 0, &mut res);

        res
    }

    fn backtrack(candidates: &Vec<i32>, start: usize, target: i32, track: &mut Vec<i32>, trackNum: i32, res: &mut Vec<Vec<i32>>) {
        if target == trackNum {
            res.push(track.clone());
            return;
        }

        if trackNum > target {
            return;
        }

        for i in start..candidates.len() {
            track.push(candidates[i]);
            Self::backtrack(candidates, i, target, track, trackNum + candidates[i], res);
            track.pop();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

