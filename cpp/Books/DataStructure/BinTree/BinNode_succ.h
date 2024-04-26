//
// Created by rick on 2024/4/25.
//

#pragma once

template<typename T>
std::optional<std::reference_wrapper<BinNode<T>>> BinNode<T>::succ() {
    if (this->rc) {
        auto s = rc.get();
        while (s->lc) s = s->lc.get();
        return {{*s}};
    } else {
        auto s = this;
        while (s->isRChild()) s = s->parent;
        s = s->parent;

        if (s != nullptr) return {{*s}};
        else return std::nullopt;
    }
}
