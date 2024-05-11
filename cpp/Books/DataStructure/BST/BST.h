//
// Created by rick on 2024/5/10.
//

#pragma once

#include <compare>
#include "BinTree.h"

template<typename T>
static BinNodePosi<T> searchIn(BinNodePosi<T> v, const T &e, BinNodePosi<T> &hot);

template<typename T> requires std::three_way_comparable<T> && std::equality_comparable<T>
class BST : public BinTree<T> {
protected:
    // “命中”节点的父亲
    BinNodePosi<T> _hot;

public:
    /*
     * 查找
     */
    virtual BinNodePosi<T> search(const T &e) {
        this->_hot = nullptr;
        return searchIn(this->_root, e, this->_hot);
    }

    /**
     * 插入
     */
    virtual BinNodePosi<T> insert(T e) {
        auto x = this->search(e);
        if (x) return x;

        // 创建新节点x：以e为值，以_hot为父
        bool isLeft = e < this->_hot->data;
        x = BinNode<T>::create(std::move(e), this->_hot);
        if (isLeft) this->_hot->adoptLC(x);
        else this->_hot->adoptRC(x);

        this->_size++;
        x->updateHeightAbove();

        return x;
    }


    /*
     * 删除
     */
    virtual bool remove(const T &e) {
        return false;
    }

};

template<typename T>
static BinNodePosi<T> searchIn(BinNodePosi<T> v, const T &e, BinNodePosi<T> &hot) {
    if (!v || e == v->data) return v;

    hot = v;
    return searchIn(e < v->data ? v->lc : v->rc, e, hot);
}

template<typename T>
static BinNodePosi<T> removeAt(BinNodePosi<T>& x,BinNodePosi<T>& hot){
    BinNodePosi<T> w = x;
    BinNodePosi<T> succ;
    if (!x->hasLChild()){
        succ = x->rc;
    } else if (!x->hasRChild()) {
        succ = x->lc;
    } else { //左右子树均存在，则选择x的直接后继作为实际被摘除节点，为此需要
        w = w->succ();
        std::swap(w->data,x->data);

        auto u = w->parent.lock();
        if (u.get() == x.get()){
            // 后继节点是待删节点的直接右子节点
            u->adoptRC(succ);
        } else { //后继节点是待删节点右子树的某个后代的左子节点
            u->adoptLC(succ);
        }
    }

    hot = w->parent;
    if (succ) succ->parent = hot;

    return succ;
}