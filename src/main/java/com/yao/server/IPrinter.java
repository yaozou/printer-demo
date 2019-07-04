package com.yao.server;

import com.yao.dto.*;

import java.util.List;

public interface IPrinter {
    void externalStickersPrint(List<ExternalStickersDto> dtos);
    void returnOrderPrint(List<ReturnOrderDto> dtos);
    void inventoryListOfUnarrivedGoodsPrint(InventoryListDto dto);
    void inventoryListOfTransportingGoodsPrint(InventoryListDto dto);
    void inventoryListOfArrivedGoodsPrint(InventoryListDto dto);
    void printTableListPrint(PrintTableListDto dto,int flag);
    void signReceiptPrint(SignReceiptDto dto, int type);
    void acceptanceFormPrint(AcceptanceFormDto dto);
}
