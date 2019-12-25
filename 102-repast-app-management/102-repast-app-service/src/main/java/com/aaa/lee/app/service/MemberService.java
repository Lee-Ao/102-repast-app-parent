package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.mapper.MemberReceiveAddressMapper;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.MemberReceiveAddress;
import com.aaa.lee.app.status.StatusEnum;
import com.aaa.lee.app.utils.IDUtil;
import com.aaa.lee.app.utils.StringUtil;
import com.aaa.lee.app.vo.MemberReceiveAddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;


import java.util.HashMap;
import java.util.List;



@Service
public class MemberService extends BaseService<Member> {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;
    @Override
    public Mapper<Member> getMapper() {
        return memberMapper;
    }

    /**
     * 执行登录操作
     * @param member
     * @return
     */
    public Boolean doLogin(Member member){
        if(StringUtil.isNotEmpty(member.getOpenId())) {
            try {
                // 随机token
                String token = IDUtil.getUUID() + member.getOpenId();
                member.setToken(token);
                Integer saveResult = super.save(member);
                if(saveResult > 0) {
                    // 说明添加成功
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 根据token查询当前用户信息
     * @return
     */
    public ResultData<List<Member>> check(String token){
        List<Member> members = memberMapper.selectAll();
        ResultData<List<Member>> resultData = new ResultData<List<Member>>();
        if (members.size()>0){
            resultData.setCode(StatusEnum.SUCCESS.getCode());
            resultData.setMsg(StatusEnum.SUCCESS.getMsg());
            resultData.setData(members);
            return resultData;
        }else {
            resultData.setCode(StatusEnum.FAILED.getCode());
            resultData.setMsg(StatusEnum.FAILED.getMsg());
            return null;
        }
    }

    /**
     * 修改个人信息
     * @return
     */
    public int update(Member member, String token){
        int update = memberMapper.updateByPrimaryKey(member);
        return update;
    }

    /**
     * 根据token查询个人信息
     * @param token
     * @return
     */
    public Member checkOne(String token){

      Example example = new Example(Member.class);
      Example.Criteria criteria = example.createCriteria();

      criteria.andEqualTo("token",token);
        Member oneByExample = memberMapper.selectOneByExample(example);
        return oneByExample;

    }

    /**
     * 根据memberId查寻当前用户地址信息
     * @return
     */
        public ResultData checkAddress(String token){
         /*   Example example = new Example(MemberReceiveAddress.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("memberId",16);*/
            ResultData resultData = new ResultData();
            Member member = new Member();
            Member memberOne = memberMapper.selectOne(member.setToken(token));
            List<MemberReceiveAddress> memberReceiveAddresses = memberReceiveAddressMapper.selectcheckAddress(memberOne.getId());

            if (memberReceiveAddresses.size()>0){
                resultData.setCode(StatusEnum.EXIST.getCode());
                resultData.setMsg(StatusEnum.EXIST.getMsg());
                resultData.setData(memberReceiveAddresses);
            }else {
                resultData.setCode(StatusEnum.NOT_EXIST.getCode());
                resultData.setMsg(StatusEnum.NOT_EXIST.getMsg());
            }
            return resultData;
        }

    /**
     * 新增和修改会员收货地址
     * @param memberReceiveAddressVo
     * @return
     */
    public ResultData saveOrUpdateAddress(MemberReceiveAddressVo memberReceiveAddressVo){
        MemberReceiveAddress memberReceiveAddress = memberReceiveAddressVo.getMemberReceiveAddress();
        ResultData resultData = new ResultData();
        if ("".equals(memberReceiveAddress.getId())||null==memberReceiveAddress.getId()){
            int count = memberReceiveAddressMapper.insert(memberReceiveAddress);
            //新增操作
            if (count!=0){
                resultData.setCode(StatusEnum.INSERT_OPERATION.getCode());
                resultData.setMsg(StatusEnum.SUCCESS.getMsg());
                resultData.setData(count);
            }else {

               resultData.setCode(StatusEnum.FAILED.getCode());
               resultData.setMsg(StatusEnum.FAILED.getMsg());

            }

        }else {
            //修改操作
            int count = memberReceiveAddressMapper.updateByPrimaryKey(memberReceiveAddress);
            if (count!=0){
                resultData.setCode(StatusEnum.UPDATE_OPERATION.getCode());
                resultData.setMsg(StatusEnum.SUCCESS.getMsg());
                resultData.setData(count);

            }else {
                resultData.setCode(StatusEnum.UPDATE_OPERATION.getCode());
                resultData.setMsg(StatusEnum.FAILED.getMsg());
            }
        }
        return resultData;
    }

    /**
     * 删除收货地址
     * @param id
     * @param token
     * @return
     */
    public ResultData deleteAddress(Long id,String token){
        ResultData resultData = new ResultData();
        int count = memberReceiveAddressMapper.deleteByPrimaryKey(id);
        if (count==0){
            resultData.setCode(StatusEnum.DELETE_OPERATION.getCode());
            resultData.setMsg(StatusEnum.SUCCESS.getMsg());
            resultData.setData(count);
        }else {
            resultData.setCode(StatusEnum.DELETE_OPERATION.getCode());
            resultData.setMsg(StatusEnum.FAILED.getMsg());
        }
        return resultData;
    }

    /**
     * 修改地址状态
     * 0设置不默认
     * 1设置为默认
     *
     * @param
     * @return
     */
    public ResultData updateAddressStatus(Long memberId,Long id,String token) {
        ResultData resultData = new ResultData();
        if (token != null) {
            if (null != memberId) {
                int i = memberReceiveAddressMapper.updateAddressStatus1(memberId);
                if (i > 0) {
                    int count = memberReceiveAddressMapper.updateAddressStatus(id);
                    if (count == 1) {
                        resultData.setCode(StatusEnum.UPDATE_OPERATION.getCode());
                        resultData.setMsg(StatusEnum.SUCCESS.getMsg());
                        resultData.setData(count);
                    }
                }
            }
        }
        else {
            resultData.setCode(StatusEnum.NOT_EXIST.getCode());
            resultData.setMsg(StatusEnum.FAILED.getMsg());

        }
        return resultData;
    }
}
