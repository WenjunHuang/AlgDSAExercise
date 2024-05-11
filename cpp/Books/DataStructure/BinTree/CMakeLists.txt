
add_library(BinTree INTERFACE)
target_link_libraries(BinTree INTERFACE Shared)
target_include_directories(BinTree INTERFACE ${CMAKE_CURRENT_LIST_DIR})
add_subdirectory(tests)