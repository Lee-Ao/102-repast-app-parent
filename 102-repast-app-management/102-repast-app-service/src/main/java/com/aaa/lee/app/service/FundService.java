package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.mapper.OrderMapper;
import com.aaa.lee.app.mapper.OrderReturnApplyMapper;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.Order;
import com.aaa.lee.app.model.OrderReturnApply;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

@Service
public class FundService extends BaseService<Member> {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderReturnApplyMapper orderReturnApplyMapper;

    @Override
    public Mapper<Member> getMapper() {
        return memberMapper;
    }


    /**
     * 查询资金明细信息
     * @param toKen
     * @return
     */
    public ResultData selectFund(String toKen){

        ResultData orderResultData = new ResultData();
        Member member = new Member();
        Member memberOne = memberMapper.selectOne(member.setToken(toKen));
        if (null != memberOne.getId() && !"".equals(memberOne.getId())){
            List<Order> orders = orderMapper.selectMemberId(memberOne.getId());
            if (orders.size()>0){
                for (Order order: orders) {
                    List<OrderReturnApply> orderReturnApplies = orderReturnApplyMapper.selectFund(order.getId());
                    if (orderReturnApplies.size()>0){
                        orderResultData.setCode(StatusEnum.EXIST.getCode());
                        orderResultData.setMsg(StatusEnum.EXIST.getMsg());
                        orderResultData.setData(orders);
                        orderResultData.setData(orderReturnApplies);
                    }else {
                        orderResultData.setCode(StatusEnum.NOT_EXIST.getCode());
                        orderResultData.setMsg(StatusEnum.NOT_EXIST.getMsg());
                    }
                }
            }else {
                orderResultData.setCode(StatusEnum.NOT_EXIST.getCode());
                orderResultData.setMsg(StatusEnum.NOT_EXIST.getMsg());
            }
        }else {
            orderResultData.setCode(StatusEnum.NOT_EXIST.getCode());
            orderResultData.setMsg(StatusEnum.NOT_EXIST.getMsg());
        }
        return orderResultData;
    }
}
