package com.aaa.lee.app.controller;

import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class OrderItemController extends BaseController {

    @Autowired
    private OrderItemService orderItemService;

    /**
     * 查询所有订单详情信息
     * @return
     */
    @PostMapping("/orderItemSelect")
    public Map<String,Object> selectAllOrderItem(@RequestParam("toKen") String toKen){
        Map<String, Object> resultMap = orderItemService.selectAllOrderItem(toKen);
        if(resultMap.size()>0){
            return resultMap;
        }else{
            return null;
        }
    }

    /**
     * 根据状态查询订单信息
     * @param toKen
     * @param memberId
     * @return
     */
    @PostMapping("/orderStatusSelect")
    public ResultData orderStatusSelect(@RequestParam("toKen") String toKen, @RequestParam("memberId") Long memberId,@RequestParam("status") Long status){
        Map<String, Object> stringObjectMap = orderItemService.orderStatusSelect(memberId, status);
        if (stringObjectMap.size()>0){
            return success(stringObjectMap);
        }
        return failed();

    }
 }

