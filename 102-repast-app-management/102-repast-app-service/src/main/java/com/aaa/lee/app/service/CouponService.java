package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.mapper.CouponMapper;
import com.aaa.lee.app.model.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class CouponService extends BaseService<Coupon> {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public Mapper<Coupon> getMapper() {
        return couponMapper;
    }

    /**
     * 查询所有的优惠券
     * @return
     */
    public List<Coupon> selectAllCoupon(){
        return couponMapper.selectAll();
    }

    /**
     * 店家或者系统发放优惠券时需要添加到优惠券表
     * @param coupon
     * @return
     */
    public Integer insertCoupon(Coupon coupon){
        return couponMapper.insert(coupon);
    }

    /**
     * //当用户使用的时候会调用该方法
     * @return
     */
    public Integer useCoupon(Integer couponId){
        Coupon coupon = this.selectCouponById(couponId);
        if (coupon.getCount()>coupon.getUseCount()){
            coupon.setUseCount(coupon.getCount()+1);
            return couponMapper.updateByPrimaryKey(coupon);
        }else {
            return null;
        }
    }

    /**
     * 根据优惠券id查询优惠券
     *
     */
    public Coupon selectCouponById(Integer couponId){
        return couponMapper.selectByPrimaryKey(couponId);
    }

}
