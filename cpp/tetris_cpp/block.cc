//
// Created by rick on 2024/9/23.
//

#include "block.h"
#include "colors.h"

Block::Block() : cellSize{30}, rotationState{Rotation::UP}, colors{getCellColors()} {

}

void Block::draw() {
    const auto &tiles = cells[rotationState];
    for (const auto &tile: tiles) {
        DrawRectangle(tile.column * cellSize + 1, tile.row * cellSize + 1, cellSize - 1, cellSize - 1, colors[id]);
    }
}
