/*
 * @lc app=leetcode.cn id=46 lang=typescript
 *
 * [46] 全排列
 */

// @lc code=start

let result: number[][] = []

function swap(nums:number[],i:number,j:number){
    const temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}

function backtrack(nums:number[],start:number) {
    if (start == nums.length) {
        const list:number[] = [];
        for(const num of nums){
            list.push(num)
        }
        result.push(list)
    }

    for (let i = start;i<nums.length;i++){
        swap(nums,start,i)
        backtrack(nums,start + 1)
        swap(nums,start,i)
    }
}

function permute(nums: number[]): number[][] {
    result = []
    backtrack(nums,0)
    return result;
};
// @lc code=end

