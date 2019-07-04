package com.yao;

import com.yao.dto.*;
import com.yao.server.PrinterServer;
import com.yao.utils.DateUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        PrinterServer server = new PrinterServer();
        server.externalStickersPrint(getExternalDemoDto());
        //回单
        server.returnOrderPrint(getReturnDemoDto());
        // 未到货清单
        //server.inventoryListOfUnarrivedGoodsPrint(getInventoryListOfUnarrivedGoodsDto());
        //货在途清单
        //server.inventoryListOfTransportingGoodsPrint(getInventoryListOfTransportingGoodsDto());
        //派单货物清单
        //server.inventoryListOfArrivedGoodsPrint(getInventoryListOfArrivedGoodsDto());
        //到货入库清单
        //server.printTableListPrint(getPrintTableListDto("到货入库清单"),0);
        //待发运货清单（无票数）
        //server.printTableListPrint(getPrintTableListDto("待发运货物清单"),0);
        //已发运货物清单
        //server.printTableListPrint(getPrintTableListDto("已发运货物清单"),0);
        //待发运货清单（有票数）
        //server.printTableListPrint(getPrintTableListDto1("待发运货物清单"),1);

        //签收单 0--签收联 1--客户联
        /*server.signReceiptPrint(getSignReceiptDto(),0);
        server.signReceiptPrint(getSignReceiptDto(),1);*/

        // 受理单
        // 1--客户联，2---存根联   1--提付 2--已付
        //server.acceptanceFormPrint(getAcceptanceFormDto(1,1));
