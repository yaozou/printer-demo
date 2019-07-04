package com.yao.dto;

import lombok.Data;

import java.util.Date;

/**
 * 回单
 */@Data
public class ReturnOrderDto {
    private String title; //标签页标题
    private String department;//部门
    private String area;//区域
    private String qrCodePath; //条形码
    private String qrCode; //条形码标号 运单号
    private String shipperName; //托运人
    private String shipperPhone; //托运人电话
    private String consigneeName; //收货人
    private String consigneePhone; //收货人电话
    private Date   shipDate; // 托运日期

    public ReturnOrderDto(String title,String department,String area,
                          String qrCodePath,String qrCode,
                          String shipperName,String shipperPhone,
                          String consigneeName,String consigneePhone,
                          Date   shipDate){
        this.title = title;
        this.department = department;
        this.area = area;

        this.qrCodePath = qrCodePath;
        this.qrCode = qrCode;

        this.shipperName = shipperName;
        this.shipperPhone = shipperPhone;

        this.consigneeName = consigneeName;
        this.consigneePhone = consigneePhone;

        this.shipDate = shipDate;
    }

}
