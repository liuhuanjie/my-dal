package com.ctrip.dal.demo.util;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.List;

public class GsonUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final Gson GSON = new GsonBuilder().serializeNulls().setDateFormat(DATE_FORMAT).create();

    public static Gson getGson(){
        return GSON;
    }

    public static String Object2Json(Object obj){
        return getGson().toJson(obj);
    }

    public static <T> String t2Json(T t){
        return getGson().toJson(t);
    }

    public static <T> T json2T(String jsonString , Class<T> clazz){
        return getGson().fromJson(jsonString,clazz);
    }


    public static <T> List<T> fromJsonToArray(String arrayJsonString, Class<T> clazz) {
        final List<T> array = Lists.newArrayList();
        new JsonParser().parse(
                arrayJsonString
        ).getAsJsonArray().forEach(
                jsonElement -> array.add(
                        getGson().fromJson(
                                jsonElement, clazz
                        )
                )
        );
        return array;
    }

    public static <T> List<T> json2Collection(String jsonStr, Type type){
        return getGson().fromJson(jsonStr,type);
    }
}
