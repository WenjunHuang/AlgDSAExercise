//leetcode submit region begin(Prohibit modification and deletion)
function palindrome(s: string, left: number, right: number): string {
    if (left < 0 || right >= s.length || s[left] != s[right]) return s.substring(left + 1, right)
    else return palindrome(s, left - 1, right + 1)
}

function longestPalindrome(s: string): string {
    let res = ""
    for (let i = 0; i < s.length; i++) {
        const l1 = palindrome(s, i, i)
        const l2 = palindrome(s, i, i + 1)
        if (l1.length > res.length) res = l1;
        if (l2.length > res.length) res = l2;
    }
    return res
}

//leetcode submit region end(Prohibit modification and deletion)
