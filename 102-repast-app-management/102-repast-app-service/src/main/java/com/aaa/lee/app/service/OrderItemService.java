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
import java.util.Map;

@Service
public class OrderItemService extends BaseService<OrderItem> {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private MemberMapper memberMapper;

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
    public Map<String, Object> selectAllOrderItem(String toKen) {
        Map<String, Object> resultMap = new HashMap<>();
        Member member = new Member();
        //根据token获取会员信息
        Member memberOne = memberMapper.selectOne(member.setToken(toKen));
            //判断会员id是否为空
            if (null != memberOne.getId() && !"".equals(memberOne.getId())) {
                //根据会员id查询所有的订单信息
                Order order = orderMapper.selectMemberId(memberOne.getId());
                //判断订单的id是否为空
                if (null != order.getId() && !"".equals(order.getId())) {
                    //如果不为空，根据订单的id查询详情订单
                    OrderItem orderItem = orderItemMapper.selectOrderItem(order.getId());
                    //判断详情订单是否为空
                    if (null != orderItem && !"".equals(orderItem)){
                        resultMap.put("code", StatusEnum.EXIST.getCode());
                        resultMap.put("msg",StatusEnum.EXIST.getMsg());
                        resultMap.put("data1",orderItem);
                        resultMap.put("data",order);
                        return resultMap;
                    }else {
                        resultMap.put("code",StatusEnum.NOT_EXIST.getCode());
                        resultMap.put("msg",StatusEnum.NOT_EXIST.getMsg());
                    }
                }else {
                    resultMap.put("code",StatusEnum.NOT_EXIST.getCode());
                    resultMap.put("msg",StatusEnum.NOT_EXIST.getMsg());
                }
         }else{
                resultMap.put("code",StatusEnum.NOT_EXIST.getCode());
                resultMap.put("msg",StatusEnum.NOT_EXIST.getMsg());
            }
        return resultMap;
    }
}