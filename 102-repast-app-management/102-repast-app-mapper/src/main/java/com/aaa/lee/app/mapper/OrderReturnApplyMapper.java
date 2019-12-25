package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.OrderReturnApply;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderReturnApplyMapper extends Mapper<OrderReturnApply> {

    List<OrderReturnApply> selectFund(Long orderId);
}