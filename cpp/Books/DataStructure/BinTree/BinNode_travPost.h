//
// Created by rick on 2024/4/26.
//

#pragma once

#include <stack>

template<typename T>
static void gotoHLVFS(std::stack<BinNode<T>*>& s){
    auto x = s.top();

}

template<typename T, BinNodeVisitor<T> VST>
void travPostRecurrsive(BinNode<T> *x, VST &visit) {
    if (x == nullptr) return;
    travPostRecurrsive(x->lc.get(), visit);
    travPostRecurrsive(x->rc.get(), visit);
    visit(x->data);
}

template<typename T>
template<BinNodeVisitor<T> VST>
void BinNode<T>::travPost(VST visit) {
    travPostRecurrsive(this, visit);
}
