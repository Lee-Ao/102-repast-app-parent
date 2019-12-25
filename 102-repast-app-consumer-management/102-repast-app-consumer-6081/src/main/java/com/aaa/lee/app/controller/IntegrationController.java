package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.IntegrationChangeHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    ResultData<IntegrationChangeHistory> getAllIntegrationChangeHistoryByToken(String token){
        return IntegrationService.getAllIntegrationChangeHistoryByToken(token);
    }

    /**
     * 新增积分变动历史表
     * @param integrationChangeHistory
     * @return
     */
    @PostMapping("/insertIntegrationChangeHistoryByToken")
    ResultData<IntegrationChangeHistory>  insertIntegrationChangeHistoryByToken(IntegrationChangeHistory integrationChangeHistory){
        return IntegrationService.insertIntegrationChangeHistoryByToken(integrationChangeHistory);
    }

    /**
     * 用户消费后对用户积分进行新增操作
     * @param token
     * @return
     */
    @PostMapping("/addMemberIntegration")
    ResultData addMemberIntegration(String token){
        return IntegrationService.addMemberIntegration(token);
    }

}
