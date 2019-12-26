package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.mapper.IntegrationChangeHistoryMapper;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.model.IntegrationChangeHistory;
import com.aaa.lee.app.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 对积分历史表进行操作
 *   积分历史只能进行新增和查询 不能删除和修改
 */
@Service
public class IntegrationService extends BaseService<IntegrationChangeHistory> {

    @Autowired
    private IntegrationChangeHistoryMapper integrationChangeHistoryMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Mapper<IntegrationChangeHistory> getMapper() {
        return integrationChangeHistoryMapper;
    }

    /**
     * 根据传入的token来查询所有该用户的积分变化
     * @param token
     * @return
     */
    public List<IntegrationChangeHistory>  getAllIntegrationChangeHistoryByToken(String token){
        //根据token查询用户id
        //再根据用户id来查询该用户的积分变化历史
        Example memberExample = new Example(Member.class);
        Example.Criteria memberCriteria = memberExample.createCriteria();
        memberCriteria.andEqualTo("token", token);

        Member member = memberMapper.selectOneByExample(memberExample);

        if (member==null){
            return null;
        }

        Example example = new Example(IntegrationChangeHistory.class);
        Example.Criteria criteria = example.createCriteria();
        //条件member_id=1
        Long memberId = member.getId();
        criteria.andEqualTo("memberId",memberId);

        List<IntegrationChangeHistory> integrationChangeHistories = integrationChangeHistoryMapper.selectByExample(example);

        return integrationChangeHistories;
    }

    /**
     * 对积分历史表进行操作
     * @param integrationChangeHistory
     * @return
     */
    public Integer insertIntegrationChangeHistory(IntegrationChangeHistory integrationChangeHistory){
        return integrationChangeHistoryMapper.insert(integrationChangeHistory);
    }

    /**
     * 对用户的积分进行新增操作
     * @param token
     * @return
     */
    public Integer addMemberIntegration(String token){
        Example memberExample = new Example(Member.class);
        Example.Criteria memberCriteria = memberExample.createCriteria();
        memberCriteria.andEqualTo("token", token);

        Member member = memberMapper.selectOneByExample(memberExample);
        //每次订单执行该方法添加五十积分
        member.setIntegration(member.getIntegration()+50);

        int updateResult = memberMapper.updateByPrimaryKey(member);

        return updateResult;
    }

}
