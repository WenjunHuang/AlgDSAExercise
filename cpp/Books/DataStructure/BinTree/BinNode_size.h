//
// Created by rick on 2024/4/26.
//

#pragma once
#include "BinNode.h"

template<typename T>
Rank BinNode<T>::size() const {
    Rank s = 1;
    if (lc) s += lc->size();
    if (rc) s += rc->size();
    return s;
}
