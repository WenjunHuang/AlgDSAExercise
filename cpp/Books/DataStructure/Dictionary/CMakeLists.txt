
add_library(Dictionary INTERFACE)
target_link_libraries(Dictionary INTERFACE Shared)
target_include_directories(Dictionary INTERFACE ${CMAKE_CURRENT_LIST_DIR})
add_subdirectory(tests)