package com.lottery.common.utils.http;

import lombok.Data;

@Data
public class RetryMeta {
    public static final RetryMeta NO_RETRY = new RetryMeta(0, 0);
    /**
     * 最大重试次数
     */
    private int maxAttempts;
    /**
     * 重试间隔
     * 单位为毫秒
     */
    private int interval;
    /**
     * 尝试次数
     */
    private int attempt;

    /**
     * 默认构造
     * 尝试二次
     * 每次间隔2秒
     */
    public RetryMeta() {
        this.maxAttempts = 2;
        this.interval = 2000;
        this.attempt = 1;
    }

    public RetryMeta(int maxAttempts, int interval) {
        this.maxAttempts = maxAttempts;
        this.interval = interval;
        this.attempt = 1;
    }
}
