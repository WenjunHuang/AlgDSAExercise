//
// Created by rick on 2024/4/25.
//

#pragma once

#include "BinTree.h"

template<typename T>
BinNode<T> *BinTree<T>::attachAsLC(BinNode<T> *x, BinTree<T> &&t) {
    Rank oldLCSize = 0;
    if (x->lc) oldLCSize = x->lc->size();

    if ((x->lc = std::move(t._root))) x->lc->parent = x;

    this->_size -= oldLCSize;
    this->_size += t._size;
    x->updateHeightAbove();

    return x;
}

template<typename T>
BinNode<T> *BinTree<T>::attachAsRC(BinNode<T> *x, BinTree<T> &&t) {
    Rank oldRCSize = 0;
    if (x->rc) oldRCSize = x->rc->size();

    if ((x->rc = std::move(t._root))) x->lc->parent = x;

    this->_size -= oldRCSize;
    this->_size += t._size;
    x->updateHeightAbove();

    return x;
}
