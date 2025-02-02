package com.lottery.common.utils;

import java.util.regex.Pattern;

/**
 * 常用正则表达式
 */
public class PatternUtils {

    /**
     * 正则表达式：纯数字
     */
    public static final String REGEX_DIGITAL = "^\\d+$";

    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：特殊字符
     */
    public static final String REGEX_SPECIAL_CHARECTER = "[\\s-_()（）]";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^(13[0-9]|14[579]|15[0-3,5-9]|17[0135678]|18[0-9])\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则： Unicode编码中的汉字范围
     */
    public static final String REGEX_ALL_CHINESE = "^[\u2E80-\u9FFF]+$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 验证大陆身份证
     */
    public static final String REGEX_ID_CARD_MAINLAND = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$";

    /**
     * 银行卡
     */
    public static final String BANK_CARD = "^([1-9]{1})(\\d{14,18})$";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";

    /**
     * 生日
     */
    public static final String REGEX_BIRTHDAY = "^\\d{4}-\\d{1,2}-\\d{1,2}$";

    /**
     * 校验用户名
     *
     * @param digital
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isDigital(String digital) {
        return Pattern.matches(REGEX_DIGITAL, digital);
    }

    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 银行卡校验
     * @param password
     * @return
     */
    public static boolean isBankCard(String password) {
        return Pattern.matches(BANK_CARD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * unioncode 汉字
     * @param chinese
     * @return
     */
    public static boolean isUnicodeChinese(String chinese) {
        return Pattern.matches(REGEX_ALL_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 大陆身份证
     * @param idCard
     * @return
     */
    public static boolean isMainLandIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD_MAINLAND, idCard);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    /**
     * 校验生日
     *
     * @param birthday
     * @return
     */
    public static boolean isBirthday(String birthday) {
        return Pattern.matches(REGEX_BIRTHDAY, birthday);
    }

}