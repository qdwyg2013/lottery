package com.lottery.ssq.service.impl;

import com.lottery.common.utils.ExcelUtils;
import com.lottery.ssq.dto.LotteryPredictForm;
import com.lottery.ssq.dto.SslExcelData;
import com.lottery.ssq.remote.RemoteService;
import com.lottery.ssq.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    private RemoteService remoteService;

    @Override
    public String predict(LotteryPredictForm form) {

        // 1.从excel读取历史数据
        List<SslExcelData> dataList;
        try {
            String filePath = "D:\\project\\caipiao\\lottery\\lottery-ssq\\src\\main\\resources\\ssq.xlsx";
            dataList = ExcelUtils.loadFromExcel(filePath, SslExcelData.class);
        } catch (Exception e) {
            log.error("load the excel is error. e:{}", e);
            return null;
        }

        if (CollectionUtils.isEmpty(dataList)) {
            log.error("the ssq data is empty.");
            return null;
        }

        // 2.按照各种规则计算


        // 3.对各种规则进行加权计算


        return "aabb";
    }

}
