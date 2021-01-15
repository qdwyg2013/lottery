package com.lottery.common.utils.http;

import lombok.Data;

@Data
public class RetryException extends RuntimeException {

    private Object result;

    private Exception exception;

    public void clear(){
        this.result = null;
        this.exception = null;
    }
}
