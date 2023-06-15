package edu.czjt.reggie.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 配送员信息
 */
@Data
public class DeliveryPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    // 姓名
    private String name;

    // 联系方式
    private String phone;

    // 工作状态，0：休息，1：工作中
    private Integer workStatus;

    // 其他可能的属性...

}