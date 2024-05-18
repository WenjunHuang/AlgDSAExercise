//
// Created by rick on 2024/5/14.
//

#pragma once

#include "BST.h"

template<typename NodePosi>
constexpr void attachAsLC(NodePosi lc, NodePosi p) {
    p->lc = lc;
    if (lc) lc->parent = p;
}

template<typename NodePosi>
constexpr void attachAsRC(NodePosi p, NodePosi rc) {
    p->rc = rc;
    if (rc) rc->parent = p;
}

template<typename T>
class Splay : public BST<T> {
private:
    /**
     * Splay树伸展算法：从节点v出发逐层伸展
     */
    BinNodePosi<T> splay(BinNodePosi<T> v) { // v为因最近访问而需伸展的节点位置
        BinNodePosi<T> p, g;//v的父亲与祖父
        while ((p = v->parent) && (g = p->parent)) { //自下而上，反复对v做双层伸展
            auto gg = g->parent; //每轮之后v都以原曾祖父为父
            switch (isRChild(p) << 1 | isRChild(v)) { //视p，v的拐向，分四种情况
                case 0b00: // zig-zig
                    attachAsLC(p->rc,g);
                    attachASLC(v->rc,p);
                    attachAsRC(p,g);
                    attachAsRC(v,p);
                    break;
                case 0b01: // zig-zag
                    attachAsRC(p,v->lc);
                    attachAsLC(v->rc,g);
                    attachAsRC(v,g);
                    attachAsLC(p,v);
                    break;
                case 0b10: //zag-zig
                    attachAsLC(v->rc,p);
                    attachAsRC(g,v->lc);
                    attachAsLC(g,v);
                    attachAsRC(v,p);
                    break;
                default: // zag-zag
                    attachAsRC(g,p->lc);
                    attachAsRC(p,v->lc);
                    attachAsLC(g,p);
                    attachAsLC(p,v);
                    break;
            }

            if (!gg) v->parent = nullptr; //若v原先的曾祖父gg不存在，则v现在应为树根
            else //否则，gg此后应该以v作为左或右孩子
                (g==gg->lc) ? attachAsLC(v,gg):attachAsRC(gg,v);
            g->updateHeight();
            p->updateHeight();
            v->updateHeight();
        } //双层伸展结束时，必有g==nullptr，但p可能非空
        p = v->parent;
        if (p) { //若p果真是根，则额外在做一次单旋
            if (isLChild(v)) {
                attachAsLC(v->rc,p);
                attachAsRC(v,p);
            } else {
                attachAsRC(p,v->lc);
                attachAsLC(p,v);
            }
            p->updateHeight();
            v->updateHeight();
        }
        v->parent = nullptr;
        return v;
    }
};