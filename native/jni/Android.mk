#定义本地路径变量 LOCAL_PATH
LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := native-lib
LOCAL_CPP_EXTENSION := cpp 
# LOCAL_SRC_FILES := native-lib.c
LOCAL_SRC_FILES:= opensl_audio.cpp
LOCAL_SRC_FILES+= pcmdata.cpp
LOCAL_SRC_FILES+= WlQueue.cpp
LOCAL_LDLIBS := -llog -lz -lOpenSLES -landroid 
include $(BUILD_SHARED_LIBRARY)