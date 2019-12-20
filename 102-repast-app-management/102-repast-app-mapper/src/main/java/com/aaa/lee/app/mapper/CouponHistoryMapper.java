package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.CouponHistory;
import tk.mybatis.mapper.common.Mapper;

public interface CouponHistoryMapper extends Mapper<CouponHistory> {

    Integer deleteCouponHistoryById(Integer id,Integer useState);

}