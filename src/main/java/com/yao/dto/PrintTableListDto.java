package com.yao.dto;

import lombok.Data;

import java.util.List;

/**
 * 表格清单
 */
@Data
public class PrintTableListDto extends InventoryListDto {
    private String title;
    private String date;
    private int totalNums;
    private String startStation;
    private String endStation;

    private int totalTicketNums;

    @Override
    public void setList(List<BaseInventoryListInfo> list) {
        super.setList(list);
        for (BaseInventoryListInfo info : list){
            totalNums += info.getNums();
            totalTicketNums += info.getTicketNums();
        }
    }
}
