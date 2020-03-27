package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Administrator
 * @create 2020/2/22
 * @Description:
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class OrderNormal {
    /**
     * 订单号
     */
    private String txLogisticID;

    /**
     * 产品代码，0-经济快递 1-标准快递
     */
    private String serviceType;

    /**
     * 寄件人信息
     */
    private Address sender;

    /**
     * 收件人信息
     */
    private Address receiver;

}
