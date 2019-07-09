package com.yao.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 入库清单
 */
@Data
public class BaseInventoryListInfo {
    // 区域
    private String area;
    // 单号
    private String orderNo;

    // 物品类型
    private String goodsType;
    // 用户名
    private String userName;

    //联系电话
    private String userPhone;
    // 运单号
    private String serialNo;
    // 品牌
    private String brandName;
    // 个数
    private double nums;
    // 单位 （件）
    private String unit;
    //票数
    private int ticketNums;

    //发站
    private String startStation;
    //到站
    private String endStation;

    //运费
    private BigDecimal freight;

    //重量
    private double weight;
    //体积
    private double volume;
    //包装
    private String packageType;

    public BaseInventoryListInfo(){}

    public BaseInventoryListInfo(String area,String orderNo){
        this.area = area;
        this.orderNo = orderNo;
    }

    public BaseInventoryListInfo(String area,String orderNo,String goodsType,String userName){
        this(area,orderNo);
        this.goodsType = goodsType;
        this.userName = userName;
    }

    public BaseInventoryListInfo(String brandName,
                                 String userName,String userPhone,
                                 String serialNo,String goodsType,double nums,String unit){
        this.brandName = brandName;

        this.userName = userName;
        this.userPhone = userPhone;

        this.serialNo = serialNo;
        this.goodsType = goodsType;

        this.nums = nums;
        this.unit = unit;
    }

    public BaseInventoryListInfo(String endStation,int ticketNums,double nums,BigDecimal freight){
        this.endStation = endStation;
        this.ticketNums = ticketNums;
        this.nums = nums;
        this.freight = freight;
    }
}
