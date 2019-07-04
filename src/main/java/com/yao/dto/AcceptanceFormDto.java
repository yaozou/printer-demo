package com.yao.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 受理单
 */
@Data
public class AcceptanceFormDto {

    //1--客户联，2---存根联
    private int type;

    private String startStationPhone;
    private String endStationPhone;
    // 路线
    private String startStation;
    private String endStation;
    private String date;

    private String shipper;
    private String shipperPhone;

    private String receiveUserName;
    private String receiveUserPhone;
    private String address;

    private List<BaseInventoryListInfo> list;

    private String barCodePath;

    // 运单号
    private String serialNo;

    //运费
    private String freight;
    // 1--提付 2--已付
    private int freightType;

    //代收款
    private String collectingFund;
    //总运费
    private BigDecimal totalFreight;
    //实付款
    private BigDecimal actualPayment;
    //总费用
    private BigDecimal totalFund;

    //承运人
    private String deliver;
    //承运人电话
    private String deliverPhone;

    private String qrCodePath;
}
