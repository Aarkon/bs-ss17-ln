cmake_minimum_required(VERSION 3.7)
project(bs_ss17_ln)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        01/mkfile.c)

set(SOURCE_FILES
        02/hawsh/hawsh.c)

add_executable(bs_ss17_ln ${SOURCE_FILES})