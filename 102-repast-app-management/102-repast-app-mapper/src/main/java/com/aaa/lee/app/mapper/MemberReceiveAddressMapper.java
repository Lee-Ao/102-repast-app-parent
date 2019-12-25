package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.MemberReceiveAddress;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MemberReceiveAddressMapper extends Mapper<MemberReceiveAddress> {
    int updateAddressStatus(Long id);
    int updateAddressStatus1(Long memberId);
    List<MemberReceiveAddress> selectcheckAddress(Long memberId);

}