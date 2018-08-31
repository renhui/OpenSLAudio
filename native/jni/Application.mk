#APP_ABI := all
#APP_ABI := armeabi armeabi-v7a x86

APP_ABI := armeabi-v7a
APP_PLATFORM := android-9
APP_STL := gnustl_static #GNU STL  
APP_CPPFLAGS := -fexceptions -frtti #允许异常功能，及运行时类型识别  
APP_CPPFLAGS +=-std=c++11 #允许使用c++11的函数等功能

