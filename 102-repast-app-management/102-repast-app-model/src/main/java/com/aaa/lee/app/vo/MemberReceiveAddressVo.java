package com.aaa.lee.app.vo;

import com.aaa.lee.app.model.MemberReceiveAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class MemberReceiveAddressVo extends MemberReceiveAddress implements Serializable {

    private String token;
    private MemberReceiveAddress memberReceiveAddress;

}
