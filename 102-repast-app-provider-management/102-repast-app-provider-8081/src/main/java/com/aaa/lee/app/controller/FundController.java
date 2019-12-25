package com.aaa.lee.app.controller;

import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Order;
import com.aaa.lee.app.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FundController extends BaseController {

    @Autowired
    private FundService fundService;

    @PostMapping("/selectFund")
    public ResultData<Order> selectFund(String toKen){
        ResultData<Order> orderResultData = fundService.selectFund(toKen);
        if (orderResultData != null){
            return success(orderResultData);
        }
        return failed();
    }
}
