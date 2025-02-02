package com.lottery.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author: wyg
 * @Date: 2018/4/4 上午11:07
 * @Description:
 */
@Slf4j
public class UrlUtils {

    /**
     * 对象转map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        try {
            Class superClaz = obj.getClass();
            List<Field> fieldList = new ArrayList(Arrays.asList(superClaz.getDeclaredFields()));
            while ((superClaz = superClaz.getSuperclass()) != null) {
                fieldList.addAll(Arrays.asList(superClaz.getDeclaredFields()));
            }
            for (Field field : fieldList) {
                field.setAccessible(true);

                Object target = field.get(obj);
                String name = field.getName();
                if (null == target) {
                    continue;
                } else if ("serialVersionUID".equalsIgnoreCase(name)) {
                    continue;
                }


                map.put(field.getName(), field.get(obj));
            }
        } catch (Throwable e) {
            log.error("真实异常打印:{}", e);
        }
        return map;
    }

    /**
     * 拼装get的url参数
     *
     * @param url
     * @param obj
     * @return
     */
    public static String urlParams(String url, Object obj) {
        Map<String, Object> params = objectToMap(obj);
        if (null == params || params.isEmpty()) {
            return url;
        }

        url = url.concat("?");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null && !"".equals(entry.getValue())) {

                url = url.concat(entry.getKey()).concat("=");
                if (entry.getValue() instanceof String[]) {
                    url = url.concat(JSONObject.toJSONString(entry.getValue())).concat("&");
                } else {
                    url = url.concat(entry.getValue().toString()).concat("&");
                }
            }
        }
        if (url.endsWith("&") || url.endsWith("?")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;

    }

    /**
     * 拼装get的url参数
     *
     * @param url
     * @param params
     * @return
     */
    public static String urlMapParams(String url, Map<String, Object> params) {
        if (null == params || params.isEmpty()) {
            return url;
        }

        url = url.concat("?");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null && entry.getValue() != "") {
                url = url.concat(entry.getKey()).concat("=").concat(entry.getValue().toString()).concat("&");
            }
        }
        if (url.endsWith("&") || url.endsWith("?")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;

    }
}