//
// Created by rick on 2024/5/13.
//

#pragma once

#include "BST.h"

template<typename T>
constexpr bool isAVLBalanced(BinNodePosi<T> x) {
    // avl平衡条件
    return (-2 < balanceFactor(x)) && (balanceFactor(x) < 2);
}

/**
 * 在左、右孩子中选更高者
 * 在AVL平衡调整前，借此确定重构方案
 */
template<typename T>
constexpr BinNodePosi<T> tallerChild(BinNodePosi<T> x) {
    if (stature(x->lc) > stature(x->rc))
        return x->lc;
    else if (stature(x->lc) < stature(x->rc))
        return x->rc;
    else
        return isLChild(x) ? x->lc : x->rc;
}

template<typename T>
class AVL : public BST<T> {
public:
    /**
     * 将关键码e插入AVL树
     */
    BinNodePosi<T> insert(const T &e) override {
        auto& x = this->search(e);
        if (x) return x; //确认目标节点不存在
        x = new BinNode<T>(e,this->_hot); // 创建新节点x
        auto xx = x;
        this->_size++;



    }

    bool remove(const T &e) override {
        return false;
    }

};