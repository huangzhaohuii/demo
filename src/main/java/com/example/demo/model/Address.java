package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Administrator
 * @create 2019/12/17
 * @Description:
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class Address {

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 电话
     */
    private String phone;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 省份
     */
    private String prov;

    /**
     * 地市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    /**
     * 地址
     */
    private String address;
}
