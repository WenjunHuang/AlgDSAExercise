package scala.sort

import org.scalatest.enablers.Sortable
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.sorted
import org.scalatest.matchers.should.Matchers.shouldBe
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

import scala.math.Ordered.orderingToOrdered
import scala.sort.TestDataGenerator.*
import scala.util.boundary

class HeapSort extends AnyFunSuite with ScalaCheckPropertyChecks {
  import HeapSort.*

  test("heap sort with random arrays") {
    forAll(intArrayGen) { arr =>
      heapSort(arr)
      arr shouldBe sorted
    }
  }
  test("heap sort with random arrays with reverse") {
    forAll(intArrayGen) { arr =>
      given order: Ordering[Int] = Ordering.Int.reverse
      heapSort(arr)
      arr shouldBe sorted
    }
  }
  test("heap sort with large arrays") {
    forAll(largeArrayGen) { arr =>
      heapSort(arr)
      arr shouldBe sorted
    }
  }

  /** Helper function to check if an array represents a valid max-heap
    */
  private def isMaxHeap[T](arr: Array[T])(implicit ord: Ordering[T]): Boolean =
    boundary {
      for (i <- 0 until arr.length / 2) {
        val left  = 2 * i + 1
        val right = 2 * i + 2

        if (left < arr.length && ord.gt(arr(left), arr(i))) boundary.break(false)
        if (right < arr.length && ord.gt(arr(right), arr(i))) boundary.break(false)
      }
      true
    }
}

object HeapSort {

  def heapSort[T: Ordering](arr: Array[T]): Unit = {
    // 初始化堆
    for { i <- arr.indices } yield heapInsert(arr, i)
//    for { i <- (arr.length - 1).to(0, -1) } yield heapify(arr, i, arr.length - 1)

    for { i <- (arr.length - 1).to(0, -1) } yield {
      swap(arr, 0, i)
      heapify(arr, 0, i - 1)
    }
  }

  def swap[T](arr: Array[T], one: Int, theOther: Int): Unit = {
    val t = arr(theOther)
    arr(theOther) = arr(one)
    arr(one) = t
  }

  def heapInsert[T](arr: Array[T], to: Int)(using Ordering[T]): Unit =
    boundary {
      var cur = to
      while (cur > 0) {
        val parent = (cur - 1) / 2
        if (arr(parent) < arr(cur)) {
          swap(arr, cur, parent)
          cur = parent
        } else {
          boundary.break()
        }
      }
    }

  def heapify[T](arr: Array[T], from: Int, to: Int)(using Ordering[T]): Unit =
    boundary {
      var cur = from
      while (cur <= to) {
        val left  = cur * 2 + 1
        val right = cur * 2 + 2

        // Find the largest among root, left child and right child
        var largest = cur

        if (left <= to && arr(left) > arr(largest)) {
          largest = left
        }

        if (right <= to && arr(right) > arr(largest)) {
          largest = right
        }

        if (largest != cur) {
          swap(arr, cur, largest)
          cur = largest
        } else {
          boundary.break()
        }
      }
    }

  /** Builds a max heap from an unordered array
    */
  def buildMaxHeap[T](arr: Array[T])(using ordering: Ordering[T]): Unit = {
    val n = arr.length
    // Start from the last non-leaf node and heapify each node
    for (i <- (n - 1) to 0 by -1)
      heapify(arr, i, n - 1)
  }

}
