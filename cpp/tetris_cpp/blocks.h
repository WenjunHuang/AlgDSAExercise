//
// Created by rick on 2024/9/23.
//

#include "block.h"
#include "position.h"

class LBlock : public Block {
public:
    LBlock() {
        id = 1;
        cells[Rotation::UP] = {Position(0, 2), Position(1, 0), Position(1, 1), Position(1, 2)};
        cells[Rotation::RIGHT] = {Position(0, 1), Position(1, 1), Position(2, 1), Position(2, 2)};
        cells[Rotation::DOWN] = {Position(1, 0), Position(1, 1), Position(1, 2), Position(2, 0)};
        cells[Rotation::LEFT] = {Position(0, 0), Position(0, 1), Position(1, 1), Position(2, 1)};
    }

};