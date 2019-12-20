package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.CouponHistory;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CouponHistoryMapper extends Mapper<CouponHistory> {

    List<CouponHistory> selectCouponHistoryByMemberId(Integer memberId);

    Integer deleteCouponHistoryById(Integer id,Integer useState);

}