package com.aaa.lee.app.api;

import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.fallback.RepastFallback;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.model.CouponHistory;
import com.aaa.lee.app.model.MemberReceiveAddress;
import com.aaa.lee.app.model.IntegrationChangeHistory;
import com.aaa.lee.app.status.StatusEnum;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.*;
import com.aaa.lee.app.vo.CouponHistoryVo;
import com.aaa.lee.app.vo.CouponVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 如果是简单类型传递数据可以传递多个，但是每一个必须要用@RequestParam
 *  *      但是如果是包装类型，只能传递一个，也必须要用@RequestBody
 */
@FeignClient(value = "userinfo-interface-provider", fallbackFactory = RepastFallback.class)
public interface IRepastService {


    /**
     * @author Seven Lee
     * @description
     *      登录熔断数据
     * @param [member]
     * @date 2019/12/19
     * @return java.lang.Boolean
     * @throws
    **/
    @PostMapping("/doLogin")
    Boolean doLogin(@RequestBody Member member);


    /**
     * 根据token查寻当前用户信息接口
     * @param token
     * @return
     */
    @PostMapping("/check")
    ResultData<List<Member>> check(@RequestParam("token") String token);


    /**
     * 根据token查寻个人用户信息接口
     * @param token
     * @return
     */
    @PostMapping("/checkOne")
    Member checkOne(@RequestParam("token") String token);


    /**
     * 修改个人信息接口
     * @param member
     * @param token
     * @return
     */
    @PostMapping("/update")
    ResultData<Member> update(@RequestBody Member member,@RequestParam("token") String token);



    /**
     * 优惠券使用历史操作
     * @param token
     * @return
     */
    /**
     * 获取所有优惠券信息
     * @param token
     * @return
     */
    @PostMapping("/getAllCouponHistory")
    ResultData<CouponHistory> getAllCouponHistory(@RequestParam("token")String token);

    /**
     * 修改
     * @param token
     * @param couponHistory
     * @return
     */
    @PostMapping("/saveOrUpdateCouponHistory")
    ResultData<CouponHistory> saveOrUpdateCouponHistory(@RequestBody CouponHistoryVo couponHistoryVo);

    /**
     * 删除
     * @param token
     * @param id
     * @param useState
     * @return
     */
    @PostMapping("/deleteCouponHistory")
    ResultData<CouponHistory> deleteCouponHistory(@RequestParam("token") String token,@RequestParam("id") Integer id);

    /**
     * 优惠券操作
     */
    /**
     * 查询所有的优惠券
     */
    @PostMapping("/selectAllCoupon")
    ResultData selectAllCoupon(@RequestParam("token") String token);
    /**
     *  商家或者系统发放优惠券时需要调用 进行优惠券的添加
     */
    @PostMapping("/insertCoupon")
    ResultData insertCoupon(@RequestBody CouponVo couponVo);
    /**
     * 使用优惠券数量的操作
     */
    @PostMapping("/useCoupon")
    ResultData useCoupon(@RequestParam("token") String token,@RequestParam("couponId") Integer couponId);
    /**
     * 根据优惠券id查询优惠券
     */
    @PostMapping("/selectCouponById")
    ResultData selectCouponById(@RequestParam("token") String token,@RequestParam("couponId") Integer couponId);

    /**
     * 查询会员收货地址
     * @param token
     * @param id
     * @return
     */
    @PostMapping("/checkAddress")
    ResultData checkAddress(@RequestParam("token")String token);

    /**
     * 新增和修改收货地址
     * @param memberReceiveAddress
     * @return
     */
    @PostMapping("/saveOrUpdateAddress")
    ResultData saveOrUpdateAddress(@RequestBody MemberReceiveAddress memberReceiveAddress);

    /**
     * 删除收货地址
     * @param token
     * @param id
     * @return
     */
    @PostMapping("/deleteAddress")
    ResultData deleteAddress(@RequestParam("token")String token,@RequestParam("id") Long id);

    /**
     * 修改地址状态
     * @param token
     * @param addressId
     * @return
     */
    @PostMapping("/updateAddressStatus")
    ResultData updateAddressStatus(@RequestParam("token")String token,@RequestParam("id") Long id,@RequestParam("memberId")Long memberId);

    /**
     * 根据token查询该用户的积分变动历史表
     * @param token
     * @return
     */
    @PostMapping("/getAllIntegrationChangeHistoryByToken")
    ResultData<IntegrationChangeHistory> getAllIntegrationChangeHistoryByToken(@RequestParam("token") String token);

    /**
     * 新增积分变动历史表
     * @param integrationChangeHistory
     * @return
     */
    @PostMapping("/insertIntegrationChangeHistoryByToken")
    ResultData<IntegrationChangeHistory>  insertIntegrationChangeHistoryByToken(@RequestBody IntegrationChangeHistory integrationChangeHistory);

    /**
     * 用户消费后对用户积分进行新增操作
     * @param token
     * @return
     */
    @PostMapping("addMemberIntegration")
    ResultData addMemberIntegration(@RequestParam("token")String token);


    /**
     * 查询订单信息
     * @param
     * @return
     */
    @PostMapping("/orderItemSelect")
    Map<String,Object> selectAllOrderItem(@RequestParam("toKen") String toKen);

    /**
     * 根据状态查询订单信息
     * @param toKen
     * @param memberId
     * @return
     */
    @PostMapping("/orderStatusSelect")
    Map<String,Object> orderStatusSelect(@RequestParam("toKen") String toKen, @RequestParam("memberId") Long memberId, @RequestParam("status") Long status);


    /**
     * 查询资金明细
     */
    @PostMapping("/selectFund")
    ResultData selectFund(@RequestParam("toKen") String toKen);
}


