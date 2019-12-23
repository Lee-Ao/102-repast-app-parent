package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.mapper.OrderItemMapper;
import com.aaa.lee.app.mapper.OrderMapper;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.Order;
import com.aaa.lee.app.model.OrderItem;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderItemService extends BaseService<OrderItem> {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public Mapper<OrderItem> getMapper() {
        return orderItemMapper;
    }

    /**
     * 查询所有订单信息
     *
     * @return
     */
    public Map<String, Object> selectAllOrderItem(Long memberId) {
                Map<String, Object> resultMap = new HashMap<>();
                List<Order> orders = orderMapper.selectMemberId(memberId);
                if (orders.size()>0) {
                    for (Order order: orders) {
                        Long orderId = order.getId();
                        List<OrderItem> orderItems = orderItemMapper.selectOrderItem(orderId);
                        if (orderItems.size()>0){
                            resultMap.put("code", StatusEnum.EXIST.getCode());
                            resultMap.put("msg",StatusEnum.EXIST.getMsg());
                            resultMap.put("data1",orderItems);
                            resultMap.put("data",orders);
                            return resultMap;
                        }
                    }
                }
        return null;
    }
}