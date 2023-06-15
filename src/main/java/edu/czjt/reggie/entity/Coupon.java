package edu.czjt.reggie.entity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 优惠券信息
 */
@Data
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    // 优惠券代码
    private String code;

    // 折扣金额
    private BigDecimal discountAmount;

    // 有效期开始日期
    private LocalDate startDate;

    // 有效期结束日期
    private LocalDate endDate;

    // 其他可能的属性...

}