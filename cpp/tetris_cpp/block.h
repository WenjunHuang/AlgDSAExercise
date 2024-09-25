//
// Created by rick on 2024/9/23.
//

#pragma once

#include <vector>
#include <map>
#include <raylib.h>
#include "position.h"

enum class Rotation {
    // four rotation state
    UP,
    RIGHT,
    DOWN,
    LEFT
};
class Block {
public:
    Block();
    void draw();

    int id;
    std::map<Rotation, std::vector<Position>> cells;
private:
    int cellSize;
    Rotation rotationState;
    std::vector<Color> colors;

};
