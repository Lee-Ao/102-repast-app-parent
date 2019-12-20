package com.aaa.lee.app.fallback;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.CouponHistory;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RepastFallback implements FallbackFactory<IRepastService> {
    @Override
    public IRepastService create(Throwable throwable) {
        IRepastService iRepastService = new IRepastService() {
            @Override
            public ResultData<CouponHistory> getAllCouponHistory(String token) {
                return null;
            }

            @Override
            public ResultData<CouponHistory> saveOrUpdateCouponHistory(CouponHistory couponHistory) {
                return null;
            }

            @Override
            public ResultData<CouponHistory> deleteCouponHistory(Integer id, Integer useState) {
                return null;
            }
        };
        return null;
    }
}
