//
// Created by rick on 2024/4/25.
//

#pragma once

template<typename T>
BinNodePtr<T> BinTree<T>::insertAsRoot(T e) {
    this->_size = 1;
    this->_root = BinNode<T>::create(std::move(e));
    return this->_root;
}

template<typename T>
BinNodePtr<T> BinTree<T>::insertAsLC(BinNodePtr<T> x, T e) {
    this->_size++;
    x->insertAsLC(std::move(e));
    x->updateHeightAbove();
    return x->lc;
}

template<typename T>
BinNodePtr<T> BinTree<T>::insertAsRC(BinNodePtr<T> x, T e) {
    this->_size++;
    x->insertAsRC(std::move(e));
    x->updateHeightAbove();
    return x->rc;
}
