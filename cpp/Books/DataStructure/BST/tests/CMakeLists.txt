

find_package(GTest CONFIG REQUIRED)
set(TESTS BST)
foreach(t ${TESTS})
    add_executable(${t}_test ${CMAKE_CURRENT_LIST_DIR}/${t}.cc)
    target_link_libraries(${t}_test GTest::gtest GTest::gtest_main)
    target_link_libraries(${t}_test BST)
    add_test(NAME ${t}_test COMMAND ${t}_test)
endforeach()