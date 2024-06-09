//
// Created by rick on 2024/6/9.
//

#pragma once

#include "BST.h"

template<typename T>
constexpr bool isBlack(BinNodePosi<T> p) {
    return !p  //外部节点也视作黑节点
           || p->color == RBColor::RB_BLACK;
}

template<typename T>
constexpr bool isRed(BinNodePosi<T> p) {
    return !isBlack(p);
}

template<typename T>
class RedBlack : public BST<T> {
protected:
    /**
     * 双红修正
     */
    void solveDoubleRed(BinNodePosi<T> x) {
        if (isRoot(x)) {
            this->_root->color = RBColor::RB_BLACK;
            this->_root->height++;
            return;
        }

        auto p = x->parent; if (isBlack(p)) return; //若p为黑，则可终止调整。否则
        auto g = p->parent; //既然p为红，则x的祖父必存在，且为黑色
        auto u = uncle(x); //以下，视x叔父u的颜色分别处理
        if (isBlack(u)) { // u为黑色（含nullptr）时
            if (isLChild(x) == isLChild(p)) //若x与p同侧（即zig-zig或zag-zag），则
                p->color = RBColor::RB_BLACK; //p由红转黑，x保持红
            else //若x与p异侧（即zig-zag或zag-zig），则
                x->color = RBColor::RB_BLACK; //x由红转黑，p保持红

            g->color = RBColor::RB_RED;

            auto gg = g->parent; //曾祖父
            auto& r = fromParentTo(g,this->_root);
            r = this->rotateAt(x); //调整后的子树根节点
            r->parent = gg; //与原曾祖父联结
        } else { //若u为红色
            p->color = RBColor::RB_BLACK; p->height++; //p由红转黑
            u->color = RBColor::RB_BLACK; u->height++; //u由红转黑
            g->color = RBColor::RB_RED;
            this->solveDoubleRed(g);
        }
    }

    /**
     * 双黑修正
     */
    void solveDoubleBlack(BinNodePosi<T> x) {}

public:
    BinNodePosi<T> insert(T e) override {
        auto &x = this->search(e);
        if (x) return x;

        x = new BinNode<T>(std::move(e), this->_hot, nullptr, nullptr, 0);
        this->_size++;
        this->solveDoubleRed(x);

        return x ? x : this->_hot->parent;

    }

    bool remove(const T &e) override {
        return true;
    }
};