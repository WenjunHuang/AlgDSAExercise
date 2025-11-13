/** 归并排序算法实现
  *
  * 归并排序是一种分治算法，它将数组分成两半，递归地对每一半进行排序， 然后将两个已排序的部分合并在一起。
  *
  * 时间复杂度: O(n log n) 空间复杂度: O(n) 稳定性: 稳定
  */
package sort

// 导入必要的类型类和反射支持
import scala.math.Ordered.orderingToOrdered
import scala.reflect.ClassTag
import scala.util.boundary

object MergeSort {

  /** 归并排序主函数
    * @param arr
    *   待排序数组
    * @tparam T
    *   数组元素类型（需支持比较操作）
    */
  def mergeSort[T: ClassTag](arr: Array[T])(using ordering: Ordering[T]): Unit =
    // 空数组或单元素数组无需排序
    boundary:
      if (arr.length <= 1) boundary.break()

      /** 递归实现归并排序
        * @param arr
        *   待排序数组
        * @param left
        *   左边界索引
        * @param right
        *   右边界索引
        */
      def mergeSortImpl(arr: Array[T], left: Int, right: Int): Unit =
        // 递归终止条件：当子数组只有一个元素时停止分割
        if (left < right) {
          // 计算中间位置，避免整数溢出
          val middle = left + (right - left) / 2

          // 递归排序左半部分
          mergeSortImpl(arr, left, middle)

          // 递归排序右半部分
          mergeSortImpl(arr, middle + 1, right)

          // 合并两个已排序的部分
          merge(arr, left, middle, right)
        }

      // 调用递归实现，初始范围为整个数组
      mergeSortImpl(arr, 0, arr.length - 1)

  /** 合并两个已排序的子数组 [left, middle] 和 [middle+1, right]
    * @param arr
    *   原始数组
    * @param left
    *   左边界索引
    * @param middle
    *   中间索引
    * @param right
    *   右边界索引
    */
  def merge[T: ClassTag](arr: Array[T], left: Int, middle: Int, right: Int)(using Ordering[T]): Array[T] = {
    // 创建辅助数组存储合并结果
    val help       = Array.ofDim[T](right - left + 1)
    var helpIndex  = 0          // 辅助数组的索引
    var leftIndex  = left       // 左子数组的索引
    var rightIndex = middle + 1 // 右子数组的索引

    // 比较两个子数组的元素，将较小的元素放入辅助数组
    while (leftIndex <= middle && rightIndex <= right) {
      if (arr(leftIndex) <= arr(rightIndex)) {
        help(helpIndex) = arr(leftIndex)
        leftIndex += 1
      } else {
        help(helpIndex) = arr(rightIndex)
        rightIndex += 1
      }
      helpIndex += 1
    }

    // 将左子数组剩余元素复制到辅助数组
    while (leftIndex <= middle) {
      help(helpIndex) = arr(leftIndex)
      leftIndex += 1
      helpIndex += 1
    }

    // 将右子数组剩余元素复制到辅助数组
    while (rightIndex <= right) {
      help(helpIndex) = arr(rightIndex)
      rightIndex += 1
      helpIndex += 1
    }

    // 将合并后的结果复制回原数组
    Array.copy(help, 0, arr, left, help.length)
    arr
  }

  /** 验证归并排序实现的正确性 生成一个大数组进行测试，并验证排序结果是否正确
    */
  @main
  def verify(): Unit = {
    // 生成一个包含100万个随机整数的数组用于测试
    val randomArray = Array.fill(1000000)((Math.random() * 100000).toInt)

    // 执行归并排序
    mergeSort(randomArray)

    // 验证排序结果的正确性
    for (index <- 1 until randomArray.length)
      // 检查每个元素是否不小于前一个元素
      if (randomArray(index - 1) > randomArray(index)) {
        throw new Exception(s"排序错误：位置 ${index} 的元素小于前一个元素")
      }

    println("归并排序验证通过！")
  }
}
