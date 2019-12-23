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
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Api(value = "订单信息", tags = "订单信息接口")
public class OrderItemController extends BaseController {

    @Autowired
    private IRepastService repastService;

    @PostMapping("/orderItemSelect")
    @ApiOperation(value = "订单", notes = "执行订单查询操作")
    public ResultData<OrderItem> selectAllOrderItem(String toKen,Long memberId){
            Map<String, Object> stringObjectMap = repastService.selectAllOrderItem(toKen,memberId);
            if (stringObjectMap.size()>0){
                return success(stringObjectMap);
            }
        return failed();

    }
}
