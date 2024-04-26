//
// Created by rick on 2024/4/25.
//

#pragma once


template<typename T>
BinNode<T> *BinNode<T>::insertAsLC(T e) {
    this->lc = std::make_unique<BinNode>(std::move(e), this);
    return this->lc.get();
}

template<typename T>
BinNode<T> *BinNode<T>::insertAsRC(T e) {
    this->rc = std::make_unique<BinNode>(std::move(e), this);
    return this->rc.get();
}
