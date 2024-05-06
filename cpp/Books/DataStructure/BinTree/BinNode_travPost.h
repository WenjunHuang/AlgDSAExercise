//
// Created by rick on 2024/4/26.
//

#pragma once

#include <stack>

template<typename T>
static void gotoHLVFS(std::stack<BinNode<T> *> &s) {
    while (auto x = s.top()) {
        if (x->hasLChild()) { //尽可能向左
            if (x->hasRChild()) s.push(x->rc.get());
            s.push(x->lc.get());
        } else //实不得已
            s.push(x->rc.get()); //才向右
    }
    s.pop();
}

template<typename T, typename VST>
void travPostI(BinNode<T> *x, VST &visit) { // 二叉树的后序遍历（迭代版）
    std::stack<BinNode<T> *> s; //辅助栈
    if (x != nullptr) s.push(x); //根节点入栈

    auto current = x;
    while (!s.empty()) {
        if (s.top() != current->parent) {

        }

    }
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
