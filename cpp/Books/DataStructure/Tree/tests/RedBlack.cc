//
// Created by rick on 2024/6/9.
//
#include <gtest/gtest.h>
#include "RedBlack.h"

TEST(RedBlackTest, SolveDoubleRedMethod) {
    // Create a red-black tree
    RedBlack<int> rb;

    // Insert elements
    rb.insert(50);
    rb.insert(30);
    rb.insert(70);
    rb.insert(60);

    // Insert an element that causes a double red violation with a black uncle
    rb.insert(65);

    // Check that the tree is correctly updated
    // The exact checks depend on the specifics of your implementation
    // Here are some general checks that you might perform:

    // Check that the size of the tree is correct
    ASSERT_EQ(rb.size(), 5);

    // Check that the root of the tree is black
    ASSERT_TRUE(isBlack(rb.root()));

    // Check that the inserted node is red
    auto insertedNode = rb.search(65);
    ASSERT_TRUE(insertedNode);
    ASSERT_TRUE(isBlack(insertedNode));

    auto n = rb.search(60);
    ASSERT_TRUE(n);
    ASSERT_TRUE(isRed(n));

    // Check that the parent of the inserted node is black
    ASSERT_TRUE(isBlack(insertedNode->parent));

    // Insert an element that causes a double red violation with a red uncle
    rb.insert(35);

    // Check that the tree is correctly updated
    // The exact checks depend on the specifics of your implementation
    // Here are some general checks that you might perform:

    // Check that the size of the tree is correct
    ASSERT_EQ(rb.size(), 6);

    // Check that the root of the tree is black
    ASSERT_TRUE(isBlack(rb.root()));

    // Check that the inserted node is red
    insertedNode = rb.search(35);
    ASSERT_TRUE(insertedNode);
    ASSERT_TRUE(isRed(insertedNode));

    // Check that the parent of the inserted node is black
    ASSERT_TRUE(isBlack(insertedNode->parent));
}