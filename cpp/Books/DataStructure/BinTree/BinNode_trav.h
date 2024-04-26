//
// Created by rick on 2024/4/26.
//

#pragma once

#include <stack>

template<typename T, BinNodeVisitor<T> VST>
void travPrevRecurrsive(BinNode<T> *x, VST &visit) {
    if (x == nullptr) return;
    visit(x->data);
    travPrevRecurrsive(x->lc.get(), visit);
    travPrevRecurrsive(x->rc.get(), visit);
}


template<typename T, BinNodeVisitor<T> VST>
static void visitAlongLeftBranch(BinNode<T> *x, VST &visit, std::stack<BinNode<T> *> &s) {
    while (x) {
        visit(x->data);

        if (x->rc)
            s.push(x->rc.get());

        x = x->lc.get();
    }
}

template<typename T, BinNodeVisitor<T> VST>
void travPrevI2(BinNode<T> *x, VST &visit) {
    std::stack<BinNode<T> *> s;
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

template<typename T, BinNodeVisitor<T> VST>
void travPostRecurrsive(BinNode<T> *x, VST &visit) {
    if (x == nullptr) return;
    travPrevRecurrsive(x->lc.get(), visit);
    travPrevRecurrsive(x->rc.get(), visit);
    visit(x->data);
}

template<typename T>
template<BinNodeVisitor<T> VST>
void BinNode<T>::travPost(VST visit) {
    travPostRecurrsive(this, visit);
}

template<typename T, BinNodeVisitor<T> VST>
void travInRecurrsive(BinNode<T> *x, VST &visit) {
    if (x == nullptr) return;
    travInRecurrsive(x->lc.get(), visit);
    visit(x->data);
    travInRecurrsive(x->rc.get(), visit);
}

template<typename T>
static void goAlongLeftBranch(BinNode<T> *x, std::stack<BinNode<T> *> &s) {
    while (x != nullptr) {
        s.push(x);
        x = x->lc.get();
    }
}

template<typename T, BinNodeVisitor<T> VST>
void travInI1(BinNode<T> *x, VST &visit) {
    std::stack<BinNode<T> *> s;
    goAlongLeftBranch(x, s);

    while (!s.empty()) {
        auto cur = s.top();
        s.pop();
        visit(cur->data);
        if (cur->rc)
            goAlongLeftBranch(cur->rc.get(), s);
    }

}

template<typename T>
template<BinNodeVisitor<T> VST>
void BinNode<T>::travIn(VST visit) {
    travInRecurrsive(this, visit);
}
