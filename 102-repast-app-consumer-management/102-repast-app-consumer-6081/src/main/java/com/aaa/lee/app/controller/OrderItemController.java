package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.OrderItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Api(value = "订单信息", tags = "订单信息接口")
public class OrderItemController extends BaseController {

    @Autowired
    private IRepastService repastService;

    @PostMapping("/orderItemSelect")
    @ApiOperation(value = "订单", notes = "执行订单查询操作")
    public ResultData<OrderItem> selectAllOrderItem(String toKen){
            Map<String, Object> stringObjectMap = repastService.selectAllOrderItem(toKen);
            if (stringObjectMap.size()>0){
                return success(stringObjectMap);
            }
        return failed();

    }

    @PostMapping("/orderStatusSelect")
    @ApiOperation(value = "订单查询", notes = "执行根据状态查询订单操作")
    public ResultData orderStatusSelect(String toKen, Long memberId, Long status){
        Map<String, Object> stringObjectMap = repastService.orderStatusSelect(toKen, memberId, status);
        if (stringObjectMap.size()>0){
            return success(stringObjectMap);
        }
        return failed();

    }
}
