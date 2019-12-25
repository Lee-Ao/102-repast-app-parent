package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.MemberReceiveAddress;
import com.aaa.lee.app.vo.MemberReceiveAddressVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "用户信息",tags = "用户信息接口")
public class MemberController extends BaseController {
    @Autowired
    private IRepastService iRepastService;

    /**
     * 登录
     * @param member
     * @return
     */
    @PostMapping("/doLogin")
    @ApiOperation(value = "登录",tags = "登录信息接口")
    public ResultData doLogin(Member member){
        if (iRepastService.doLogin(member)){
            //登录成功
            return success();
        }
        return failed();

    }

    /**
     * 根据token查寻当前用户信息
     * @param token
     * @return
     */
    @PostMapping("/check")
    @ApiOperation(value = "查询",tags = "根据token查寻当前用户信息接口")
    public ResultData<List<Member>> check(String token){
        ResultData<List<Member>> check = iRepastService.check(token);
        return check;

    }

    /**
     * 根据token查询单个用户信息
     * @param token
     * @return
     */
    @PostMapping("/checkOne")
    @ApiOperation(value = "查询",tags = "查询单个用户信息接口")
    public Member checkOne(String token){
        Member member = iRepastService.checkOne(token);
        return member;
    }

    /**
     * 修改用户信息
     * @param member
     * @param token
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改",tags = "修改用户信息接口")
    public ResultData<Member> update(Member member,String token){
        ResultData<Member> update = iRepastService.update(member, token);
        return update;
    }

    /**
     * 查看用户收货地址
     * @param
     * @param token
     * @return
     */
    @PostMapping("/checkAddress")
    @ApiOperation(value = "查看",tags = "查看用户收货地址接口")
    public ResultData checkAddress(String token){
        ResultData resultData = iRepastService.checkAddress(token);
        return resultData;
    }

    /**
     * 修改和新增收货地址
     * @param token
     * @param memberReceiveAddress
     * @return
     */
    @PostMapping("/saveOrUpdateAddress")
    @ApiOperation(value = "新增和修改",tags = "新增和修改收货地址")
    public ResultData saveOrUpdateAddress(String token,MemberReceiveAddress memberReceiveAddress){
        MemberReceiveAddressVo memberReceiveAddressVo = new MemberReceiveAddressVo();
        memberReceiveAddressVo.setToken(token);
        memberReceiveAddressVo.setMemberReceiveAddress(memberReceiveAddress);
        ResultData resultData = iRepastService.saveOrUpdateAddress(memberReceiveAddressVo);
        return resultData;
    }

    /**
     * 根据id删除地址
     * @param token
     * @param id
     * @return
     */
    @PostMapping("/deleteAddress")
    @ApiOperation(value = "删除",tags = "删除收货地址")
    public ResultData deleteAddress(String token,Long id){
        ResultData resultData = iRepastService.deleteAddress(token, id);
        return resultData;
    }

    /**
     * 根据id修改地址状态
     * @param token
     * @param id
     * @return
     */
    @PostMapping("/updateAddressStatus")
    @ApiOperation(value = "修改地址状态",tags = "修改地址状态接口")
    public ResultData updateAddressStatus(String token,Long id,Long memberId){
        ResultData resultData = iRepastService.updateAddressStatus(token, id,memberId);
        return resultData;
    }
}
