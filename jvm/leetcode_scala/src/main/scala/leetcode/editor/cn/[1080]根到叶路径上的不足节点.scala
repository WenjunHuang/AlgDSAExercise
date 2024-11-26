package leetcode.editor.cn

object InsufficientNodesInRootToLeafPaths{
import scala.util.control.TailCalls.*
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
 *   var value: Int = _value
 *   var left: TreeNode = _left
 *   var right: TreeNode = _right
 * }
 */
object Solution {
    def sufficientSubset(root: TreeNode, limit: Int): TreeNode = {
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)

import scala.util.control.TailCalls.*

/** 栈安全的State Monad。可用于leetcode的用fp方式来解决问题。
  */
case class State[S, A](run: S => TailRec[(S, A)]){
  def evalState(s: S): A = run(s).result._2
  def execState(s: S): S = run(s).result._1
  def map[B](ab: A => B): State[S, B] =
    flatMap(a => State(s => done((s, ab(a)))))

  def flatMap[B](afb: A => State[S, B]): State[S, B] =
    State(s =>
      tailcall(
        run(s).flatMap { case (s, a) => tailcall(afb(a).run(s)) }
      )
    )
}
object StateOps{
  def point[S, A](a: A): State[S, A] = State(s => done((s, a)))

  def get[S]: State[S, S] = State(s => done((s, s)))

  def put[S](s: S): State[S, Unit] = State(_ => done((s, ())))

  def modify[S](ss: S => S): State[S, Unit] =
    for { s <- get[S]; _ <- put(ss(s)) } yield ()
}

}