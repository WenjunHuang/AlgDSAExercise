project(tetris_cpp)

set(CMAKE_CXX_STANDARD 20)
find_package(raylib CONFIG REQUIRED)
add_executable(tetris_cpp main.cpp
        grid.cc
        grid.h
        block.cc
        block.h
        position.cc
        position.h
        colors.h
        colors.cc
        blocks.h)
target_link_libraries(tetris_cpp PRIVATE raylib)
