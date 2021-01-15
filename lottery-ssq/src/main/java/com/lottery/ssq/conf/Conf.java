package com.lottery.ssq.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource({"classpath:conf.properties"})
public class Conf {

    @Value("${lottery.history.data.url}")
    private String lotteryHistoryDataUrl;

}
