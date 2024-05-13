
add_library(Tree INTERFACE)
target_link_libraries(Tree INTERFACE Shared)
target_include_directories(Tree INTERFACE ${CMAKE_CURRENT_LIST_DIR})
add_subdirectory(tests)