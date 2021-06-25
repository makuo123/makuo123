package com.stock.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Author mk
 * @Date 2020/10/21 14:29
 * @Version 1.0
 */
public class JsonListUtil {
    /**
     * List<T> 转 json 保存到数据库
     */
    public static <T> String listToJson(List<T> ts) {
        String jsons = JSON.toJSONString(ts);
        return jsons;
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }

    /**
     * JSON 转 POJO
     */
    public static <T> T getObject(String pojo, Class<T> tclass) {
        try {
            return JSONObject.parseObject(pojo, tclass);
        } catch (Exception e) {
            //log.error(tclass + "转 JSON 失败");
            e.printStackTrace();
        }
        return null;
    }
}
