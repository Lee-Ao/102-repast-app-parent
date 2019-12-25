package com.aaa.lee.app.vo;

import com.aaa.lee.app.model.Order;
import com.aaa.lee.app.model.OrderReturnApply;
import com.aaa.lee.app.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderAndOrderReturnApplyVO  implements Serializable {
    private String toKen;
    private Date payment_time;
    private Double pay_amount;

}
