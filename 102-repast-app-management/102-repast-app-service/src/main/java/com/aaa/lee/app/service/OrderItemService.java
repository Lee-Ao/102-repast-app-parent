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

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public Mapper<OrderItem> getMapper() {
        return orderItemMapper;
    }

    /**
     * 查询所有订单信息
     *
     * @return
     */
    public Map<String, Object> selectAllOrderItem(String toKen) {
                Map<String, Object> resultMap = new HashMap<>();
                Member member = new Member();
                Member memberOne = memberMapper.selectOne(member.setToken(toKen));
                if (memberOne.getId() != null){
                    List<Order> orders = orderMapper.selectMemberId(memberOne.getId());
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
                            }else{
                                resultMap.put("code",StatusEnum.NOT_EXIST.getCode());
                                resultMap.put("msg",StatusEnum.NOT_EXIST.getMsg());
                            }
                        }
                    }else {
                        resultMap.put("code",StatusEnum.NOT_EXIST.getCode());
                        resultMap.put("msg",StatusEnum.NOT_EXIST.getMsg());
                    }
                }

        return resultMap;
    }

    /**
     * 根据会员id和状态查询订单信息
     * @param memberId
     * @param status
     * @return
     */
    public Map<String,Object> orderStatusSelect(Long memberId,Long status){
        Map<String, Object> resultMap = new HashMap<>();
        List<Order> orders = orderMapper.selectStatus(memberId, status);
        if (orders.size()>0){
            for (Order order: orders
                 ) {
                List<OrderItem> orderItems = orderItemMapper.selectOrderItem(order.getId());
                if (orderItems.size() > 0) {
                    resultMap.put("code", StatusEnum.EXIST.getCode());
                    resultMap.put("msg", StatusEnum.EXIST.getMsg());
                    resultMap.put("data1", orderItems);
                    resultMap.put("data", orders);
                    return resultMap;
                } else {
                    resultMap.put("code", StatusEnum.NOT_EXIST.getCode());
                    resultMap.put("msg", StatusEnum.NOT_EXIST.getMsg());
                }
            }
        }else {
            resultMap.put("code", StatusEnum.NOT_EXIST.getCode());
            resultMap.put("msg", StatusEnum.NOT_EXIST.getMsg());
        }
        return resultMap;
    }
}