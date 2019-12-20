package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.mapper.CouponHistoryMapper;
import com.aaa.lee.app.mapper.CouponMapper;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.model.CouponHistory;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class CouponHistoryService extends BaseService<CouponHistory> {

    @Autowired
    private CouponHistoryMapper couponHistoryMapper;

    @Override
    public Mapper<CouponHistory> getMapper() {
        return couponHistoryMapper;
    }

    /**
     * 查询所有优惠券的
     *   查询所有用户可用的优惠券
     *   根据用户id查询所有优惠券
     * @return
     */
    public List<CouponHistory> getAllCouponHistory(){
        return couponHistoryMapper.selectAll();
    }

    /**
     * 增加或者修改优惠券
     * @param couponHistory
     * @return
     */
    public Integer saveOrUpdateCouponHistory(CouponHistory couponHistory){
        Integer result;

        if (null!=couponHistory.getId()) {
            result = couponHistoryMapper.insert(couponHistory);
        }else {
            result = couponHistoryMapper.updateByPrimaryKey(couponHistory);
        }
        return result;
    }

    /**
     * 使用优惠券后或者优惠券到期   删除优惠券
     *      useState    使用状态
     * @param id
     * @return
     */
    public Integer deleteCouponHistoryById(Integer id,Integer useState){
        return couponHistoryMapper.deleteCouponHistoryById(id,useState);
    }

}
