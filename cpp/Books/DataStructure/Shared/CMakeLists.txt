
add_library(Shared INTERFACE)
target_include_directories(Shared INTERFACE ${CMAKE_CURRENT_LIST_DIR})
add_subdirectory(tests)