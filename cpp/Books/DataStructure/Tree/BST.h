//
// Created by rick on 2024/5/10.
//

#pragma once

#include <compare>
#include "BinTree.h"


template<typename T>
static BinNodePosi<T> &searchIn(BinNodePosi<T> &v, const T &e, BinNodePosi<T> &hot) {
    if (v == nullptr || e == v->data) return v;

    hot = v;
    return searchIn(e < v->data ? v->lc : v->rc, e, hot);
}

template<typename T>
static BinNodePosi<T> removeAt(BinNodePosi<T> &x, BinNodePosi<T> &hot) {
    BinNodePosi<T> w = x; // 实际被摘除的节点，初值同x
    BinNodePosi<T> succ; // 实际被删除节点的接替者
    if (!hasLChild(x)) {
        // 若x的左子树为空，则可直接将x替换为其右子树
        x = x->rc;
        succ = x;
    } else if (!hasRChild(x)) {
        // 若右子树为空，则可对称地处理
        x = x->lc;
        succ = x;
    } else { // 若左右子树均存在，则选择x的直接后继作为实际被摘除节点，为此需要
        w = w->succ(); // （在右子树中）找到x的直接后继w
        std::swap(x->data, w->data);

        auto u = w->parent;
        ((u == x) ? u->rc : u->lc) = succ = w->rc; // 隔离节点w，因为w为x的直接后继，所以w必定没有左节点
    }

    hot = w->parent; //记录实际被删除节点的父亲
    if (succ) succ->parent = hot; //并将被删除节点的接替者与hot相连

    // 释放被摘除节点，返回接替者
    release(std::move(w->data));
    release(w);

    return succ;
}

template<typename T> requires std::three_way_comparable<T> && std::equality_comparable<T>
class BST : public BinTree<T> {
protected:
    // “命中”节点的父亲
    BinNodePosi<T> _hot = nullptr;

public:
    BST() = default;

    ~BST() = default;

    /*
     * 查找
     */
    virtual BinNodePosi<T> &search(const T &e) {
        this->_hot = nullptr;
        return searchIn(this->_root, e, this->_hot);
    }

    /**
     * 插入
     */
    virtual BinNodePosi<T> insert(T e) {
        auto &x = this->search(e);
        if (x) return x;

        // 创建新节点x：以e为值，以_hot为父
        x = new BinNode<T>(std::move(e), this->_hot);

        this->_size++;
        x->updateHeightAbove();

        return x;
    }


    /*
     * 删除
     */
    virtual bool remove(const T &e) {
        auto &x = this->search(e);
        if (x == nullptr) return false;

        removeAt(x, _hot);
        this->_size--;
        if (_hot != nullptr) _hot->updateHeightAbove();
        return true;
    }

    /******************************************************************************************
     * BST节点旋转变换统一算法（3节点 + 4子树），返回调整之后局部子树根节点的位置
     * 注意：尽管子树根会正确指向上层节点（如果存在），但反向的联接须由上层函数完成
     ******************************************************************************************/
    BinNodePosi<T> rotateAt(BinNodePosi<T> v) {
        auto p = v->parent;
        auto g = p->parent;
        switch (isRChild(p) << 1 | isRChild(v)) {
            case 0b00: // zig-zig
                p->parent = g->parent;
                return this->connect34(v, p, g, v->lc, v->rc, p->rc, g->rc);
            case 0b01: // zig-zag
                v->parent = g->parent;
                return this->connect34(p, v, g, p->lc, v->lc, v->rc, g->rc);
            case 0b10: // zag-zig
                v->parent = g->parent;
                return this->connect34(g, v, p, g->lc, v->lc, v->rc, p->rc);
            default: // zag-zag
                p->parent = g->parent;
                return this->connect34(g, p, v, g->lc, v->lc, v->rc, p->rc);
        }

    }

    BinNodePosi<T> connect34(BinNodePosi<T> a, BinNodePosi<T> b, BinNodePosi<T> c,
                             BinNodePosi<T> t0, BinNodePosi<T> t1, BinNodePosi<T> t2, BinNodePosi<T> t3) {
        a->lc = t0;
        if (t0) t0->parent = a;
        a->rc = t1;
        if (t1) t1->parent = a;
        a->updateHeight();

        c->lc = t2;
        if (t2) t2->parent = c;
        c->rc = t3;
        if (t3) t3->parent = c;
        c->updateHeight();

        b->lc = a;
        a->parent = b;
        b->rc = c;
        c->parent = b;
        b->updateHeight();

        return b; //该子树新的根节点
    }

    BinNodePosi<T> getHot() { return _hot; }
};
