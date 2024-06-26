//
// Created by rick on 2024/5/10.
//
#include "BinTree.h"
#include <gtest/gtest.h>

TEST(BinTreeTest, InsertAsRoot) {
    // Create a binary tree
    auto tree = std::make_shared<BinTree<int>>();

    // Test insertAsRoot
    auto root = tree->insertAsRoot(1);
    ASSERT_EQ(root->data, 1);
    ASSERT_EQ(tree->size(), 1);
}

TEST(BinTreeTest, InsertAsLC) {
    // Create a binary tree
    auto tree = std::make_shared<BinTree<int>>();
    auto root = tree->insertAsRoot(1);

    // Test insertAsLC
    auto leftChild = tree->insertAsLC(root, 2);
    ASSERT_EQ(leftChild->data, 2);
    ASSERT_EQ(tree->size(), 2);
    ASSERT_EQ(root->lc, leftChild);
}

TEST(BinTreeTest, InsertAsRC) {
    // Create a binary tree
    auto tree = std::make_shared<BinTree<int>>();
    auto root = tree->insertAsRoot(1);

    // Test insertAsRC
    auto rightChild = tree->insertAsRC(root, 3);
    ASSERT_EQ(rightChild->data, 3);
    ASSERT_EQ(tree->size(), 2);
    ASSERT_EQ(root->rc, rightChild);
}


TEST(BinTreeTest, AttachAsLC) {
    // Create a binary tree
    auto tree = std::make_shared<BinTree<int>>();
    auto root = tree->insertAsRoot(1);

    // Create a second binary tree to attach
    auto treeToAttach = new BinTree<int>;
    treeToAttach->insertAsRoot(2);

    // Test attachAsLC
    tree->attachAsLC(root, treeToAttach);
    ASSERT_EQ(root->lc->data, 2);
    ASSERT_EQ(tree->size(), 2);
}

TEST(BinTreeTest, AttachAsRC) {
    // Create a binary tree
    auto tree = std::make_shared<BinTree<int>>();
    auto root = tree->insertAsRoot(1);

    // Create a second binary tree to attach
    auto treeToAttach = new BinTree<int>;
    treeToAttach->insertAsRoot(2);

    // Test attachAsRC
    tree->attachAsRC(root, treeToAttach);
    ASSERT_EQ(root->rc->data, 2);
    ASSERT_EQ(tree->size(), 2);
}
TEST(BinTreeTest, SecedeMethod) {
    // Create a binary tree
    auto tree = std::make_shared<BinTree<int>>();
    auto root = tree->insertAsRoot(1);
    auto leftChild = tree->insertAsLC(root, 2);
    auto rightChild = tree->insertAsRC(root, 3);

    // Secede the left subtree
    auto secededTree = tree->secede(leftChild);

    // Check that the seceded tree is correct
    ASSERT_EQ(secededTree->root()->data, 2);
    ASSERT_EQ(secededTree->size(), 1);

    // Check that the original tree is correctly updated
    ASSERT_EQ(tree->root()->data, 1);
    ASSERT_EQ(tree->root()->rc->data, 3);
    ASSERT_EQ(tree->size(), 2);
    ASSERT_TRUE(!tree->root()->lc);
}

TEST(BinTreeTest, RemoveMethod) {
    // Create a binary tree
    auto tree = std::make_shared<BinTree<int>>();
    auto root = tree->insertAsRoot(1);
    auto leftChild = tree->insertAsLC(root, 2);
    auto rightChild = tree->insertAsRC(root, 3);

    // Remove the left child
    auto removedSize = tree->remove(leftChild);

    // Check that the removed size is correct
    ASSERT_EQ(removedSize, 1);

    // Check that the original tree is correctly updated
    ASSERT_EQ(tree->root()->data, 1);
    ASSERT_EQ(tree->root()->rc->data, 3);
    ASSERT_EQ(tree->size(), 2);
    ASSERT_TRUE(!tree->root()->lc);
}