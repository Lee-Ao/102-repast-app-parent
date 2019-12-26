package com.aaa.lee.app.fallback;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.*;
import com.aaa.lee.app.vo.CouponHistoryVo;
import com.aaa.lee.app.vo.CouponVo;
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
            public ResultData<CouponHistory> saveOrUpdateCouponHistory(CouponHistoryVo couponHistoryVo) {
                return null;
            }

            @Override
            public ResultData<CouponHistory> deleteCouponHistory(String token, Integer id) {
                return null;
            }


            @Override
            public ResultData selectAllCoupon(String token) {
                return null;
            }

            @Override
            public ResultData<Coupon> insertCoupon(CouponVo couponVo) {
                return null;
            }

            @Override
            public ResultData<Coupon> useCoupon(String token, Integer couponId) {
                return null;
            }

            @Override
            public ResultData selectCouponById(String token, Integer couponId) {
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
            public ResultData deleteAddress(String token, Long id) {
                return null;
            }

            @Override
            public ResultData updateAddressStatus(String token, Long id, Long memberId) {
                return null;
            }

            @Override
            public ResultData<IntegrationChangeHistory> getAllIntegrationChangeHistoryByToken(String token) {
                return null;
            }

            @Override
            public ResultData<IntegrationChangeHistory> insertIntegrationChangeHistoryByToken(IntegrationChangeHistory integrationChangeHistory) {
                return null;
            }

            @Override
            public ResultData addMemberIntegration(String token) {
                return null;
            }

            @Override
            public Map<String, Object> selectAllOrderItem(String toKen) {
                return null;
            }

            @Override
            public Map<String, Object> orderStatusSelect(String toKen, Long memberId, Long status) {
                return null;
            }

            @Override
            public ResultData selectFund(String toKen) {
                return null;
            }


        };
        return null;
    }
}
