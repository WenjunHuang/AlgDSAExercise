//
// Created by rick on 2024/5/11.
//

#pragma once

template<typename T>
BinNodePtr<T> BinTree<T>::attachAsRC(BinNodePtr<T> x, BinTreePtr<T> t) {
    if (x->rc) this->_size -= x->rc->size();

    // 接入
    x->rc = std::move(t->_root);
    if (x->rc) {
        x->rc->parent = x;
        this->_size += t->_size;
    }
    x->updateHeightAbove();

    return x;
}

template<typename T>
BinNodePtr<T> BinTree<T>::attachAsLC(BinNodePtr<T> x, BinTreePtr<T> t) {
    if (x->lc) this->_size -= x->lc->size();

    // 接入
    x->lc = std::move(t->_root);
    if (x->lc) {
        x->lc->parent = x;
        this->_size += t->_size;
    }
    x->updateHeightAbove();

    return x;
}
