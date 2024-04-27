//
// Created by rick on 2024/4/25.
//

#pragma once

#include <cstdint>
#include <utility>
#include <memory>
#include <optional>

using Rank = uint32_t;


template<typename T>
struct BinNode;

template<typename T>
Rank stature(BinNode<T> *n) {
#if defined(DSA_REDBLACK)
#else
    if (n) return n->height;
    else return -1;
#endif
}

template<typename VST, typename T>
concept BinNodeVisitor = std::invocable<VST, T &>;

/**
 * 二叉树节点模板类
 * @tparam T
 */
template<typename T>
struct BinNode {
    T data;
    BinNode<T> *parent = nullptr;//父节点及左右孩子;
    std::unique_ptr<BinNode> lc = nullptr;
    std::unique_ptr<BinNode> rc = nullptr;
    Rank height = 0; // 高度
    Rank npl = 1; // Null Path Length

    BinNode() = default;

    explicit BinNode(T e,
                     BinNode<T> *p = nullptr,
                     std::unique_ptr<BinNode> lc = nullptr,
                     std::unique_ptr<BinNode> rc = nullptr,
                     int h = 0,
                     int l = 1) :
            data(std::move(e)), parent(p), lc(std::move(lc)), rc(std::move(rc)), height(h), npl(l) {}

    Rank size() const;

    Rank updateHeight();

    void updateHeightAbove();

    BinNode<T> *insertAsLC(T e);

    BinNode<T> *insertAsRC(T e);

    void adoptLC(std::unique_ptr<BinNode<T>> l);

    void adoptRC(std::unique_ptr<BinNode<T>> r);

    std::unique_ptr<BinNode<T>> abandonLC();

    std::unique_ptr<BinNode<T>> abandonRC();

    std::optional<std::reference_wrapper<BinNode<T>>> succ();

    bool isLChild() const {
        return this->parent != nullptr && this->parent->lc.get() == this;
    }

    bool isRChild() const {
        return this->parent != nullptr && this->parent->rc.get() == this;
    }

    template<BinNodeVisitor<T> VST>
    void travIn(VST);

    template<BinNodeVisitor<T> VST>
    void travLevel(VST);

    template<BinNodeVisitor<T> VST>
    void travPre(VST);

    template<BinNodeVisitor<T> VST>
    void travPost(VST);
};

#include "BinNode_insert.h"
#include "BinNode_travPre.h"
#include "BinNode_travIn.h"
#include "BinNode_travPost.h"
#include "BinNode_succ.h"