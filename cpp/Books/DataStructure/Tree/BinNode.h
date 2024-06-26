//
// Created by rick on 2024/4/25.
//

#pragma once

#include <cstdint>
#include <utility>
#include <memory>
#include <optional>
#include <concepts>
#include <compare>
#include "release.h"

using Rank = int32_t;

template<typename T>
struct BinNode;


template<typename VST, typename T>
concept BinNodeVisitor = std::invocable<VST, T &>;

template<typename T> using BinNodePosi = BinNode<T> *; //节点位置

enum class RBColor {
    RB_RED, RB_BLACK
};

template<typename T>
constexpr Rank stature(BinNode<T> *n) {
#if defined(DSA_REDBLACK)
    if(n)
        return n->height;
    else
        return 0;
#else
    if (n) return n->height;
    else return -1;
#endif
}

template<typename T>
constexpr bool isBalanced(BinNodePosi<T> x) {
    return stature(x->lc) == stature(x->rc);
}

template<typename T>
constexpr auto balanceFactor(BinNodePosi<T> x) {
    return stature(x->lc) - stature(x->rc);
}

template<typename T>
constexpr bool isRoot(BinNodePosi<T> n);

template<typename T>
constexpr bool isLChild(BinNodePosi<T> n);

template<typename T>
constexpr bool hasParent(BinNodePosi<T> n);

template<typename T>
constexpr bool hasLChild(BinNodePosi<T>);

template<typename T>
constexpr bool hasRChild(BinNodePosi<T>);

template<typename T>
constexpr bool hasChild(BinNodePosi<T>);

template<typename T>
constexpr bool hasBothChild(BinNodePosi<T>);

template<typename T>
constexpr bool isLeaf(BinNodePosi<T>);

template<typename T>
constexpr BinNodePosi<T> &fromParentTo(BinNodePosi<T>, BinNodePosi<T> &defaultRoot);

/**
 * 二叉树节点模板类
 * @tparam T
 */
template<typename T>
struct BinNode {
    T data;
    BinNodePosi<T> parent = nullptr;//父节点及左右孩子;
    BinNodePosi<T> lc = nullptr;
    BinNodePosi<T> rc = nullptr;

    Rank height = 0; // 高度
    Rank npl = 1; // Null Path Length

    RBColor color; // 颜色（红黑树）

    Rank size() const {
        Rank s = 1;
        if (lc) s += lc->size();
        if (rc) s += rc->size();
        return s;
    }

    Rank updateHeight() {
        this->height = 1 + std::max(stature(this->lc), stature(this->rc));
        return this->height;
    }

    void updateHeightAbove() {
        auto self = this;
        while (self) {
            self->updateHeight();
            self = self->parent;
        }
    }

    BinNodePosi<T> insertAsLC(T e) {
        return lc = new BinNode<T>(std::move(e), this);
    }

    BinNodePosi<T> insertAsRC(T e) {
        return rc = new BinNode<T>(std::move(e), this);
    }


    bool hasLChild() const {
        return this->lc != nullptr;
    }

    bool hasRChild() const {
        return this->rc != nullptr;
    }

    /**
     * 找出在中序遍历下，本节点的直接后继节点
     */
    BinNodePosi<T> succ() {
        if (this->rc) {
            auto s = this->rc;
            while (s->lc) s = s->lc;
            return s;
        } else {
            auto s = this;
            while (s && s->isRChild()) s = s->parent;
            s = s ? s->parent : s;
            return s;
        }
    }

    [[nodiscard]] inline bool isLChild() const {
        if (auto p = this->parent)
            return p->lc == this;
        else
            return false;
    }

    [[nodiscard]] inline bool isRChild() const {
        if (auto p = this->parent)
            return p->rc == this;
        else
            return false;
    }

    template<BinNodeVisitor<T> VST>
    void travIn(VST);

    template<BinNodeVisitor<T> VST>
    void travLevel(VST);

