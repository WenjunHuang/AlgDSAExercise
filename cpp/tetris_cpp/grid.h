//
// Created by rick on 2024/9/23.
//

#pragma once

#include <vector>
#include <raylib.h>


class Grid {
public:
    Grid();

    void initialize();
    void draw();

    int grid[20][10];
private:
    int numRows;
    int numCols;
    int cellSize;
    std::vector<Color> colors;

};
