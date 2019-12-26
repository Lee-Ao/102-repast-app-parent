package com.aaa.lee.app.controller;


import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.IntegrationChangeHistory;
import com.aaa.lee.app.service.IntegrationService;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IntegrationController extends BaseController {

    @Autowired
    private IntegrationService integrationService;

    /**
     * 根据token查询该用户的积分变动历史表
     * @param token
     * @return
     */
        @PostMapping("/getAllIntegrationChangeHistoryByToken")
        public ResultData getAllIntegrationChangeHistoryByToken(@RequestParam("token") String token){
            List<IntegrationChangeHistory> integrationChangeHistories = integrationService.getAllIntegrationChangeHistoryByToken(token);
            if (integrationChangeHistories.size()>0){
                return super.success(StatusEnum.EXIST.getCode(),StatusEnum.EXIST.getMsg(),integrationChangeHistories);
            }else {
                return super.failed(StatusEnum.NOT_EXIST.getCode(),StatusEnum.NOT_EXIST.getMsg());
            }
        }

        /**
         * 新增积分变动历史表
         * @param integrationChangeHistory
         * @return
         */
        @PostMapping("/insertIntegrationChangeHistoryByToken")
        public ResultData  insertIntegrationChangeHistoryByToken(@RequestBody IntegrationChangeHistory integrationChangeHistory){
            Integer integer = integrationService.insertIntegrationChangeHistory(integrationChangeHistory);
            if (null!=integer){
                return super.success(StatusEnum.INSERT_OPERATION.getCode(),StatusEnum.INSERT_OPERATION.getMsg(),integer);
            }else {
                return super.failed(StatusEnum.FAILED.getCode(),StatusEnum.FAILED.getMsg());
            }
        }

        /**
         * 用户消费后对用户积分进行新增操作
         * @param token
         * @return
         */
        @PostMapping("addMemberIntegration")
        public ResultData addMemberIntegration(@RequestParam("token")String token){
        Integer integer = integrationService.addMemberIntegration(token);

        if (null!=integer){
            return super.success(StatusEnum.UPDATE_OPERATION.getCode(),StatusEnum.UPDATE_OPERATION.getMsg(),integer);
        }else {
            return super.failed(StatusEnum.FAILED.getCode(),StatusEnum.FAILED.getMsg());
        }
    }

}
