package com.lottery.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Create by tianjiaqin 2019/7/9-17-24
 */
public class StringEncodeUtils {

    /**
     * 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
     */
    public static final String US_ASCII = "US-ASCII";

    /**
     * ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
     */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /**
     * 8 位 UCS 转换格式
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序
     */
    public static final String UTF_16BE = "UTF-16BE";

    /**
     * 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序
     */
    public static final String UTF_16LE = "UTF-16LE";

    /**
     * 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识
     */
    public static final String UTF_16 = "UTF-16";

    /**
     * 中文超大字符集
     */
    public static final String GBK = "GBK";


    public static final String GB2312 = "GB2312";

    /**
     * 获取 String 字符串编码格式
     *
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getEncodeCharset(String text) {

        if (StringUtils.isBlank(text)) {
            return null;
        }
        try {
            if (text.equals(new String(text.getBytes(Charset.defaultCharset()), US_ASCII))) {
                return US_ASCII;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            if (text.equals(new String(text.getBytes(ISO_8859_1), ISO_8859_1))) {
                return ISO_8859_1;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            if (text.equals(new String(text.getBytes(UTF_8), UTF_8))) {
                return UTF_8;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            if (text.equals(new String(text.getBytes(GBK), GBK))) {
                return GBK;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            if (text.equals(new String(text.getBytes(GB2312), GB2312))) {
                return GB2312;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            if (text.equals(new String(text.getBytes(GB2312), GB2312))) {
                return GB2312;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取GB2312编码格式的字符串
     *
     * @param text
     * @return
     */
    public static String getGB2312Encode(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        String resultEncode = null;
        try {
            String encodeCharset = getEncodeCharset(text);
            // 如果是utf-8转换为gb2312 其他不转换
            if (UTF_8.equalsIgnoreCase(encodeCharset)) {
                resultEncode = StringUtils.toEncodedString(text.getBytes(encodeCharset), Charset.forName(GB2312));
            } else {
                resultEncode = text;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultEncode;
    }


}
