package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 根据会员id和状态查询订单信息
     */
    List<Order> selectStatus(@Param("memberId" ) Long memberId, @Param("status") Long status);
}