package com.aaa.lee.app.api;

import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.fallback.RepastFallback;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.model.CouponHistory;
import com.aaa.lee.app.model.OrderItem;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 如果是简单类型传递数据可以传递多个，但是每一个必须要用@RequestParam
 *  *      但是如果是包装类型，只能传递一个，也必须要用@RequestBody
 */
@FeignClient(value = "userinfo-interface-provider", fallbackFactory = RepastFallback.class)
public interface IRepastService {

    /**
     * 优惠券使用历史操作
     */
    @PostMapping("/getAllCouponHistory")
    ResultData<CouponHistory> getAllCouponHistory(@RequestParam("token")String token);

    @PostMapping("/saveOrUpdateCouponHistory")
    ResultData<CouponHistory> saveOrUpdateCouponHistory(@RequestParam("token") String token,@RequestBody CouponHistory couponHistory);

    @PostMapping("/deleteCouponHistory")
    ResultData<CouponHistory> deleteCouponHistory(@RequestParam("token") String token,@RequestParam("id") Integer id,@RequestParam("useState") Integer useState );

    /**
     * 优惠券操作
     */
    /**
     * 查询所有的优惠券
     */
    @PostMapping("/selectAllCoupon")
    public ResultData<Coupon> selectAllCoupon(@RequestParam("token") String token);
    /**
     *  商家或者系统发放优惠券时需要调用 进行优惠券的添加
     */
    @PostMapping("/insertCoupon")
    ResultData<Coupon> insertCoupon(@RequestParam("token") String token,@RequestBody Coupon coupon);
    /**
     * 使用优惠券数量的操作
     */
    @PostMapping("/useCoupon")
    ResultData<Coupon> useCoupon(@RequestParam("token") String token,@RequestParam("couponId") Integer couponId);
    /**
     * 根据优惠券id查询优惠券
     */
    @PostMapping("/selectCouponById")
    ResultData<Coupon> selectCouponById(@RequestParam("token") String token,@RequestParam("couponId") Integer couponId);

    /**
     * 查询订单信息
     * @param toKen
     * @return
     */
    @GetMapping("/orderItemSelect")
    Map<String,Object> selectAllOrderItem(@RequestParam("token") String toKen);
}
