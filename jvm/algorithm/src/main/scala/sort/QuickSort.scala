package sort

import scala.math.Ordering.Implicits.*
import scala.reflect.ClassTag
import org.scalatest.funsuite.AnyFunSuite
object QuickSort extends FunSuit{
  def quickSort[T: ClassTag](arr: Array[T])(using Ordering[T]): Unit =
    if (arr.length > 1) {
      def impl(arr: Array[T], left: Int, right: Int): Unit =
        if (left < right) {
          val (l,r) = netherlandsPartition(arr, left, right)
          impl(arr, left, l - 1)
          impl(arr, r + 1, right)
        }

      impl(arr, 0, arr.length - 1)
    }

  private def netherlandsPartition[T: ClassTag](arr: Array[T], left: Int, right: Int)(using Ordering[T]): (Int, Int) = {
    // 必须采用随机pivot的方法，否则会使得最坏情况（有序数组）时算法复杂度为O(n^2)
    val s = Math.floor(left + Math.random() * (right - left)).toInt
    swap(arr, left, s)
    
    val pivot = left
    var lb    = left
    var rb    = right + 1
    var p     = left + 1
    while (p < rb)
      if (arr(p) < arr(pivot)) {
        swap(arr, p, lb + 1)
        p += 1
        lb += 1
      } else if (arr(p) == arr(pivot)) {
        p += 1
      } else {
        swap(arr, p, rb - 1)
        rb -= 1
      }
    swap(arr, pivot, left)
    (left, right - 1)
  }

  private def partition[T: ClassTag](arr: Array[T], left: Int, right: Int)(using Ordering[T]): Int = {
    // 必须采用随机pivot的方法，否则会使得最坏情况（有序数组）时算法复杂度为O(n^2)
    val s = Math.floor(left + Math.random() * (right - left)).toInt
    swap(arr, left, s)

    var leftBound = left + 1
    val pivot     = left
    for { i <- (left + 1).to(right) } yield
      if (arr(i) < arr(pivot)) {
        swap(arr, leftBound, i)
        leftBound += 1
      }
    swap(arr, leftBound - 1, pivot)
    leftBound - 1
  }

  private def swap[T](arr: Array[T], src: Int, dest: Int): Unit =
    if (src != dest) {
      val t = arr(src)
      arr(src) = arr(dest)
      arr(dest) = t
    }

  
  def testNetherlandsPartition
  @main
  def verifyQuickSort(): Unit = {
    // 生成一个包含100万个随机整数的数组用于测试
    val test = List(Array.fill(1000000)((Math.random() * 10000).toInt), 0.to(1000000).toArray, Array.fill(1000000)(1))

    // 执行归并排序
    test.foreach { randomArray =>
      quickSort(randomArray)

      // 验证排序结果的正确性
      for (index <- 1 until randomArray.length)
        // 检查每个元素是否不小于前一个元素
        if (randomArray(index - 1) > randomArray(index)) {
          throw new Exception(s"排序错误：位置 ${index} 的元素小于前一个元素")
        }
      println("快速排序验证通过！")
    }

  }

}
