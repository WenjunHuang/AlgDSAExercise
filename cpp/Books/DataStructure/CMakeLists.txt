

find_package(GTest CONFIG REQUIRED)
add_single_file_targets(PREFIX "DSA" DIR ${CMAKE_CURRENT_LIST_DIR}
        TESTS
        LIBS GTest::gtest GTest::gtest_main)

add_library(BinTree STATIC
        BinTree/BinNode.h
        BinTree/BinTree.h
        BinTree/BinTree.cc)