package com.lottery.common.utils;

/**
 * 自定义String工具类
 */
public class ObjectUtils {

    public static boolean equalsIgnoreEmptyNull(Object a, Object b) {

        // 将字符串
        if (a instanceof String && a.equals("")) {
            a = null;
        }
        if (b instanceof String && b.equals("")) {
            b = null;
        }

        return (a == b) || (a != null && a.equals(b));
    }


}
