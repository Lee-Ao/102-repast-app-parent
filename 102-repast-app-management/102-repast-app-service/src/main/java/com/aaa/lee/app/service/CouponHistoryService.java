package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.mapper.CouponHistoryMapper;
import com.aaa.lee.app.mapper.CouponMapper;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.model.CouponHistory;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponHistoryService extends BaseService<CouponHistory> {

    @Autowired
    private CouponHistoryMapper couponHistoryMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private MemberMapper memberMapper;

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
    public List<CouponHistory> getAllCouponHistory(String token){

        Example memberExample = new Example(Member.class);
        Example.Criteria memberCriteria = memberExample.createCriteria();
        memberCriteria.andEqualTo("token", token);

        Member member = memberMapper.selectOneByExample(memberCriteria);
        //根据token查询用户
        return couponHistoryMapper.selectCouponHistoryByMemberId(Integer.valueOf(member.getId().toString()));
    }

    /**
     * 增加或者修改优惠券
     * @param couponHistory
     * @return
     */
    public Map<String,Object> saveOrUpdateCouponHistory(CouponHistory couponHistory){
        Integer result;
        Map<String,Object> resultData=new HashMap<>();
        if (null!=couponHistory.getId()) {
            result = couponHistoryMapper.insert(couponHistory);
            resultData.put("code",StatusEnum.INSERT_OPERATION.getCode());
            resultData.put("msg",StatusEnum.INSERT_OPERATION.getMsg());
            resultData.put("data",result);
        }else {
            result = couponHistoryMapper.updateByPrimaryKey(couponHistory);
            resultData.put("code",StatusEnum.UPDATE_OPERATION.getCode());
            resultData.put("msg",StatusEnum.UPDATE_OPERATION.getMsg());
            resultData.put("data",result);
        }
        return resultData;
    }

    /**
     * 使用优惠券后或者优惠券到期   删除优惠券
     *      useState    使用状态
     * @param id
     * @return
     */
    public Integer deleteCouponHistoryById(Integer id){
        return couponHistoryMapper.deleteCouponHistoryById(id);
    }
    /**
     * 查询所有的订单遍历比较订单的时间是否为可用
     */
    @Scheduled(cron = "0 0 0/24 * * *")
    public void checkAllCouponHistory(){
        List<CouponHistory> couponHistories = couponHistoryMapper.selectAll();
        List<Coupon> coupons = couponMapper.selectAll();
        for (Coupon coupon : coupons) {
            Date endTime = coupon.getEndTime();
            if (new Date().after(endTime)){
                //过期状态
                for (CouponHistory couponHistory:couponHistories) {
                    if (couponHistory.getCouponId()==coupon.getId()){
                        //修改超时优惠券的状态
                        Integer result = couponHistoryMapper.outTimeCouponHistory(Integer.valueOf(couponHistory.getId().toString()));
                    }
                }
            }
        }
    }

}
