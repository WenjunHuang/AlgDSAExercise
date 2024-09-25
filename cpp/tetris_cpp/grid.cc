//
// Created by rick on 2024/9/23.
//

#include "grid.h"
#include <vector>
#include <raylib.h>
#include "colors.h"


Grid::Grid() : numRows{20}, numCols{10}, cellSize{30}, grid{},
               colors{getCellColors()} {
}

void Grid::initialize() {

}

void Grid::draw() {
    for (auto row = 0; row < numRows; row++) {
        for (auto column = 0; column < numCols; column++) {
            auto cellValue = grid[row][column];
            DrawRectangle(column * cellSize + 1, row * cellSize + 1, cellSize - 1, cellSize - 1, colors[cellValue]);
        }
    }

}
