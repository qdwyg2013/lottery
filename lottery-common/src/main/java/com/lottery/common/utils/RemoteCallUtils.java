package com.lottery.common.utils;


import com.alibaba.fastjson.JSONObject;
import com.lottery.common.enums.BaseCodeEnum;
import com.lottery.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author: wyg
 * @Date: 2018/4/4 上午11:07
 * @Description:
 */
@Slf4j
@Component
public class RemoteCallUtils {


    @Autowired
    private RestTemplate restTemplate;

    private HttpEntity<String> getJsonHttpEntity(Object obj) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return new HttpEntity<>(JSONUtils.objToStr(obj), headers);
    }

    /**
     * pos请求
     * 参数为json传输
     *
     * @param url
     * @param param
     * @param isJson 请求是否要求为json格式 true:是；false:否
     * @return
     */
    public String post(String url, Object param, boolean isJson) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        log.info("call the third post service request url={}; params={}", url, JSONUtils.objToStr(param, true));
        String result = null;
        try {
            if (isJson) {
                HttpEntity<String> formEntity = getJsonHttpEntity(param);
                result = restTemplate.postForObject(url, formEntity, String.class);
            } else {
                result = restTemplate.postForObject(UrlUtils.urlParams(url, param), "", String.class);
            }
        } catch (Exception e) {
            log.error("call the third post service is error. e:{}", e);
        }

        stopWatch.stop();
        log.info("call the third post service return data. url={}; timeCost={}; result={}", url, stopWatch.getTotalTimeMillis(), result);

        return result;
    }

    /**
     * post请求
     *
     * @param url
     * @param param
     * @return
     */
    public String postByMap(String url, Map<String, Object> param) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        log.info("call the third post service request url={}; params={}", url, JSONUtils.objToStr(param, true));
        String result = null;
        try {
            result = restTemplate.postForObject(url, param, String.class);
        } catch (Exception e) {
            log.error("call the third post service is error. e:{}", e);
        }

        stopWatch.stop();
        log.info("call the third post service return data. url={}; timeCost={}; result={}", url, stopWatch.getTotalTimeMillis(), result);

        return result;
    }

    /**
     * post 发送 application/x-www-form-urlencoded 或 multipart/form-data
     *
     * @param url
     * @param param
     * @param isFile 是否为文件
     * @return
     */
    public String postByMapFormData(String url, Map<String, Object> param, boolean isFile) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        MultiValueMap<String, Object> mparam = new LinkedMultiValueMap<>();
        param.forEach((k, v) -> {
            if (v == null) {
                mparam.set(k, "");
            } else {
                mparam.set(k, v.toString());
            }
        });

        HttpHeaders headers = new HttpHeaders();
        if (isFile) {
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            log.info("call the third post service request url={}", url);
        } else {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            log.info("call the third post service request url={}; params={}", url, JSONUtils.objToStr(mparam));
        }
        HttpEntity<MultiValueMap<String, Object>> params = new HttpEntity<>(mparam, headers);
        String result = null;
        try {
            result = restTemplate.postForObject(url, params, String.class);
        } catch (Exception e) {
            log.error("call the third post service is error. e:{}", e);
        }

        stopWatch.stop();
        log.info("call the third post service return data. url={}; timeCost={}; result={}", url, stopWatch.getTotalTimeMillis(), result);

        return result;
    }

    /**
     * get请求
     *
     * @param url
     * @param param
     * @return
     */
    public String get(String url, Object param) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        log.info("call the third get service request url={}; params={}", url, JSONUtils.objToStr(param, true));
        String result = null;
        try {
            result = restTemplate.getForObject(UrlUtils.urlParams(url, param), String.class);
        } catch (Exception e) {
            log.error("call the third get service is error. e:{}", e);
        }

        stopWatch.stop();
        log.info("call the third get service return data. url={}; timeCost={}; result={}", url, stopWatch.getTotalTimeMillis(), result);

        return result;
    }

    /**
     * get请求
     *
     * @param url
     * @param param
     * @return
     */
    public String getByMap(String url, Map<String, Object> param) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        log.info("call the third get service request url={}; params={}", url, JSONUtils.objToStr(param, true));
        String result = null;
        try {
            result = restTemplate.getForObject(UrlUtils.urlMapParams(url, param), String.class);
        } catch (Exception e) {
            log.error("call the third get service is error. e:{}", e);
        }

        stopWatch.stop();
        log.info("call the third get service return data. url={}; timeCost={}; result={}", url, stopWatch.getTotalTimeMillis(), result);

        return result;
    }

    /**
     * get请求
     * 参数json串作为占位符处理
     *
     * @param url
     * @param param
     * @return
     */
    public String getByJson(String url, String param) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        log.info("call the third get service request url={}; params={}", url, param);
        String result = null;
        try {
            result = restTemplate.getForObject(url, String.class, param);
        } catch (Exception e) {
            log.error("call the third get service is error. e:{}", e);
        }

        stopWatch.stop();
        log.info("call the third get service return data. url={}; timeCost={}; result={}", url, stopWatch.getTotalTimeMillis(), result);

        return result;
    }

    private String getResult(String result) throws BaseException {

        if (StringUtils.isBlank(result)) {
            throw new BaseException(BaseCodeEnum.THIRD_RETURN_DATA_EMPTY_ERROR.getCode(), BaseCodeEnum.THIRD_RETURN_DATA_EMPTY_ERROR.getMsg());
        }

        JSONObject jsonObj = JSONObject.parseObject(result);
        if (null != jsonObj) {
            Integer code = jsonObj.getInteger("errorCode");
            if (code.equals(0)) {
                String data = JSONUtils.objToStr(jsonObj.get("extra"), true);
                if (StringUtils.isBlank(data) || "{}".equals(data) || "[]".equals(data)) {
                    return null;
                }
                return data;
            }
            String msg = jsonObj.getString("errorMessage");
            throw new BaseException(code, msg);
        }
        throw new BaseException(BaseCodeEnum.THIRD_CALL_EXCEPTION_ERROR.getCode(), BaseCodeEnum.THIRD_CALL_EXCEPTION_ERROR.getMsg());

    }

}
