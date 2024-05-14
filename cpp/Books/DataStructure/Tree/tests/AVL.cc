//
// Created by rick on 2024/5/13.
//
#include <gtest/gtest.h>
#include "AVL.h"

TEST(AVLTest, InsertionMaintainsBalance) {
    AVL<int> tree;
    tree.insert(5);
    tree.insert(3);
    tree.insert(7);
    tree.insert(2);
    tree.insert(6);
    tree.insert(8);
    ASSERT_TRUE(isAVLBalanced(tree.root()));

    std::vector<int> col;
    tree.travIn([&](auto v){
        col.push_back(v);
    });
    ASSERT_EQ(col,std::vector<int>({2,3,5,6,7,8}));
}

TEST(AVLTest, RemovalMaintainsBalance) {
    AVL<int> tree;
    tree.insert(5);
    tree.insert(3);
    tree.insert(7);
    tree.insert(2);
    tree.insert(6);
    tree.insert(8);
    tree.remove(5);
    ASSERT_TRUE(isAVLBalanced(tree.root()));

    std::vector<int> col;
    tree.travIn([&](int& v){
        col.push_back(v);
    });

    ASSERT_EQ(col, std::vector<int>({2, 3, 6, 7, 8}));
}

TEST(AVLTest, TallerChildReturnsCorrectChild) {
    AVL<int> tree;
    tree.insert(5);
    tree.insert(3);
    tree.insert(7);
    ASSERT_EQ(tallerChild(tree.root())->data, 7);
}

TEST(AVLTest, InsertionIncreasesSize) {
    AVL<int> tree;
    tree.insert(5);
    ASSERT_EQ(tree.size(), 1);
    tree.insert(3);
    ASSERT_EQ(tree.size(), 2);
}

TEST(AVLTest, RemovalDecreasesSize) {
    AVL<int> tree;
    tree.insert(5);
    tree.insert(3);
    tree.remove(5);
    ASSERT_EQ(tree.size(), 1);
}

TEST(AVLTest, RemovalOfNonExistentElementDoesNotChangeSize) {
    AVL<int> tree;
    tree.insert(5);
    tree.remove(3);
    ASSERT_EQ(tree.size(), 1);
}
