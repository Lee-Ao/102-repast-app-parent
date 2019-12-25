package com.aaa.lee.app.controller;

import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;

import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.MemberReceiveAddress;
import com.aaa.lee.app.service.MemberService;
import com.aaa.lee.app.vo.MemberReceiveAddressVo;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class MemberController extends BaseController {
    @Autowired
    private MemberService memberService;

    /**
     * 执行登录操作
     * @param member
     * @return
     */
    @PostMapping("/doLogin")
    public Boolean doLogin(@RequestBody Member member){
        //调用service
       return memberService.doLogin(member);
    }

    /**
     * 根据token查寻当前用户信息
     * @param token
     * @return
     */
    @PostMapping("/check")
    public ResultData<List<Member>> check(@RequestParam("token")String token){
        //调用service
        return memberService.check(token);

    }

    /**
     * 修改用户信息
     * @param member
     * @param token
     * @return
     */
    @PostMapping("/update")
    public ResultData<Member> update(@RequestBody Member member,@RequestParam("token")String token){
        int update = memberService.update(member, token);
        if (update!=0){
            return success();
        }
        return failed();
    }

    /**
     * 根据token查询个人信息
     * @param token
     * @return
     */
    @PostMapping("/checkOne")
    public Member checkOne(@RequestParam("token")String token){
        //调用service
       return memberService.checkOne(token);

    }

    /**
     *根据id查询用户收货地址
     * @param token
     * @return
     */
    @PostMapping("/checkAddress")
    public ResultData checkAddress(@RequestParam("token")String token){

        //调用service
        return memberService.checkAddress(token);
    }

    /**
     * 新增和修改收货地址操作
     * @param
     * @param memberReceiveAddress
     * @return
     */
    @PostMapping("/saveOrUpdateAddress")
    public ResultData saveOrUpdateAddress(@RequestBody MemberReceiveAddressVo memberReceiveAddress){

        //调用service
        return memberService.saveOrUpdateAddress(memberReceiveAddress);
    }


    /**
     * 删除收货地址
     * @param token
     * @param id
     * @return
     */
    @PostMapping("/deleteAddress")
    public ResultData deleteAddress(@RequestParam("token")String token,@RequestParam("id") Long id){
        //调用service
        return memberService.deleteAddress(id,token);
    }

    /**
     * 修改地址状态
     * 0设置不默认
     * 1设置为默认
     * @param token
     * @param memberId
     * @return
     */
    @PostMapping("/updateAddressStatus")
    public ResultData updateAddressStatus(@RequestParam("token")String token,@RequestParam("id") Long id,@RequestParam("memberId")Long memberId){
        //调用service
        ResultData resultData = memberService.updateAddressStatus(memberId, id, token);

        return super.success(resultData.getCode(),resultData.getMsg(),resultData.getData());
    }
}
