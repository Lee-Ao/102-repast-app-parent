package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.vo.CouponVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "优惠券", tags = "优惠券接口")
public class CouponController {

    @Autowired
    private IRepastService couponService;

    /**
     * 查询所有优惠券
     * @param token
     * @return
     */
    @PostMapping("/selectAllCoupon")
    @ApiOperation(value = "查询所有的优惠券")
    public ResultData selectAllCoupon(String token){
        return couponService.selectAllCoupon(token);
    }
    /**
     *  商家或者系统发放优惠券时需要调用 进行优惠券的添加
     */
    @PostMapping("/insertCoupon")
    @ApiOperation(value = "优惠券的添加")
    public ResultData insertCoupon(String token,Coupon coupon){
        CouponVo couponVo = new CouponVo();
        couponVo.setToken(token);
        couponVo.setCoupon(coupon);
        return couponService.insertCoupon(couponVo);
    }
    /**
     * 使用优惠券数量的操作
     */
    @PostMapping("/useCoupon")
    @ApiOperation(value = "使用优惠券，优惠券数量减少")
    public ResultData useCoupon(String token,Integer couponId){
        return couponService.useCoupon(token,couponId);
    }
    /**
     * 根据优惠券id查询优惠券
     */
    @PostMapping("/selectCouponById")
    @ApiOperation(value = "根据id查询优惠券")
    public ResultData selectCouponById(String token,Integer couponId){
        return couponService.selectCouponById(token,couponId);
    }

}