//        server.acceptanceFormPrint(getAcceptanceFormDto(2,1));
//
//        server.acceptanceFormPrint(getAcceptanceFormDto(1,2));
//        server.acceptanceFormPrint(getAcceptanceFormDto(2,2));
    }
    private static List<ExternalStickersDto> getExternalDemoDto() {
        List<ExternalStickersDto> dtos = new ArrayList<ExternalStickersDto>();
        dtos.add(new ExternalStickersDto("余氏东风物流",
                "总部",
                "D:\\test.png",
                "2999-0000-481*1*1","新都区",
                "徐建琼","新都区大丰镇悉尼湾"));
        dtos.add(new ExternalStickersDto("余氏东风物流",
                "江油市",
                "D:\\test.png",
                "2999-0000-481*1*1","河南镇州",
                "王振兴",""));
        System.out.println("总共需打印"+(dtos.size())+"次");
        return dtos;
    }

    private static List<ReturnOrderDto> getReturnDemoDto() {
        List<ReturnOrderDto> dtos = new ArrayList<ReturnOrderDto>();
        dtos.add(new ReturnOrderDto("回单  1份",
                "总部","新都区",
                "D:\\test.png","2999-0000-481*1*1",
                "勾承儒","13981861802",
                "徐建琼","18782200102",
                new Date()
        ));
        System.out.println("总共需打印"+(dtos.size())+"次");
        return dtos;
    }

    private static InventoryListDto getInventoryListOfUnarrivedGoodsDto(){
        InventoryListDto dto = new InventoryListDto();
        List<BaseInventoryListInfo> infos = new ArrayList<>();
        infos.add(new BaseInventoryListInfo("新津县","2181000970210-4-1"));
        infos.add(new BaseInventoryListInfo("新津县","2181000970210-4-2"));
        infos.add(new BaseInventoryListInfo("新津县","2181000970210-4-3"));
        infos.add(new BaseInventoryListInfo("新津县","2181000970210-4-4"));
        infos.add(new BaseInventoryListInfo("新津县","2181000970210-4-5"));
        infos.add(new BaseInventoryListInfo("新津县","2181000970210-4-6"));
        infos.add(new BaseInventoryListInfo("新津县","2181000970210-4-7"));

        dto.setList(infos);
        return dto;
    }


    private static InventoryListDto getInventoryListOfTransportingGoodsDto() {
        InventoryListDto dto = new InventoryListDto();
        List<BaseInventoryListInfo> infos = new ArrayList<>();
        infos.add(new BaseInventoryListInfo("新津县","2181000970210-4-1","兽药","吴总梅") );
        infos.add(new BaseInventoryListInfo("新津县","2181000970210-4-2","兽药","吴总梅") );
        infos.add(new BaseInventoryListInfo("新津县","2181000970210-4-3","兽药","吴总梅") );
        infos.add(new BaseInventoryListInfo("新津县","2181000970210-4-4","兽药","吴总梅") );
        dto.setList(infos);
        return dto;
    }

    private static InventoryListDto getInventoryListOfArrivedGoodsDto() {
        InventoryListDto dto = new InventoryListDto();
        List<BaseInventoryListInfo> infos = new ArrayList<>();
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",1,"件"));
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",1,"件"));
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",1,"件"));
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",1,"件"));
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",1,"件"));
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",1,"件"));
        dto.setList(infos);
        return dto;
    }

    private static PrintTableListDto getPrintTableListDto(String title){
        PrintTableListDto dto = new PrintTableListDto();
        dto.setTitle(title);
        dto.setDate(DateUtils.dateToStr(new Date()));
        dto.setStartStation("遂宁");
        dto.setEndStation("新都");

        List<BaseInventoryListInfo> infos = new ArrayList<>();
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",1,"件"));
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",5,"件"));
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",1,"件"));
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",1,"件"));
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",1,"件"));
        infos.add(new BaseInventoryListInfo("爱灯堡","胡兵","13194996295",
                "28420317047","灯具",1,"件"));

        dto.setList(infos);
        return dto;
    }

    private static PrintTableListDto getPrintTableListDto1(String title){
        PrintTableListDto dto = new PrintTableListDto();
        dto.setTitle(title);
        dto.setDate(DateUtils.dateToStr(new Date()));
        dto.setStartStation("新都区");
        dto.setEndStation("");

        List<BaseInventoryListInfo> infos = new ArrayList<>();
        infos.add(new BaseInventoryListInfo("新都区",205,562,new BigDecimal("7533")));
        infos.add(new BaseInventoryListInfo("金牛天回镇",7,7,new BigDecimal("116")));
        infos.add(new BaseInventoryListInfo("成华（三环内）",1,1,new BigDecimal("20")));
        infos.add(new BaseInventoryListInfo("新都区",10,15,new BigDecimal("471")));

        dto.setList(infos);
        return dto;
    }
    private static SignReceiptDto getSignReceiptDto(){
        SignReceiptDto dto = new SignReceiptDto();
        dto.setNatePhone("028-83992268");
        dto.setDate(DateUtils.dateToStr(new Date()));

        dto.setBrand("爱登堡");
        dto.setArea("新都区");

        dto.setTrackNo("2842-0317-047");

        dto.setReceiveUserName("胡XXX");
        dto.setReceiveUserPhone("13194998564222");
        dto.setAddress("四川省遂宁市船山区河东新区万达广场斜对面的卡卡头");
        dto.setGoodsType("灯具 纸箱");
        dto.setNums(1);
        dto.setFreight("提付5.0");

        dto.setCollectingFund("342.0");
        dto.setTotalReceiveFund("329.0");

        dto.setShipper("楼约为");
        dto.setShipperPhone("18140256357");

        dto.setBarCodePath("D:\\test.png");
        dto.setQrCodePath("D:\\1562165596.png");
        return dto;
    }

    private static  AcceptanceFormDto getAcceptanceFormDto(int type,int freightType){
        AcceptanceFormDto dto = new AcceptanceFormDto();
        dto.setType(type);

        dto.setStartStationPhone("0816-5198033");
        dto.setEndStationPhone("028-962508");
        dto.setStartStation("江油市");
        dto.setEndStation("河南镇州市");

        dto.setDate(DateUtils.dateToStr(new Date()));
        dto.setShipper("马凤琴");
        dto.setShipperPhone("1877777777777777");

        dto.setReceiveUserName("王正昕");
        dto.setReceiveUserPhone("1700000000000000");
        dto.setAddress("四川省遂宁市船山区河东新区万达广场斜对面的卡卡头");

        dto.setSerialNo("2737-00002-715");

        dto.setFreight("60.0");
        dto.setFreightType(freightType);
        dto.setCollectingFund("零元整");
        dto.setTotalFreight(new BigDecimal("60"));
        dto.setActualPayment(new BigDecimal("60"));
        dto.setTotalFund(new BigDecimal("60"));

        dto.setDeliver("成名");
        dto.setDeliverPhone("028-862534");

        dto.setBarCodePath("D:\\test.png");
        dto.setQrCodePath("D:\\1562165596.png");

        List<BaseInventoryListInfo> list = new ArrayList<>();
        for (int i = 0;i<3;i++){
            BaseInventoryListInfo listInfo = new BaseInventoryListInfo();
            listInfo.setGoodsType("日化");
            listInfo.setPackageType("木架");
            listInfo.setNums(1);
            listInfo.setFreight(new BigDecimal(60));
            list.add(listInfo);
        }
        dto.setList(list);
        return dto;
    }

}
