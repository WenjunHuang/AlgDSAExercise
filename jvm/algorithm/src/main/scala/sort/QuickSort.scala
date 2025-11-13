package sort

import scala.reflect.ClassTag
import scala.math.Ordering.Implicits.*

object QuickSort {
  def quickSort[T: ClassTag](arr: Array[T])(using Ordering[T]): Unit = {
    if (arr.length > 1) {
      def impl(arr: Array[T], left: Int, right: Int): Unit = {
        if (left < arr.length && right < arr.length && right - left > 1) {
          val p = pivot(arr, left, right)
          impl(arr, left, p)
          impl(arr, p + 1, right)
          MergeSort.merge(arr,left,p,right)
        }
      }

      impl(arr, 0, arr.length - 1)
    }
  }

  private def pivot[T: ClassTag](arr: Array[T], left: Int, right: Int)(using Ordering[T]): Int =
    if (right - left > 0) {
      var index = left
      for { i <- (left + 1).to(right) } yield
        if (arr(i) < arr(left)) {
          swap(arr, index, i)
          index += 1
        }
      index
    } else {
      0
    }

  private def swap[T](arr: Array[T], src: Int, dest: Int): Unit = {
    val t = arr(src)
    arr(src) = arr(dest)
    arr(dest) = t
  }

  @main
  def verifyQuickSort():Unit = {
    // 生成一个包含100万个随机整数的数组用于测试
    val randomArray = Array.fill(1000000)((Math.random() * 1000000).toInt)

    // 执行归并排序
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
