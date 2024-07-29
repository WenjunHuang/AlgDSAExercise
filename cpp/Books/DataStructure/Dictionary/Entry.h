//
// Created by rick on 2024/6/29.
//

#pragma once
#include <compare>

/**
 * 词条模板类
 */
template<typename K,typename V>struct Entry {
    K key;V value;//关键码、数值
    Entry(K k = K(),V v = V()):key{k},value{v}{}
    Entry(const Entry<K,V>& e):key{e.key},value{e.value}{}

    auto operator<=>(const Entry<K,V>& e) const{
        return key <=> e.key;
    }

    bool operator==(const Entry<K,V>& e) const {
        return key == e.key;
    }
};
