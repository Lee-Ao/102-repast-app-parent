package com.aaa.lee.app.controller;

import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.CouponHistory;
import com.aaa.lee.app.service.CouponHistoryService;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CouponHistoryController extends BaseController {

    @Autowired
    private CouponHistoryService couponHistoryService;


    @PostMapping("/getAllCouponHistory")
    public ResultData<CouponHistory> getAllCouponHistory(@RequestParam("token") String token){
        List<CouponHistory> allCouponHistory = couponHistoryService.getAllCouponHistory(token);
        if (allCouponHistory.size()>0){
            return  success(StatusEnum.EXIST.getCode(),StatusEnum.EXIST.getMsg(),allCouponHistory);
        }else {
            return  failed(StatusEnum.NOT_EXIST.getCode(),StatusEnum.NOT_EXIST.getMsg());
        }
    }

    @PostMapping("/saveOrUpdateCouponHistory")
    public ResultData<CouponHistory> saveOrUpdateCouponHistory(@RequestParam("token") String token,@RequestBody CouponHistory couponHistory){
        Map<String, Object> resultMap = couponHistoryService.saveOrUpdateCouponHistory(couponHistory);
        if (null!=resultMap.get("data")){
            return success((String) resultMap.get("code"),(String) resultMap.get("msg"),resultMap.get("data"));
        }else {
            return failed(StatusEnum.FAILED.getCode(),StatusEnum.FAILED.getMsg());
        }
    }

    @PostMapping("/deleteCouponHistory")
    public ResultData<CouponHistory> deleteCouponHistory(@RequestParam("token") String token,@RequestParam("id") Integer id,@RequestParam("useState") Integer useState){
        Integer result = couponHistoryService.deleteCouponHistoryById(id, useState);
        if (null!=result){
            return success(StatusEnum.DELETE_OPERATION.getCode(),StatusEnum.DELETE_OPERATION.getMsg(),result);
        }else {
            return failed(StatusEnum.FAILED.getCode(),StatusEnum.FAILED.getMsg());
        }
    }

}
