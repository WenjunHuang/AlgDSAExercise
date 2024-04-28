mod leetcode;
use self::leetcode::editor::cn::MinimumWindowSubstring::Solution;

fn main() {
    let s = "ADOBECODEBANC".to_string();
    let t = "ABC".to_string();
    let res = Solution::min_window(s, t);
    println!("{}", res);
}
