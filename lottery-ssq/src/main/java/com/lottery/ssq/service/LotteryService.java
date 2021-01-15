package com.lottery.ssq.service;

import com.lottery.ssq.dto.LotteryPredictForm;

public interface LotteryService {

    /**
     * 预测号码
     */
    String predict(LotteryPredictForm form);

}
