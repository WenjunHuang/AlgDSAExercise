//
// Created by rick on 2024/4/25.
//

#include <gtest/gtest.h>
#include "BinNode.h"

TEST(BinNodeTest, NodeInitialization) {
    BinNode<int> node(0);
    EXPECT_EQ(node.data, 0);
}

TEST(BinNodeTest, SuccessorOfSingleNode) {
    BinNode<int> node(5);
    auto successor = node.succ();
    EXPECT_FALSE(successor.has_value());
}

TEST(BinNodeTest, SuccessorOfNodeWithRightChild) {
    BinNode<int> node(5);
    node.insertAsRC(7);
    auto successor = node.succ();
    ASSERT_TRUE(successor.has_value());
    EXPECT_EQ(successor.value().get().data, 7);
}

TEST(BinNodeTest, SuccessorOfRightmostNodeInLeftSubtree) {
    BinNode<int> node(5);
    auto leftChild = node.insertAsLC(3);
    leftChild->insertAsRC(4);
    auto successor = leftChild->succ();
    ASSERT_TRUE(successor.has_value());
    EXPECT_EQ(successor.value().get().data, 4);
}

TEST(BinNodeTest, SuccessorOfRightmostNodeInTree) {
    BinNode<int> node(5);
    auto rightChild = node.insertAsRC(7);
    rightChild->insertAsRC(9);
    auto successor = rightChild->rc->succ();
    EXPECT_FALSE(successor.has_value());
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