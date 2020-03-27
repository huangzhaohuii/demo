package com.example.demo.model;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 * @create 2019/12/17
 * @Description:
 * @since 1.0.0
 */
@Data
public class WayBill {

    private String txLogisticID;
    private String orderNo;
    private String mailNum;
    private List<SubMail> subMailList;
    private int ypdjpayment;
    private int orderType;
    private long serviceType;
    private String remark;
    private long weight;
    private long volumeWeight;
    private String feeWeight;
    private String insuredAmount;
    private String valuationAmount;
    private String collect;
    private String sender;
    private String receiver;
    private String items;
    private String custCode;
    private String deliveryTime;
    private String receiverPay;
    private String collectionMoney;
    private String revertBill;
    private String revertMailNo;
    private String postage;
    private String sendType;
    private String commodityMoney;
    private String state;

}
