package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.IntegrationChangeHistory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "积分信息",tags = "积分信息接口" )
@RestController
public class IntegrationController extends BaseController {

    @Autowired
    private IRepastService IntegrationService;

    /**
     * 根据token查询该用户的积分变动历史表
     * @param token
     * @return
     */
    @PostMapping("/getAllIntegrationChangeHistoryByToken")
    @ApiOperation(value = "查询积分变动历史表")
    public ResultData getAllIntegrationChangeHistoryByToken(String token){
        return IntegrationService.getAllIntegrationChangeHistoryByToken(token);
    }

    /**
     * 新增积分变动历史表
     * @param integrationChangeHistory
     * @return
     */
    @ApiOperation(value = "新增积分变动历史")
    @PostMapping("/insertIntegrationChangeHistoryByToken")
    public ResultData  insertIntegrationChangeHistoryByToken(IntegrationChangeHistory integrationChangeHistory){
        return IntegrationService.insertIntegrationChangeHistoryByToken(integrationChangeHistory);
    }

    /**
     * 用户消费后对用户积分进行新增操作
     * @param token
     * @return
     */
    @PostMapping("/addMemberIntegration")
    @ApiOperation(value = "用户消费后对用户积分新增")
    public ResultData addMemberIntegration(String token){
        return IntegrationService.addMemberIntegration(token);
    }

}
