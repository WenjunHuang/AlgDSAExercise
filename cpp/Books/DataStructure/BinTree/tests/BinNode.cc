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

TEST(BinNodeTest, ZigMethod) {
    // Create a small binary tree
    BinNode<int> root(2);
    BinNode<int> left(1);
    BinNode<int> leftLeft(4);
    BinNode<int> leftRight(5);
    BinNode<int> right(3);

    left.lc = &leftLeft;
    left.rc = &leftRight;
    root.lc = &left;
    root.rc = &right;

    // Call the zig method on the root
    auto newRoot = root.zig();

    // Check that the tree has been correctly restructured
    ASSERT_EQ(newRoot->data, 1);
    ASSERT_EQ(newRoot->rc->data, 2);
    ASSERT_EQ(newRoot->rc->rc->data, 3);
    ASSERT_EQ(newRoot->rc->lc->data, 5);
    ASSERT_EQ(newRoot->lc->data, 4);
    ASSERT_TRUE(!newRoot->lc->lc);
    ASSERT_TRUE(!newRoot->lc->rc);
}