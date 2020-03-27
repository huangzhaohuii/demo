package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Administrator
 * @create 2020/2/21
 * @Description:
 * @since 1.0.0
 */
@Data
public class EmsInfo {

    /**
     * 订单号
     */
    @NotBlank(message = "订单号不能为空")
    private String orderId;

    /**
     * 寄件人姓名
     */
    @NotBlank(message = "寄件人姓名不能为空")
    private String senderName;

    /**
     * 寄件人邮编
     */
    @NotBlank(message = "寄件人邮编不能为空")
    private String senderPostCode;

    /**
     * 寄件人电话号码1
     */
    @NotBlank(message = "寄件人手机不能为空")
    private String senderMobile;

    /**
     * 寄件人电话号码2
     */
    private String senderTelephone;

    /**
     * 寄件人省份
     */
    @NotBlank(message = "寄件人省份不能为空")
    private String senderProvince;

    /**
     * 寄件人地市
     */
    @NotBlank(message = "寄件人地市不能为空")
    private String senderCity;

    /**
     * 寄件人区县
     */
    @NotBlank(message = "寄件人区县不能为空")
    private String senderCountry;

    /**
     * 寄件人地址
     */
    @NotBlank(message = "寄件人地址不能为空")
    private String senderAddress;

    /**
     * 收件人姓名
     */
    @NotBlank(message = "收件人姓名不能为空")
    private String receiverName;

    /**
     * 收件人邮编
     */
    @NotBlank(message = "收件人邮编不能为空")
    private String receiverPostCode;

    /**
     * 收件人电话1
     */
    @NotBlank(message = "收件人电话不能为空")
    private String receiverMobile;

    /**
     * 收件人电话2
     */
    private String receiverTelephone;

    /**
     * 收件人省份
     */
    @NotBlank(message = "收件人省份不能为空")
    private String receiverProvince;

    /**
     * 收件人地市
     */
    @NotBlank(message = "收件人地市不能为空")
    private String receiverCity;

    /**
     * 收件人区县
     */
    @NotBlank(message = "收件人区县不能为空")
    private String receiverCountry;

    /**
     * 收件人地址
     */
    @NotBlank(message = "收件人地址不能为空")
    private String receiverAddress;

}
