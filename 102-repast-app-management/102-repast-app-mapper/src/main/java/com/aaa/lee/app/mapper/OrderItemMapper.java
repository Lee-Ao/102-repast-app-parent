package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.OrderItem;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface OrderItemMapper extends Mapper<OrderItem> {

    List<OrderItem> selectOrderItem(Long orderId);
}