package com.lottery.ssq.dto;

import com.lottery.common.utils.ExcelUtils;
import lombok.Data;

@Data
public class SslExcelData {

    @ExcelUtils.Header(name = "期号")
    private Integer issue;

    @ExcelUtils.Header(name = "红球号码1")
    private Integer red1;
    @ExcelUtils.Header(name = "红球号码2")
    private Integer red2;
    @ExcelUtils.Header(name = "红球号码3")
    private Integer red3;
    @ExcelUtils.Header(name = "红球号码4")
    private Integer red4;
    @ExcelUtils.Header(name = "红球号码5")
    private Integer red5;
    @ExcelUtils.Header(name = "红球号码6")
    private Integer red6;
    @ExcelUtils.Header(name = "蓝球")
    private Integer blue;

    @ExcelUtils.Header(name = "开奖日期")
    private String lotteryDate;
}
