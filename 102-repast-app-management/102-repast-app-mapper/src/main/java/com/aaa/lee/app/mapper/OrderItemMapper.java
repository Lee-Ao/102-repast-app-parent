package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.OrderItem;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;

@Resource
public interface OrderItemMapper extends Mapper<OrderItem> {

    OrderItem selectOrderItem(Long orderId);
}