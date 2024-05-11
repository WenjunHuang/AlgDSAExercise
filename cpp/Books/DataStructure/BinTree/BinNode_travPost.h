//
// Created by rick on 2024/4/26.
//

#pragma once

#include <stack>

template<typename T>
static void gotoHLVFS(std::stack<BinNodePosi<T>> &s) {
    while (auto x = s.top()) {
        if (x->hasLChild()) { //尽可能向左
            if (x->hasRChild()) s.push(x->rc);
            s.push(x->lc);
        } else //实不得已
            s.push(x->rc); //才向右
    }
    s.pop();
}

template<typename T, typename VST>
void travPostI(BinNodePosi<T> x, VST &visit) { // 二叉树的后序遍历（迭代版）
    std::stack<BinNodePosi<T>> s; //辅助栈
    if (x != nullptr) s.push(x); //根节点入栈

    auto current = x;
    while (!s.empty()) {
        if (s.top() != current->parent) {

        }

    }
}

template<typename T, BinNodeVisitor<T> VST>
void travPostRecurrsive(BinNodePosi<T> x, VST &visit) {
    if (x == nullptr) return;
    travPostRecurrsive(x->lc, visit);
    travPostRecurrsive(x->rc, visit);
    visit(x->data);
}

template<typename T>
template<BinNodeVisitor<T> VST>
void BinNode<T>::travPost(VST visit) {
    travPostRecurrsive(this, visit);
}
