package com.lottery.common.utils.http;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@Component
public class HttpExecutor {
    @Resource(name = "commonRestTemplate")
    private RestTemplate commonRestTemplate;

    public <T> T executor(Task<T> task, Retryer retryer, Retryer.RetryMeta retryMeta) throws Exception {
        RetryException exception = new RetryException();

        T result = null;
        while (true) {
            long startTime = System.currentTimeMillis();
            try {
                exception.clear();

                result = task.excutor(commonRestTemplate);

                exception.setResult(result);
            } catch (Exception e) {
                exception.setException(e);
            } finally {
                log.info("result:{}, exception: {}, cst: {}", JSON.toJSONString(result), exception.getException(),
                        System.currentTimeMillis() - startTime);
                if (null != retryer && retryer.continueOrEnd(retryer, exception, retryMeta)) {
                    log.info("retry.......");
                    continue;
                } else {
                    log.info("jump out of retry...");
                    if (null != exception.getResult()) {
                        return result;
                    } else {
                        throw exception.getException();
                    }
                }
            }
        }
    }
}
