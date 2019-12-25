package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Order;
import com.aaa.lee.app.status.LoginStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "资金明细信息", tags = "资金明细信息接口")
public class FundController extends BaseController {

    @Autowired
    private IRepastService repastService;


    @PostMapping("/selectFund")
    @ApiOperation(value = "资金明细", notes = "执行资金明细查询操作")
    public ResultData<Order> selectFund(String toKen){
        if (toKen != null){
            ResultData<Order> orderResultData = repastService.selectFund(toKen);
            if (orderResultData != null){
                return success(orderResultData);
            }
        }
        return failed();
    }
}
