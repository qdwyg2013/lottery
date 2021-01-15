package com.lottery.ssq.remote;

import com.alibaba.fastjson.JSONObject;
import com.lottery.common.utils.RemoteCallUtils;
import com.lottery.ssq.conf.Conf;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class RemoteService {

    @Autowired
    private Conf conf;

    @Autowired
    private RemoteCallUtils remoteCallUtils;

    public String getLotteryHistoryData(Integer start, Integer end) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("end", end);

        String response = remoteCallUtils.getByMap(conf.getLotteryHistoryDataUrl(), params);

        if (StringUtils.isNotBlank(response)) {
            return null;
        }

        log.error("remote the getLotteryHistoryData return data is error. response:{}", response);
        return null;
    }
}
