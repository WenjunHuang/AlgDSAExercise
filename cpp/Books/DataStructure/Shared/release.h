//
// Created by rick on 2024/5/11.
//

#pragma once

#include <iostream>
#include <typeinfo>
#include <format>

template<typename T>
struct Cleaner {
    static void clean(T x) {
#ifdef _DEBUG
        static int n = 0;
//        if (strlen(typeid(T).name()) < 7) { //只输出基本类型
        std::cout << std::format("\t<{}>[{}]=", typeid(T).name(), ++n) << x << " purged" << std::endl;
//        }
#endif
    }
};

template<typename T>
struct Cleaner<T *> {
    static void clean(T *v) {
        delete v;
#ifdef _DEBUG
        static int n = 0;
        std::cout << std::format("\t<{}>[{}]", typeid(T *).name(), ++n) << std::endl;
#endif
    }
};


template<typename T>
void release(T x) { Cleaner<T>::clean(x); }