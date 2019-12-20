package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.CouponHistory;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CouponHistoryController extends BaseController {

    @Autowired
    private IRepastService couponHistoryService;


    @PostMapping("/getAllCouponHistory")
    public ResultData<CouponHistory> getAllCouponHistory(String token){
        return couponHistoryService.getAllCouponHistory(token);

    }

    @PostMapping("/saveOrUpdateCouponHistory")
    public ResultData<CouponHistory> saveOrUpdateCouponHistory(String token,CouponHistory couponHistory){
        return couponHistoryService.saveOrUpdateCouponHistory(token,couponHistory);

    }

    @PostMapping("/deleteCouponHistory")
    public ResultData<CouponHistory> deleteCouponHistory(String token,Integer id,Integer useState){
        return couponHistoryService.deleteCouponHistory(token,id, useState);
    }

}
