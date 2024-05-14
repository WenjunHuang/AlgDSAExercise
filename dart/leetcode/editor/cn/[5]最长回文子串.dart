//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  String longestPalindrome(String s) {
    String result = "";
    for (int i = 0; i < s.length; i++) {
      var s1 = _palindrome(s, i, i);
      var s2 = _palindrome(s, i, i + 1);
      if (s1.length > result.length) result = s1;
      if (s2.length > result.length) result = s2;
    }
    return result;
  }

  String _palindrome(String s, int left, int right) {
    if (left < 0 || right >= s.length || s[left] != s[right]) {
      return s.substring(left + 1, right);
    } else {
      return _palindrome(s, left - 1, right + 1);
    }
  }
}
//leetcode submit region end(Prohibit modification and deletion)

void main() {
  print("Hello");
}
