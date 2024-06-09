//
// Created by rick on 2024/5/13.
//

#pragma once

#include "BST.h"

template<typename T>
constexpr bool isAVLBalanced(BinNodePosi<T> x) {
    // avl平衡条件
    auto fac = balanceFactor(x);
    return -2 < fac && fac < 2;
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
    BinNodePosi<T> insert(T e) override {
        auto &x = this->search(e);
        if (x) return x; //确认目标节点不存在
        auto xx = x = new BinNode<T>(std::move(e), this->_hot); // 创建新节点x
        this->_size++;

        // 此时，x的父亲_hot若增高，则其祖父有可能失衡
        for (auto g = this->_hot; g; g = g->parent) {
            if (!isAVLBalanced(g)) {//一旦发现g失衡，则采用“3+4”算法使之复衡；并将子树重新接入原树
                this->fromParentTo(g) = this->rotateAt(tallerChild(tallerChild(g)));
                break; //局部子树复衡后，高度必然复原；其祖先亦必如此，故调整结束
            } else { //否则，g仍平衡
                g->updateHeight(); // 只需要更新其高度（注意：即便g未失衡，高度亦可能增加）
            }
        }
        return xx; //返回新节点位置
    }

    bool remove(const T &e) override {
        auto &x = this->search(e);
        if (!x) return false; // 确认目标存在
        removeAt(x, this->_hot);
        this->_size--; // 先按BST规则删除之（此后，原节点之父_hot及其祖先均可能失衡）
        for (auto g = this->_hot; g; g = g->parent) { //从_hot出发向上，逐层检查各代祖先g
            if (!isAVLBalanced(g)) { //一旦发现g失衡，则采用“3+4”算法使之复衡，并将该子树联至原父亲
                g = this->fromParentTo(g) = this->rotateAt(tallerChild(tallerChild(g)));
            }
            g->updateHeight();
        }
        return true;
    }

};