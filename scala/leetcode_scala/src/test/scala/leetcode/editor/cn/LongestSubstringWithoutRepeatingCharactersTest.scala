package leetcode.editor.cn

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LongestSubstringWithoutRepeatingCharactersSpec extends AnyFlatSpec with Matchers {
  "lengthOfLongestSubstring" should "return the length of the longest substring without repeating characters" in {
    val s = "abcabcbb"
    val result = LongestSubstringWithoutRepeatingCharacters.Solution.lengthOfLongestSubstring(s)
    result should be(3)
  }

  it should "return 0 when the string is empty" in {
    val s = ""
    val result = LongestSubstringWithoutRepeatingCharacters.Solution.lengthOfLongestSubstring(s)
    result should be(0)
  }

  it should "return 1 when all characters in the string are the same" in {
    val s = "bbbbbb"
    val result = LongestSubstringWithoutRepeatingCharacters.Solution.lengthOfLongestSubstring(s)
    result should be(1)
  }

  it should "handle strings with one distinct character" in {
    val s = "bwwkew"
    val result = LongestSubstringWithoutRepeatingCharacters.Solution.lengthOfLongestSubstring(s)
    result should be(3)
  }
}