    template<BinNodeVisitor<T> VST>
    void travPre(VST);

    template<BinNodeVisitor<T> VST>
    void travPost(VST);


    auto operator<=>(const BinNode<T> &bn) const requires std::three_way_comparable<T> {
        return this->data <=> bn.data;
    }

    bool operator==(const BinNode<T> &bn) const requires std::equality_comparable<T> { return data == bn.data; }

    /**
     * 顺时针旋转（右旋）
     * @return
     */
    BinNodePosi<T> zig() {
        if (!this->lc) return this;


        // 右旋后左子节点替代当前节点的位置
        auto v = this->lc;
        v->parent = this->parent;

        // 将右旋节点的右节点作为自己的左节点
        this->lc = v->rc;

        // 建立右旋节点和父节点的父子关系
        if (auto p = this->parent) {
            if (p->lc == this) {
                p->lc = v;
            } else {
                p->rc = v;
            }
        }

        // 将当前节点作为右旋节点的右节点
        v->rc = this;
        this->parent = v;

        // 更新高度
        this->updateHeight();
        v->updateHeight();

        return v;
    }

    /**
     * 逆时针旋转（左旋）
     * @return
     */
    BinNodePosi<T> zag() {
        if (!this->rc) return this;

        auto v = this->rc;
        v->parent = this->parent;

        this->rc = v->lc;

        if (auto p = this->parent) {
            if (p->lc == this) {
                p->lc = v;
            } else {
                p->rc = v;
            }
        }

        v->lc = this;
        this->parent = v;

        this->updateHeight();
        v->updateHeight();

        return v;

    }

    explicit BinNode(T &&e,
                     BinNodePosi<T> p = nullptr,
                     BinNodePosi<T> lc = nullptr,
                     BinNodePosi<T> rc = nullptr,
                     int h = 0,
                     int l = 1,
                     RBColor color = RBColor::RB_RED) :
            data(std::move(e)), parent(p), lc(lc), rc(rc), height(h), npl(l),color(color) {}
};

template<typename T>
constexpr bool isRoot(BinNodePosi<T> n) { return n->parent == nullptr; }

template<typename T>
constexpr bool isLChild(BinNodePosi<T> n) { return !isRoot(n) && n->parent->lc == n; }

template<typename T>
constexpr bool isRChild(BinNodePosi<T> n) { return !isRoot(n) && n->parent->rc == n; }

template<typename T>
constexpr bool hasParent(BinNodePosi<T> n) { return !isRoot(n); }

template<typename T>
constexpr bool hasLChild(BinNodePosi<T> n) { return n->lc != nullptr; }

template<typename T>
constexpr bool hasRChild(BinNodePosi<T> n) { return n->rc != nullptr; };

template<typename T>
constexpr bool hasChild(BinNodePosi<T> n) { return hasLChild(n) || hasRChild(n); }

template<typename T>
constexpr bool hasBothChild(BinNodePosi<T> n) { return hasLChild(n) && hasRChild(n); }

template<typename T>
constexpr bool isLeaf(BinNodePosi<T> n) { return !hasChild(n); }

template<typename T>
constexpr BinNodePosi<T> &fromParentTo(BinNodePosi<T> n, BinNodePosi<T> &defaultRoot) {
    if (isRoot(n)) { return defaultRoot; }
    else {
        if (isLChild(n)) {
            return n->parent->lc;
        } else {
            return n->parent->rc;
        }
    }
}

template<typename T>
constexpr BinNodePosi<T> sibling(BinNodePosi<T> p) {
    if (isLChild(p))
        return p->parent->rc;
    else
        return p->parent->lc;
}
template<typename T>
constexpr BinNodePosi<T> uncle(BinNodePosi<T> x) {
    return sibling(x->parent);
}

#include "BinNode_travPre.h"
#include "BinNode_travIn.h"
#include "BinNode_travPost.h"