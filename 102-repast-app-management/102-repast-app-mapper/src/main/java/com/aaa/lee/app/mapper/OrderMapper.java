package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.Order;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface OrderMapper extends Mapper<Order> {
    /**
     * 根据会员id查询订单部分信息
     * @param memberId
     * @return
     */
    List<Order> selectMemberId(Long memberId);
}