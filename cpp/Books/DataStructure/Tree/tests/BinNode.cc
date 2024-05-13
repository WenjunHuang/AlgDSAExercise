//
// Created by rick on 2024/4/25.
//

#include <gtest/gtest.h>
#include <type_traits>
#include "BinNode.h"

struct Dump {
};

TEST(BinNodeTest, BinNodeBasicProperties) {
    ASSERT_FALSE(std::is_default_constructible_v<BinNode<int>>);
    ASSERT_TRUE(std::is_move_constructible_v<BinNode<int>>);
    ASSERT_TRUE(std::is_move_assignable_v<BinNode<int>>);
    ASSERT_TRUE(std::three_way_comparable<BinNode<int>>);
    ASSERT_TRUE(std::equality_comparable<BinNode<int>>);
    ASSERT_TRUE(!std::three_way_comparable<BinNode<Dump>>);
    ASSERT_TRUE(!std::equality_comparable<BinNode<Dump>>);
}

TEST(BinNodeTest, ThreeWayCompare) {
    BinNode<int> node1(1);
    BinNode<int> node2(2);
    BinNode<int> node3(1);
    ASSERT_EQ(node1 <=> node2, std::weak_ordering::less);
    ASSERT_EQ(node1 <=> node3, std::weak_ordering::equivalent);
}

TEST(BinNodeTest, NodeInitialization) {
    BinNode<int> node(0);
    EXPECT_EQ(node.data, 0);
}


TEST(BinNodeTest, SuccessorOfSingleNode) {
    BinNode<int> node(5);
    auto successor = node.succ();
    ASSERT_TRUE(successor == nullptr);
}

TEST(BinNodeTest, SuccessorOfNodeWithRightChild) {
    BinNode<int> node(5);
    node.insertAsRC(7);
    auto successor = node.succ();
    ASSERT_TRUE(successor != nullptr);
    EXPECT_EQ(successor->data, 7);
}

TEST(BinNodeTest, SuccessorOfRightmostNodeInLeftSubtree) {
    BinNode<int> node(5);
    auto leftChild = node.insertAsLC(3);
    leftChild->insertAsRC(4);
    auto successor = leftChild->succ();
    ASSERT_TRUE(successor != nullptr);
    EXPECT_EQ(successor->data, 4);
}

TEST(BinNodeTest, SuccessorOfRightmostNodeInTree) {
    BinNode<int> node(5);
    auto rightChild = node.insertAsRC(7);
    rightChild->insertAsRC(9);
    auto successor = rightChild->rc->succ();
    ASSERT_TRUE(successor == nullptr);
}

TEST(BinNodeTest, InOrderTraversal) {
    BinNode<int> node(5);
    auto leftChild = node.insertAsLC(3);
    leftChild->insertAsRC(4);
    auto rightChild = node.insertAsRC(7);
    rightChild->insertAsRC(9);

    std::vector<int> result;
    node.travIn([&result](int &value) { result.push_back(value); });

    std::vector<int> expected = {3, 4, 5, 7, 9};
    EXPECT_EQ(result, expected);
}

TEST(BinNodeTest, PreOrderTraversal) {
    BinNode<int> node(5);
    auto leftChild = node.insertAsLC(3);
    leftChild->insertAsRC(4);
    auto rightChild = node.insertAsRC(7);
    rightChild->insertAsRC(9);

    std::vector<int> result;
    node.travPre([&result](int &value) { result.push_back(value); });

    std::vector<int> expected = {5, 3, 4, 7, 9};
    EXPECT_EQ(result, expected);
}

TEST(BinNodeTest, PostOrderTraversal) {
    BinNode<int> node(5);
    auto leftChild = node.insertAsLC(3);
    leftChild->insertAsRC(4);
    auto rightChild = node.insertAsRC(7);
    rightChild->insertAsRC(9);

    std::vector<int> result;
    node.travPost([&result](int &value) { result.push_back(value); });

    std::vector<int> expected = {4, 3, 9, 7, 5};
    EXPECT_EQ(result, expected);
}

TEST(BinNodeTest, Zig) {
    // Create a binary node
    BinNode<int> node(5);

//         5
//        / \
//       3   7
//      / \ / \
//     2  4 6  8
//    / \
//    1  0
    // Insert elements
    auto leftChild = node.insertAsLC(3);
    leftChild->insertAsLC(2);
    leftChild->insertAsRC(4);

    auto rightChild = node.insertAsRC(7);
    rightChild->insertAsLC(6);
    rightChild->insertAsRC(8);

    auto leftLeftChild = leftChild->lc;
    leftLeftChild->insertAsLC(1);
    leftLeftChild->insertAsRC(0);

    // Test zig
//     3
//    / \
//   2   5
//  / \  / \
//  1  0 4  7
//         / \
//        6   8
    auto zigNode = node.zig();
    ASSERT_EQ(zigNode->data, 3);
    ASSERT_EQ(zigNode->lc->data, 2);
    ASSERT_EQ(zigNode->rc->data, 5);
    ASSERT_EQ(zigNode->lc->lc->data, 1);
    ASSERT_EQ(zigNode->lc->rc->data, 0);
    ASSERT_EQ(zigNode->rc->lc->data, 4);
    ASSERT_EQ(zigNode->rc->rc->data, 7);
    ASSERT_EQ(zigNode->rc->rc->lc->data, 6);
    ASSERT_EQ(zigNode->rc->rc->rc->data, 8);
}

TEST(BinNodeTest, Zag) {
    // Create a binary node
    BinNode<int> node(5);
//         5
//        / \
//       3   7
//      / \ / \
//     2  4 6  8
//            / \
//           9  10
    // Insert elements
    auto leftChild = node.insertAsLC(3);
    leftChild->insertAsLC(2);
    leftChild->insertAsRC(4);

    auto rightChild = node.insertAsRC(7);
    rightChild->insertAsLC(6);
    rightChild->insertAsRC(8);

    auto rightRightChild = rightChild->rc;
    rightRightChild->insertAsLC(9);
    rightRightChild->insertAsRC(10);

    // Test zag
//         7
//        / \
//       5   8
//      / \ / \
//     3  6 9 10
//    / \
//   2  4
    auto zagNode = node.zag();
    ASSERT_EQ(zagNode->data, 7);
    ASSERT_EQ(zagNode->lc->data, 5);
    ASSERT_EQ(zagNode->lc->lc->data, 3);
    ASSERT_EQ(zagNode->lc->rc->data, 6);
    ASSERT_EQ(zagNode->lc->lc->lc->data, 2);
    ASSERT_EQ(zagNode->lc->lc->rc->data, 4);
    ASSERT_EQ(zagNode->rc->data, 8);
    ASSERT_EQ(zagNode->rc->lc->data, 9);
    ASSERT_EQ(zagNode->rc->rc->data, 10);

}