package leetcode.editor.cn

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ValidateBinarySearchTreeTest : FunSpec({
    val solution = ValidateBinarySearchTree.Solution()

    test("Should return true for valid binary search tree") {
        val root = TreeNode(2)
        root.left = TreeNode(1)
        root.right = TreeNode(3)
        solution.isValidBST(root) shouldBe true
    }

    test("Should return false for invalid binary search tree") {
        val root = TreeNode(5)
        root.left = TreeNode(1)
        root.right = TreeNode(4)
        root.right?.left = TreeNode(3)
        root.right?.right = TreeNode(6)
        solution.isValidBST(root) shouldBe false
    }

    test("Should return true for single node tree") {
        val root = TreeNode(1)
        solution.isValidBST(root) shouldBe true
    }

    test("Should return true for empty tree") {
        solution.isValidBST(null) shouldBe true
    }
})
