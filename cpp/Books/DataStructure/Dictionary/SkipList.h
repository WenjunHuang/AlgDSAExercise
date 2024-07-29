//
// Created by rick on 2024/6/29.
//

#pragma once

#include <list>
#include "Dictionary.h"
#include "Entry.h"

template<typename T>
struct QNode;
template<typename T> using QNodePosi = QNode<T> *;

template<typename T>
struct QNode {
    T entry;
    // 前驱、后继
    QNodePosi<T> pred, succ;
    // 上邻、下邻
    QNodePosi<T> above, below;

    explicit QNode(T e = T{},
                   QNodePosi<T> p = nullptr,
                   QNodePosi<T> s = nullptr,
                   QNodePosi<T> a = nullptr,
                   QNodePosi<T> b = nullptr) : entry{e}, pred{p}, succ{s}, above{a}, below{b} {}

    /**
     * 将e作为当前节点的后继、b的上继插入
     */
    QNodePosi<T> insert(T e, QNodePosi<T> b = nullptr) {
        auto node = new QNode<T>(std::move(e), this, this->succ, nullptr, b);
        this->succ->pred = node; //设置水平逆向链接
        this->succ = node; //设置垂直逆向链接

        if (b) b->above = node; //设置垂直逆向链接

        return node;
    }
};

/**
 * 四联列表
 */
template<typename T>
struct QuadList {
    void init() {
        header = new QNode<T>; // 创建头哨兵节点
        trailer = new QNode<T>; // 创建尾哨兵节点
        header->succ = trailer;
        header->pred = nullptr;
        trailer->pred = header;
        trailer->succ = nullptr;
        header->above = trailer->above = nullptr;
        header->below = trailer->below = nullptr;
        _size = 0;
    }

    int clear() {

    }

    QuadList() { init(); }

    ~QuadList() {
        clear();
        delete header;
        delete trailer;
    }

    T remove(QNodePosi<T> p) {
    }

    QNodePosi<T> insert(const T &e, QNodePosi<T> p, QNodePosi<T> b = nullptr) {

    }

    size_t _size;
    QNodePosi<T> header, trailer;

};

template<typename K, typename V>
class SkipList : public Dictionary<K, V> {
public:
    SkipList() {
        data.emplace_back({});
    }

    /**
     * 由关键码查询词条
     */
    QNodePosi<Entry<K, V>> search(K) {
        for (auto p = (*data.begin()).header;;){

        }

    }

    size_t size() const override {
        return 0;
    }

    bool put(K k, V v) override {
        return false;
    }

    V *get(K k) override {
        return nullptr;
    }

    bool remove(K k) override {
        return false;
    }

private:
    std::list<QuadList<Entry<K, V>>> data;
};