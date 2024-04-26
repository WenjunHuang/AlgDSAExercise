
add_library(BinTree INTERFACE)
target_include_directories(BinTree INTERFACE ${CMAKE_CURRENT_LIST_DIR})
add_subdirectory(tests)