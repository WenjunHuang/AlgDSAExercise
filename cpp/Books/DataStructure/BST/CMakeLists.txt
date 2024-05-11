
add_library(BST INTERFACE)
target_include_directories(BST INTERFACE ${CMAKE_CURRENT_LIST_DIR})
target_link_libraries(BST INTERFACE BinTree)
add_subdirectory(tests)