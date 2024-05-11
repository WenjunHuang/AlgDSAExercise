//
// Created by rick on 2024/5/10.
//
#include "BST.h"
#include <gtest/gtest.h>


TEST(BSTTest, Search) {
    // Create a binary search tree
    auto bst = std::make_shared<BST<int>>();

    // Insert elements
    bst->insert(5);
    bst->insert(3);
    bst->insert(7);

    // Test search
    auto foundNode = bst->search(3);
    ASSERT_TRUE(foundNode);
    ASSERT_EQ(foundNode->data, 3);

    // Test search for non-existing element
    auto notFoundNode = bst->search(10);
    ASSERT_FALSE(notFoundNode);
}
