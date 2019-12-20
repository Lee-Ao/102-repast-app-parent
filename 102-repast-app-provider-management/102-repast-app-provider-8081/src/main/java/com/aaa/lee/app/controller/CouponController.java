package com.aaa.lee.app.controller;

import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.service.CouponService;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;

    /**
     * 查询所有的优惠券
     * @return
     */
    @PostMapping("/selectAllCoupon")
    public ResultData<Coupon> selectAllCoupon(@RequestParam("token") String token){
        List<Coupon> coupons = couponService.selectAllCoupon();

        if (coupons.size()>0){
            return success(StatusEnum.EXIST.getCode(),StatusEnum.EXIST.getMsg(),coupons);
        }else {
            return failed(StatusEnum.NOT_EXIST.getCode(),StatusEnum.NOT_EXIST.getMsg());
        }
    }

    /**
     *  商家或者系统发放优惠券时需要调用 进行优惠券的添加
     * @param coupon
     * @return
     */
    @PostMapping("/insertCoupon")
    public ResultData<Coupon> insertCoupon(@RequestParam("token") String token,@RequestBody Coupon coupon){
        Integer result = couponService.insertCoupon(coupon);
        if (null!=result){
            return success(StatusEnum.INSERT_OPERATION.getCode(),StatusEnum.INSERT_OPERATION.getMsg(),result);
        }else {
            return failed(StatusEnum.FAILED.getCode(),StatusEnum.FAILED.getMsg());
        }
    }

    /**
     * 使用优惠券数量的操作
     * @param couponId
     * @return
     */
    @PostMapping("/useCoupon")
    public ResultData<Coupon> useCoupon(@RequestParam("token") String token,@RequestParam("couponId")Integer couponId){
        Integer result = couponService.useCoupon(couponId);
        if (null!=result){
            return success(StatusEnum.SUCCESS.getCode(),StatusEnum.SUCCESS.getMsg(),result);
        }else {
            return failed(StatusEnum.FAILED.getCode(),StatusEnum.FAILED.getMsg());
        }
    }

    /**
     * 根据优惠券id查询优惠券
     * @param couponId
     * @return
     */
    @PostMapping("/selectCouponById")
    public ResultData<Coupon> selectCouponById(@RequestParam("token") String token,@RequestParam("couponId") Integer couponId){
        Coupon coupon = couponService.selectCouponById(couponId);
        if (null!=coupon){
            return success(StatusEnum.EXIST.getCode(),StatusEnum.EXIST.getMsg(),coupon);
        }else {
            return failed(StatusEnum.NOT_EXIST.getCode(),StatusEnum.NOT_EXIST.getMsg());
        }
    }

}
