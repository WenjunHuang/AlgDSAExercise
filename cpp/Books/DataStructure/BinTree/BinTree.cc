//
// Created by rick on 2024/4/25.
//

#include "BinNode.h"
int main(){
    BinNode<int> node;
    node.travPre([](int& i){});
    node.travPost([](int& i){});
}