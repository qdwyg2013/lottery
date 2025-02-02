package com.lottery.common.utils;

import com.lottery.common.enums.BaseCodeEnum;
import com.lottery.common.exception.BaseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wyg
 * @Date: 2018/4/4 上午11:07
 * @Description: 参数校验，日志打印
 */
@Component
public class ValidateUtils {

    private boolean ifCheckFlag = true;

    public ValidateUtils() {
    }

    public ValidateUtils(boolean ifCheckFlag) {
        this.ifCheckFlag = ifCheckFlag;
    }

    /**
     * 参数错误检查
     *
     * @param request
     * @param obj
     * @param clazz
     * @param br
     * @param <T>
     */
    public <T> void check(HttpServletRequest request, Object obj, Class<T> clazz, BindingResult br) throws BaseException {
        // 日志还是要打印滴....
        this.log(obj);
        // 如果关闭检查, 就跳过检查
        if (!ifCheckFlag) {
            return;
        }
        if (br != null) {
            if (br.hasFieldErrors()) {
                String errorMsg = BaseCodeEnum.BASE_PARAMS_ERROR.getMsg() + ":" + br.getFieldError().getDefaultMessage();
                throw new BaseException(BaseCodeEnum.BASE_PARAMS_ERROR.getCode(), errorMsg);
            }
        }
        paramValid(request, obj, clazz);
    }

    /**
     * 参数校验
     *
     * @param obj
     * @param clazz
     * @param <T>
     */
    public <T> void paramValid(HttpServletRequest request, Object obj, Class<T> clazz) {

    }

    /**
     * 请求日志打印
     *
     * @param params
     */
    public void log(Object params) {
        LogUtils.requestLog(params);
    }
}