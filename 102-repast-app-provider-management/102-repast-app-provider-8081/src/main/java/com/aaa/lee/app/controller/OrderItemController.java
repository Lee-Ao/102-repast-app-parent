package com.aaa.lee.app.controller;

import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.OrderItem;
import com.aaa.lee.app.service.OrderItemService;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class OrderItemController extends BaseController {

    @Autowired
    private OrderItemService orderItemService;

    /**
     * 查询所有订单详情信息
     * @return
     */
    @GetMapping("/orderItemSelect")
    public Map<String,Object> selectAllOrderItem(@RequestParam("token")String toKen){
        Map<String, Object> resultMap = orderItemService.selectAllOrderItem(toKen);
        if(resultMap.size()>0){
            return resultMap;
        }else{
            return null;
        }
    }
 }

