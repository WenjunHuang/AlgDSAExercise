//
// Created by rick on 2024/5/10.
//
#include "BST.h"
#include <gtest/gtest.h>


TEST(BSTTest, Search) {
    // Create a binary search tree
    BST<int> bst;

    // Insert elements
    bst.insert(5);
    bst.insert(3);
    bst.insert(7);

    // Test search
    auto &foundNode = bst.search(3);
    ASSERT_TRUE(foundNode);
    ASSERT_EQ(foundNode->data, 3);

    // Test search for non-existing element
    auto &notFoundNode = bst.search(10);
    ASSERT_FALSE(notFoundNode);
    ASSERT_TRUE(bst.getHot() != nullptr);
    ASSERT_EQ(bst.getHot()->data, 7);
}

TEST(BSTTest, RemoveMethod) {
    // Create a binary search tree
    BST<int> bst;

    // Insert elements
    bst.insert(5);
    bst.insert(3);
    bst.insert(7);

    EXPECT_EQ(bst.root()->height, 1);

    // Remove an element
    bool removed = bst.remove(3);

    // Check that the remove operation was successful
    ASSERT_TRUE(removed);

    auto root = bst.root();
    EXPECT_EQ(root->height, 1);

    // Check that the removed element is no longer in the tree
    auto &notFoundNode = bst.search(3);
    ASSERT_FALSE(notFoundNode);

    // Check that the _hot member field is correctly updated
    ASSERT_TRUE(bst.getHot() != nullptr);
    ASSERT_EQ(bst.getHot()->data, 5);
}