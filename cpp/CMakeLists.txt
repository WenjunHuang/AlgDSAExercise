cmake_minimum_required(VERSION 3.15)
project(AlgorithmAndDataStructure)
enable_testing()
set(CMAKE_CXX_STANDARD 20)
set(CMAKE_C_STANDARD 17)
if (APPLE)
    list(APPEND CMAKE_MODULE_PATH "${CMAKE_CURRENT_LIST_DIR}/cmake")
endif ()

# if msvc then set encoding to utf8
if (MSVC)
    add_compile_options("/utf-8")
endif ()
# 单个cc文件
function(add_single_file_targets)
    include(CMakeParseArguments)
    set(options TEST)
    set(singleValues DIR PREFIX)
    set(multiValues LIBS INCLUDES DEPS)
    cmake_parse_arguments(ARG
            "${options}"
            "${singleValues}"
            "${multiValues}"
            ${ARGN})

    if (ARG_DIR)
        set(TARGETS "")
        file(GLOB SRCS ${ARG_DIR}/*.cc ${ARG_DIR}/*.cpp ${ARG_DIR}/*.c)
        foreach (SRC_FILE IN LISTS SRCS)
            get_filename_component(FILE_NAME ${SRC_FILE} NAME_WLE)
            string(REGEX MATCHALL "([A-Za-z0-9_]+)-?.*" MATCHES "${FILE_NAME}")
            if (MATCHES)
                set(TARGET_NAME ${ARG_PREFIX}_${CMAKE_MATCH_1})
                list(FIND TARGETS ${TARGET_NAME} IDX)
                if (IDX EQUAL "-1")
                    list(APPEND TARGETS ${TARGET_NAME})
                    set(${TARGET_NAME}_files ${SRC_FILE})
                else ()
                    list(APPEND ${TARGET_NAME}_files ${SRC_FILE})
                endif ()
            endif ()
        endforeach ()

        foreach (TARGET IN LISTS TARGETS)
            add_executable(${TARGET} ${${TARGET}_files})
            if (ARG_LIBS)
                target_link_libraries(${TARGET} PRIVATE ${ARG_LIBS})
            endif ()
            if (ARG_INCLUDES)
                target_include_directories(${TARGET} PRIVATE ${ARG_INCLUDES})
            endif ()
            if (ARG_DEPS)
                add_dependencies(${TARGET} ${ARG_DEPS})
            endif ()
            if (ARG_TEST)
                add_test(NAME ${TARGET} COMMAND ${TARGET})
            endif ()
            #            if (WIN32)
            #                target_compile_options(${TARGET} PRIVATE -fsanitize=address)
            #                target_link_directories(${TARGET} PRIVATE "$ENV{ProgramFiles\(x86\)}/Microsoft Visual Studio/2019/Community/VC/Tools/Llvm/x64/lib/clang/12.0.0/lib/windows")
            #                target_link_libraries(${TARGET} PRIVATE clang_rt.asan_dynamic-x86_64 clang_rt.asan_dynamic_runtime_thunk-x86_64)
            #                target_link_options(${TARGET} PRIVATE /wholearchive:clang_rt.asan_dynamic_runtime_thunk-x86_64.lib)
            #            endif ()
        endforeach ()
    else ()
        message(FATAL_ERROR "please specify DIR")
    endif ()
endfunction()

function(add_dir_target)
    include(CMakeParseArguments)
    set(singleValues DIR PREFIX)
    set(multiValues LIBS INCLUDES DEPS)
    cmake_parse_arguments(ARG
            ""
            "${singleValues}"
            "${multiValues}"
            ${ARGN})
    if (ARG_PREFIX AND ARG_DIR)
        if (IS_DIRECTORY ${ARG_DIR})
            file(GLOB children ${ARG_DIR}/*)
            foreach (child IN LISTS children)
                IF (IS_DIRECTORY ${child})
                    file(GLOB files LIST_DIRECTORIES false ${child}/*)
                    get_filename_component(dir_name ${child} NAME)
                    add_executable(${ARG_PREFIX}_${dir_name} ${files})
                    if (ARG_LIBS)
                        target_link_libraries(${ARG_PREFIX}_${dir_name} PRIVATE ${ARG_LIBS})
                    endif ()
                    if (ARG_INCLUDES)
                        target_include_directories(${ARG_PREFIX}_${dir_name} PRIVATE ${ARG_INCLUDES})
                    endif ()
                    if (ARG_DEPS)
                        add_dependencies(${ARG_PREFIX}_${dir_name} ${ARG_DEPS})
                    endif ()
                ENDIF ()
            endforeach ()
        else ()
            message(SEND_ERROR "${ARG_DIR} is not a directory")
        endif ()
    endif ()
endfunction()

function(create_test)
    include(CMakeParseArguments)
    set(singleValues NAME)
    set(multiValues SRCS LIBS)
    cmake_parse_arguments(ARG
            ""
            "${singleValues}"
            "${multiValues}"
            ${ARGN})
    add_executable(${ARG_NAME} ${ARG_SRCS})
    target_link_libraries(${ARG_NAME} PRIVATE
            "${ARG_LIBS}"
            GTest::gmock GTest::gtest GTest::gtest_main)
endfunction()

add_subdirectory(Books)
add_subdirectory(LeetCode)
add_subdirectory(tetris_cpp)