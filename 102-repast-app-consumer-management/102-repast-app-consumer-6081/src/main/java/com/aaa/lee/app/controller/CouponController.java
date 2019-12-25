package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

    @Autowired
    private IRepastService couponService;


    @PostMapping("/selectAllCoupon")
    public ResultData<Coupon> selectAllCoupon(String token){
        return couponService.selectAllCoupon(token);
    };
    /**
     *  商家或者系统发放优惠券时需要调用 进行优惠券的添加
     */
    @PostMapping("/insertCoupon")
    public ResultData<Coupon> insertCoupon(String token,Coupon coupon){
        return couponService.insertCoupon(token,coupon);
    };
    /**
     * 使用优惠券数量的操作
     */
    @PostMapping("/useCoupon")
    public ResultData<Coupon> useCoupon(String token,Integer couponId){
        return couponService.useCoupon(token,couponId);
    };
    /**
     * 根据优惠券id查询优惠券
     */
    @PostMapping("/selectCouponById")
    public ResultData<Coupon> selectCouponById(String token,Integer couponId){
        return couponService.selectCouponById(token,couponId);
    };

}
