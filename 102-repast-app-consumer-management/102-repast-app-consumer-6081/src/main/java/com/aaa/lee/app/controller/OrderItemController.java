package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.OrderItem;
import com.aaa.lee.app.status.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@Api(value = "订单详情信息", tags = "订单详情信息接口")
public class OrderItemController extends BaseController {

    @Autowired
    private IRepastService repastService;

    @GetMapping("/orderItemSelect")
    @ApiOperation(value = "订单详情", notes = "执行订单详情查询操作")
    public ResultData<OrderItem> selectAllOrderItem(String toKen){
        if (toKen != null && !"".equals(toKen)){
            Map<String, Object> stringObjectMap = repastService.selectAllOrderItem(toKen);
            if (stringObjectMap.size()>0){
                return success(stringObjectMap);
            }
        }
        return failed();

    }
}
