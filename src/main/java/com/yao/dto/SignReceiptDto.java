package com.yao.dto;

import lombok.Data;

/**
 * 签收单
 */
@Data
public class SignReceiptDto {
    //网点电话
    private String natePhone;
    //日期
    private String date;

    //品牌
    private String brand;
    // 区域
    private String area;

    // 运单号
    private String trackNo;

    private String receiveUserName;
    private String receiveUserPhone;
    private String address;

    private String goodsType;
    private int nums;

    //运费
    private String freight;

    //代收款项
    private String collectingFund;
    //应收总额
    private String totalReceiveFund;

    private String shipper;
    private String shipperPhone;

    private String barCodePath;
    private String qrCodePath;
}
