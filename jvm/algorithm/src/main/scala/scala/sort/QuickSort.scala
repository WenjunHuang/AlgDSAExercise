package scala.sort

import org.scalatest.concurrent.TimeLimits
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

import scala.math.Ordering.Implicits.*
import scala.reflect.ClassTag
import scala.sort.TestDataGenerator.{ duplicateArrayGen, extremeValueArrayGen, intArrayGen, largeArrayGen, partiallySortedArrayGen, sortedArrayGen }
import QuickSort.*

class QuickSort extends AnyFunSuite with TimeLimits with ScalaCheckPropertyChecks {

  test("quick sort with random arrays") {
    forAll(intArrayGen) { arr =>
      val original = arr.clone()
      quickSortWithNetherlandsPartition(arr)
      arr shouldBe sorted
      arr should contain theSameElementsAs original
    }
  }

  test("quick sort with large arrays") {
    forAll(largeArrayGen) { arr =>
      quickSortWithNetherlandsPartition(arr)
      arr shouldBe sorted
    }
  }

  test("quick sort with sorted arrays") {
    forAll(sortedArrayGen) { arr =>
      val original = arr.clone()
      quickSortWithNetherlandsPartition(arr)
      arr shouldBe sorted
    }
  }

  test("quick sort with duplicate elements") {
    forAll(duplicateArrayGen) { arr =>
      quickSortWithNetherlandsPartition(arr)
      arr shouldBe sorted
    }
  }

  test("quick sort with partially sorted arrays") {
    forAll(partiallySortedArrayGen) { arr =>
      quickSortWithNetherlandsPartition(arr)
      arr shouldBe sorted
    }
  }

  test("quick sort with extreme values") {
    forAll(extremeValueArrayGen) { arr =>
      quickSortWithNetherlandsPartition(arr)
      arr shouldBe sorted
    }
  }

  test("quick sort preserves empty array") {
    val arr = Array.empty[Int]
    quickSortWithNetherlandsPartition(arr)
    arr shouldBe empty
  }

  test("quick sort preserves single element array") {
    forAll { (n: Int) =>
      val arr = Array(n)
      quickSortWithNetherlandsPartition(arr)
      arr should contain theSameElementsAs Array(n)
    }
  }
}

// 保留原有的QuickSort对象不变
object QuickSort {
  def quickSortWithClassicPartition[T: ClassTag](arr: Array[T])(using Ordering[T]): Unit =
    if (arr.length > 1) {
      def impl(arr: Array[T], left: Int, right: Int): Unit =
        if (left < right) {
          val m = partition(arr, left, right)
          impl(arr, left, m - 1)
          impl(arr, m + 1, right)
        }

      impl(arr, 0, arr.length - 1)
    }

  def quickSortWithNetherlandsPartition[T: ClassTag](arr: Array[T])(using Ordering[T]): Unit =
    if (arr.length > 1) {
      def impl(arr: Array[T], left: Int, right: Int): Unit =
        if (left < right) {
          val (l, r) = netherlandsPartition(arr, left, right)
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
    swap(arr, pivot, lb)
    (lb, rb - 1)
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
}
