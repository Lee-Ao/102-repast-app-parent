package com.aaa.lee.app.fallback;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.*;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import java.util.Map;

@Component
public class RepastFallback implements FallbackFactory<IRepastService> {
    @Override
    public IRepastService create(Throwable throwable) {
        IRepastService iRepastService = new IRepastService() {
            @Override
            public Boolean doLogin(Member member) {
                return null;
            }

            @Override
            public ResultData<List<Member>> check(String token) {
                return null;
            }

            @Override
            public Member checkOne(String token) {
                return null;
            }

            @Override
            public ResultData<Member> update(Member member, String token) {
                return null;
            }

            @Override
            public ResultData<CouponHistory> getAllCouponHistory(String token) {
                return null;
            }

            @Override
            public ResultData<CouponHistory> saveOrUpdateCouponHistory(String token, CouponHistory couponHistory) {
                return null;
            }

            @Override
            public ResultData<CouponHistory> deleteCouponHistory(String token, Integer id, Integer useState) {
                return null;
            }

            @Override
            public ResultData<Coupon> selectAllCoupon(String token) {
                return null;
            }

            @Override
            public ResultData<Coupon> insertCoupon(String token, Coupon coupon) {
                return null;
            }

            @Override
            public ResultData<Coupon> useCoupon(String token, Integer couponId) {
                return null;
            }

            @Override
            public ResultData<Coupon> selectCouponById(String token, Integer couponId) {
            public ResultData<CouponHistory> saveOrUpdateCouponHistory(String token, CouponHistory couponHistory) {
                return null;
            }

            @Override
            public ResultData<CouponHistory> deleteCouponHistory(String token, Integer id, Integer useState) {
                return null;
            }

            @Override
            public ResultData<Coupon> selectAllCoupon(String token) {
                return null;
            }

            @Override
            public ResultData<Coupon> insertCoupon(String token, Coupon coupon) {
                return null;
            }

            @Override
            public ResultData<Coupon> useCoupon(String token, Integer couponId) {
                return null;
            }

            @Override
            public ResultData<Coupon> selectCouponById(String token, Integer couponId) {
                return null;
            }

            @Override
            public ResultData checkAddress(String token) {
                return null;
            }


            @Override
            public ResultData saveOrUpdateAddress(MemberReceiveAddress memberReceiveAddress) {
                return null;
            }

            @Override
            public Map<String,Object> selectAllOrderItem(String toKen) {
                return null;
            }

            @Override
            public Map<String,Object> orderStatusSelect(String toKen, Long memberId, Long status) {
                return null;
            }

            @Override
            public ResultData selectFund(String toKen) {
            public ResultData deleteAddress(String token, Long id) {
                return null;
            }

            @Override
            public ResultData updateAddressStatus(String token, Long id, Long memberId) {
                return null;
            }


        };
        return null;
    }
}
