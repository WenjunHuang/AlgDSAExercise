//
// Created by rick on 2024/4/25.
//

#pragma once

#include "BinNode.h"

template<typename T>
Rank
BinNode<T>::updateHeight() {
    this->height = 1 + std::max(stature(this->lc.get()), stature(this->rc.get()));
}

template<typename T>
void BinNode<T>::updateHeightAbove() {
    for (auto x = this; x; x = x->parent) {
        x->updateHeight();
    }
}