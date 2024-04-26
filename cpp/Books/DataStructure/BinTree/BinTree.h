//
// Created by rick on 2024/4/25.
//

#pragma once

#include <cassert>
#include "BinNode.h"

template<typename T>
class BinTree {
protected:
    int _size = 0;
    std::unique_ptr<BinNode<T>> _root = nullptr;


public:
    BinTree() = default;

    int size() const { return this->_size; }

    bool empty() const { return !this->_root; }

    BinNode<T> *root() const { return _root.get(); }

    BinNode<T> *insertAsRoot(T e);

    BinNode<T> *insertAsLC(BinNode<T> *x, T e);

    BinNode<T> *insertAsRC(BinNode<T> *x, T e);

    /**
     * t作为x左子树接入
     */
    BinNode<T> *attachAsLC(BinNode<T> *x, BinTree<T> &&t);

    /**
     * t作为x右子树接入
     */
    BinNode<T> *attachAsRC(BinNode<T> *x, BinTree<T> &&t);

    /**
     * 将子树x从当前树中摘除，并将其转换为一颗独立的树
     * x必须为二叉树中的合法位置
     */
    BinTree<T> secede(BinNode<T> *x) {
        assert(x->parent != nullptr);
        auto parent = x->parent;
        BinTree<T> tree;
        if (x->isLChild()) {
            tree._root = std::move(parent->lc);
        } else {
            tree._root = std::move(parent->rc);
        }

        parent->updateHeightAbove();
        tree._root->parent = nullptr;
        tree._size = tree._root->size();
        this->_size -= tree._size;

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
};
