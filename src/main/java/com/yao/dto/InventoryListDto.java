package com.yao.dto;

import lombok.Data;

import java.util.List;

/**
 * 未到货入库清单
 */
@Data
public class InventoryListDto {
    private List<BaseInventoryListInfo> list;
}
