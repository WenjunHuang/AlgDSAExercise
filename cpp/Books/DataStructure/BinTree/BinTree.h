//
// Created by rick on 2024/4/25.
//

#pragma once

#include <memory>
#include <cassert>
#include "BinNode.h"

template<typename T>
class BinTree;


template<typename T>
class BinTree {
protected:
    int _size = 0;
    BinNodePosi<T> _root = nullptr;

public:
    BinTree() = default;

    int size() const { return this->_size; }

    bool empty() const { return !this->_root; }

    BinNodePosi<T> root() const { return this->_root; }

    // 插入根节点
    BinNodePosi<T> insertAsRoot(T e) {
        this->_size = 1;
        return _root = new BinNode<T>(std::move(e));
    }

    //插入左孩子
    BinNodePosi<T> insertAsLC(BinNodePosi<T> x, T e) {
        this->_size++;
        x->insertAsLC(std::move(e));
        x->updateHeightAbove();
        return x->lc;
    }

    // 插入右孩子
    BinNodePosi<T> insertAsRC(BinNodePosi<T> x, T e) {
        this->_size++;
        x->insertAsRC(std::move(e));
        x->updateHeightAbove();
        return x->rc;
    }

    /**
     * t作为x左子树接入
     */
    BinNodePosi<T> attachAsLC(BinNodePosi<T> x, BinTree<T> *&t) {
        x->lc = t->_root;
        if (x->lc) x->lc->parent = x;

        this->_size += t->_size;
        x->updateHeightAbove();

        t->_root = nullptr;
        t->_size = 0;
        release(t);
        t = nullptr;

        return x;
    }

    /**
     * t作为x右子树接入
     */
    BinNodePosi<T> attachAsRC(BinNodePosi<T> x, BinTree<T> *&t) {
        x->rc = t->_root;
        if (x->rc) x->rc->parent = x;

        this->_size += t->_size;
        x->updateHeightAbove();

        // 清理子树
        t->_root = nullptr;
        t->_size = 0;
        release(t);
        t = nullptr;

        return x;
    }

    /**
     * 子树删除
     */
    Rank remove(BinNodePosi<T> n) {
        // 切断来自父节点的指针
        fromParentTo(n, this->_root) = nullptr;
        n->parent->updateHeightAbove();

        auto rank = BinTree<T>::removeAt(n);
        this->_size -= rank;
        return rank;
    }

    /**
     * 将子树x从当前树中摘除，并将其转换为一颗独立的树
     * x必须为二叉树中的合法位置
     */
    BinTree<T> *secede(BinNodePosi<T> x) {
        fromParentTo(x, this->_root) = nullptr;
        x->parent->updateHeightAbove();

        auto tree = new BinTree<T>;
        tree->_root = x;
        x->parent = nullptr;

        // 更新新树的根节点信息
        tree->_size = x->size();

        // 更新本树的信息
        this->_size -= tree->_size;

        return tree;
    }

    /**
     * 层次遍历
     */
    template<typename VST>
    void travLevel(VST &visit) {
        if (this->_root) {
            this->_root->travLevel(visit);
        }
    }

    template<typename VST>
    void travPre(VST &visit) {
        if (this->_root) {
            this->_root->travPre(visit);
        }
    }

    template<typename VST>
    void travIn(VST &visit) {
        if (this->_root) {
            this->_root->travIn(visit);
        }
    }

    template<typename VST>
    void travPost(VST &visit) {
        if (this->_root) {
            this->_root->travPost(visit);
        }
    }


    /**
     * 删除二叉树中位置x处的节点及其后代，返回被删除节点的数量
     * @param x
     * @return
     */
    static Rank removeAt(BinNodePosi<T> x) {
        if (!x) return 0;

        auto n = 1 + removeAt(x->lc) + removeAt(x->rc);
        release(x->data);
        release(x);
        return n;
    }
};