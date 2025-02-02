package com.lottery.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.*;

/**
 * @Author: wyg
 * @Date: 2017/11/21 上午10:01
 * @Description:
 */
@Slf4j
public class IdUtils {

    public static final String[] CHARSET = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    /**
     * 生成36位的唯一ID
     *
     * @return
     */
    public static String buildUUID() {
        return UUID.randomUUID().toString();
    }
    /**
     * 生成32位的唯一ID,去除‘-’
     *
     * @return
     */
    public static String buildUUID32() {
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 生成18位的唯一主键（15位时间戳+3位随机数）
     *
     * @return
     */
    public static BigInteger uniqueId() {
        StringBuffer id = new StringBuffer();
        id.append(DateUtils.formatDate(new Date(), DateUtils.YYYYMMDDHHMMSSSSS));
        id.append(String.valueOf(IdUtils.randomNum(3)));
        return new BigInteger((id.toString()));
    }

    /**
     * 生成指定范围
     * 包含最大、最小值
     *
     * @return
     */
    public static int randomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 生成N位随机数
     *
     * @return
     */
    public static long randomNum(int n) {
        return (long) (Math.random() * (0.9 * (long) Math.pow(10, n))) + (long) Math.pow(10, n - 1);
    }

    /**
     * 随机生成6位数字验证码
     *
     * @return
     */
    public static String createVerifyCode() {
        List<String> list = Arrays.asList(CHARSET);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        return afterShuffle.substring(2, 8);
    }

}
