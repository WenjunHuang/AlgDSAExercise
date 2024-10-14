#include <iostream>
#include <raylib.h>
#include "grid.h"
#include "blocks.h"

int main() {
    Color darkBlue = {44, 44, 127, 255};
    InitWindow(300, 600, "raylib Tetris");
    SetTargetFPS(60);
    Grid grid;

    LBlock block;
    while (!WindowShouldClose()) {
        BeginDrawing();
        ClearBackground(darkBlue);

        grid.draw();
        block.draw();
        EndDrawing();
    }

    CloseWindow();
    return 0;
}
