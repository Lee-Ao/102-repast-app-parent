package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.CouponHistory;
import com.aaa.lee.app.status.StatusEnum;
import com.aaa.lee.app.vo.CouponHistoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "优惠券历史", tags = "优惠券历史接口")
public class CouponHistoryController extends BaseController {

    @Autowired
    private IRepastService couponHistoryService;

    /**
     * 获取所有优惠券历史
     * @param token
     * @return
     */
    @PostMapping("/getAllCouponHistory")
    @ApiOperation(value = "查询所有该用户的优惠券")
    public ResultData<CouponHistory> getAllCouponHistory(String token){
        return couponHistoryService.getAllCouponHistory(token);

    }

    /**
     * 保存或修改优惠券历史
     * @param token
     * @param couponHistory
     * @return
     */
    @PostMapping("/saveOrUpdateCouponHistory")
    @ApiOperation(value = "新增或者修改优惠券历史")
    public ResultData<CouponHistory> saveOrUpdateCouponHistory(String token,CouponHistory couponHistory){

        CouponHistoryVo couponHistoryVo = new CouponHistoryVo();
        couponHistoryVo.setToken(token);
        couponHistoryVo.setCouponHistory(couponHistory);

        return couponHistoryService.saveOrUpdateCouponHistory(couponHistoryVo);

    }

    /**
     * 删除优惠券历史
     * @param token
     * @param id
     * @return
     */
    @PostMapping("/deleteCouponHistory")
    @ApiOperation(value = "使用优惠券或者优惠券过期")
    public ResultData<CouponHistory> deleteCouponHistory(String token,Integer id){
        return couponHistoryService.deleteCouponHistory(token,id);
    }

}
