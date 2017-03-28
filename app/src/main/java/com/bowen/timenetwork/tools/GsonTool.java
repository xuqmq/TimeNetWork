package com.bowen.timenetwork.tools;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class GsonTool {
    private static  Gson gson = new Gson();

    /**
     *将一个Json转换为object对象
     * @param json  //json数据
     * @param object //object对象
     * @param <T> // 类型
     * @return
     */
    public static <T> T parseJson2Object(String json,Class<T> object) {
                return gson.fromJson(json,object);
    }

}
