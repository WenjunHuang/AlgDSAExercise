package scala.sort

import org.scalacheck.{ Arbitrary, Gen }

object TestDataGenerator {
  // 定义数组生成器
  val intArrayGen: Gen[Array[Int]] = Gen.containerOf[Array, Int](Arbitrary.arbitrary[Int])

  // 定义特定大小的数组生成器
  val largeArrayGen: Gen[Array[Int]] = Gen.containerOfN[Array, Int](1000000, Arbitrary.arbitrary[Int])

  // 定义有序数组生成器
  val sortedArrayGen: Gen[Array[Int]] = for {
    start <- Arbitrary.arbitrary[Int]
    size  <- Gen.choose(1, 1000000)
    step  <- Gen.choose(1, 10)
  } yield (start until (start + size * step) by step).toArray

  // 定义重复元素数组生成器
  val duplicateArrayGen: Gen[Array[Int]] = for {
    value <- Arbitrary.arbitrary[Int]
    size  <- Gen.choose(1, 1000000)
  } yield Array.fill(size)(value)

  // 定义部分有序数组生成器
  // 生成一个数组，其中包含多个有序子序列，但整体不是完全有序的
  val partiallySortedArrayGen: Gen[Array[Int]] = for {
    size <- Gen.choose(10, 1000000)
    // 确定有序段的数量（至少2个，最多size/5个）
    segmentCount <- Gen.choose(2, Math.max(2, size / 5))
  } yield {
    val result = new Array[Int](size)
    // 计算每个段的近似大小
    val baseSegmentSize = size / segmentCount
    val remainder = size % segmentCount
    
    var currentIndex = 0
    for (segment <- 0 until segmentCount) {
      // 计算当前段的实际大小
      val segmentSize = baseSegmentSize + (if (segment < remainder) 1 else 0)
      val segmentEnd = currentIndex + segmentSize
      
      // 填充当前段
      if (segment % 2 == 0) {
        // 偶数段：填充有序数据
        val startValue = scala.util.Random.nextInt(10000)
        for (i <- currentIndex until segmentEnd) {
          result(i) = startValue + (i - currentIndex)
        }
      } else {
        // 奇数段：填充随机数据
        for (i <- currentIndex until segmentEnd) {
          result(i) = scala.util.Random.nextInt(10000)
        }
      }
      
      currentIndex = segmentEnd
    }
    
    result
  }

  // 定义包含极值的数组生成器
  val extremeValueArrayGen: Gen[Array[Int]] = Gen.containerOf[Array, Int](
    Gen.oneOf(Int.MinValue, Int.MaxValue, 0, -1, 1, Int.MinValue + 1, Int.MaxValue - 1)
  )
}
