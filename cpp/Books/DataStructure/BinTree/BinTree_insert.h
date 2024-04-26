//
// Created by rick on 2024/4/25.
//

#pragma once

#include "BinTree.h"

template<typename T>
BinNode<T> *BinTree<T>::insertAsRoot(T e) {
    this->_size = 1;
    this->_root = std::make_unique<BinNode<T>>(std::move(e));
    return this->_root.get();
}

template<typename T>
BinNode<T> *BinTree<T>::insertAsLC(BinNode<T> *x, T e) {
    this->_size++;
    x->insertASLC(std::move(e));
    x->updateHeightAbove();
    return x->lc.get();
}

template<typename T>
BinNode<T> *BinTree<T>::insertAsRC(BinNode<T> *x, T e) {
    this->_size++;
    x->insertAsRC(std::move(e));
    x->updateHeightAbove();
    return x->rc.get();
}
