//
// Created by rick on 2024/4/26.
//

#pragma once

#include <stack>

template<typename T, BinNodeVisitor<T> VST>
void travPrevRecurrsive(BinNodePosi<T> x, VST &visit) {
    if (x == nullptr) return;
    visit(x->data);
    travPrevRecurrsive(x->lc, visit);
    travPrevRecurrsive(x->rc, visit);
}


template<typename T, BinNodeVisitor<T> VST>
static void visitAlongLeftBranch(BinNodePosi<T> x, VST &visit, std::stack<BinNodePosi<T> > &s) {
    while (x) {
        visit(x->data);

        if (x->rc)
            s.push(x->rc);

        x = x->lc;
    }
}

template<typename T, BinNodeVisitor<T> VST>
void travPrevI2(BinNodePosi<T> x, VST &visit) {
    std::stack<BinNodePosi<T> > s;
    s.push(x);
    while (!s.empty()) {
        auto cur = s.top();
        s.pop();
        visitAlongLeftBranch(cur, visit, s);
    }

}

template<typename T>
template<BinNodeVisitor<T> VST>
void BinNode<T>::travPre(VST visit) {
    travPrevI2(this, visit);
}

