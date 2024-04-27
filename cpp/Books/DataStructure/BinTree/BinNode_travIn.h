//
// Created by rick on 2024/4/26.
//

#pragma once

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

template<typename T, BinNodeVisitor<T> VST>
void travInI2(BinNode<T> *x, VST &visit) {
    std::stack<BinNode<T> *> s;
    while (true) {
        if (x) {
            s.push(x);
            x = x->lc.get();
        } else if (!s.empty()) {
            x = s.top();
            s.pop();
            visit(x->data);
            x = x->rc.get();
        } else
            break;
    }
}

template<typename T, BinNodeVisitor<T> VST>
void travInI3(BinNode<T> *x, VST &visit) {
    bool backtrack = false; //前一部是否刚刚从右子树回溯
    while (true) {
        if (!backtrack && x->lc) { //若有左子树且不是刚刚回溯
            // 则深入遍历左子树
            x = x->lc.get();
        } else { // 否则-无左子树或刚刚回溯（相当于无左子树）
            visit(x->data); //访问该节点

            if (x->rc) { //若其右子树非空，则
                x = x->rc.get();//深入右子树继续遍历
                backtrack = false; //并关闭回溯标致
            } else { //若右子树空，则
                auto succ = x->succ();
                if (!succ) break; //回溯（含抵达末节点时的退出返回）
                x = &succ->get();
                backtrack = true; //并设置回溯标志

            }

        }

    }
}

template<typename T>
template<BinNodeVisitor<T> VST>
void BinNode<T>::travIn(VST
                        visit) {
    travInI2(this, visit);
}
