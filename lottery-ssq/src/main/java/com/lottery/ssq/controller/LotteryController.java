package com.lottery.ssq.controller;

import com.lottery.common.exception.BaseException;
import com.lottery.common.utils.controller.BaseController;
import com.lottery.ssq.dto.LotteryPredictForm;
import com.lottery.ssq.service.LotteryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Api(description = "彩票")
@RestController
@RequestMapping("/lottery")
public class LotteryController extends BaseController {

    @Autowired
    private LotteryService lotteryService;

    @ApiOperation(value = "预测彩票", notes = "预测彩票", httpMethod = "GET")
    @RequestMapping(value = {"/predict"}, method = {RequestMethod.GET})
    public Object predict(@ModelAttribute @Valid LotteryPredictForm form) throws BaseException {

        return this.success(lotteryService.predict(form));
    }

}
