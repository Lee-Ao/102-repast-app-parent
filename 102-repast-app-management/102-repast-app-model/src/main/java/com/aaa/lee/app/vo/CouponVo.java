package com.aaa.lee.app.vo;

import com.aaa.lee.app.model.Coupon;
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
public class CouponVo extends Coupon implements Serializable {

    private String  token;

    private Coupon coupon;
}
