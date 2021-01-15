package com.lottery.common.utils.controller;

import com.lottery.common.enums.BaseCodeEnum;
import com.lottery.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 所有Controller的基类，其他Controller继承这个类.
 * 类中包含统一的成功数据响应封装方法. 也包括其他处理错误响应的方法. 其他子类只要抛出异常即可.
 */
@Slf4j
@RestController
public class BaseController {

    protected static final int SUCCESS_CODE = 0;
    protected static final int FAIL_CODE = 1;

    protected Object result(Object obj) {
        return obj;
    }

    protected Response success() {
        Response response = new Response();
        response.setErrorCode(SUCCESS_CODE);
        response.setStatusCode(BaseCodeEnum.BASE_REQUEST_SUCCESS.getCode());
        response.setStatusMsg(BaseCodeEnum.BASE_REQUEST_SUCCESS.getMsg());

        return response;
    }

    protected Response success(Object obj) {
        Response response = new Response();
        response.setErrorCode(SUCCESS_CODE);
        response.setStatusCode(BaseCodeEnum.BASE_REQUEST_SUCCESS.getCode());
        response.setStatusMsg(BaseCodeEnum.BASE_REQUEST_SUCCESS.getMsg());
        response.setData(obj);

        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Response handleGoodsException(BaseException exception) {
        log.error("error code [{}]", exception.getCode());
        log.error("error exception {}", exception);

        Response response = new Response();
        response.setErrorCode(FAIL_CODE);
        response.setStatusCode(exception.getCode());
        response.setStatusMsg(exception.getMessage());

        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Response handleGoodsException(RuntimeException exception) {
        log.error("error exception {}", exception);

        Response response = new Response();
        response.setErrorCode(FAIL_CODE);
        response.setStatusCode(BaseCodeEnum.BASE_SYSTEM_ERROR.getCode());
        response.setStatusMsg(exception.getMessage());

        return response;
    }
}
