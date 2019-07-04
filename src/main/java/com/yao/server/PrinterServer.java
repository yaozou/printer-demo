package com.yao.server;

import com.yao.dto.*;
import com.yao.printer.*;

import java.awt.print.*;
import java.util.List;

public class PrinterServer implements IPrinter {
    double width = 750, height=620;
    @Override
    public void externalStickersPrint(List<ExternalStickersDto> dtos) {
        ExternalStickersPrinter printer = new ExternalStickersPrinter(dtos);
        run (printer , dtos.size());
    }

    @Override
    public void returnOrderPrint(List<ReturnOrderDto> dtos) {
        ReturnOrderPrinter printer = new ReturnOrderPrinter(dtos);
        run(printer,dtos.size());
    }

    @Override
    public void inventoryListOfUnarrivedGoodsPrint(InventoryListDto dto) {
        InventoryListOfUnarrivedGoodsPrinter printer = new InventoryListOfUnarrivedGoodsPrinter(dto);
        run(printer,1);
    }

    @Override
    public void inventoryListOfTransportingGoodsPrint(InventoryListDto dto) {
        InventoryListOfTransportingGoodsPrinter printer = new InventoryListOfTransportingGoodsPrinter(dto);
        run(printer,1);
    }

    @Override
    public void inventoryListOfArrivedGoodsPrint(InventoryListDto dto) {
        InventoryListOfArrivedGoodsPrinter printer = new InventoryListOfArrivedGoodsPrinter(dto);
        run(printer,1);
    }

    @Override
    public void printTableListPrint(PrintTableListDto dto, int flag) {
        PrintTableListPrinter printer = new PrintTableListPrinter(dto,flag);
        run(printer,1);
    }

    @Override
    public void signReceiptPrint(SignReceiptDto dto, int type) {
        SignReceiptPrinter printer =  new SignReceiptPrinter(dto,type);
        run(printer,1);
    }

    @Override
    public void acceptanceFormPrint(AcceptanceFormDto dto) {
        AcceptanceFormPrinter printer = new AcceptanceFormPrinter(dto);
        this.height = 1200;
        run(printer,1);
    }
    private void run (BasePrinter basePrinter , int size){
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT); // 设置成竖打

        //通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        p.setSize(width,height);//纸张大小
        p.setImageableArea(0,0, width,height);
        pf.setPaper(p);

        //获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();
        Book book = new Book();
        //    把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append(basePrinter, pf);
        // 设置打印类
        job.setPageable(book);
        try {
            //直接打印 ,不显示对话框
            for (int i=0;i<size;i++ ){
                basePrinter.setIndex(i);
                job.print();
            }
        }catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}
