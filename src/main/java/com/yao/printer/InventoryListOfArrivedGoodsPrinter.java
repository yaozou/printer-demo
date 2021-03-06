package com.yao.printer;

import com.yao.dto.BaseInventoryListInfo;
import com.yao.dto.InventoryListDto;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

public class InventoryListOfArrivedGoodsPrinter extends BasePrinter {
    private InventoryListDto dto;

    public InventoryListOfArrivedGoodsPrinter(InventoryListDto dto){
        this.dto = dto;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        System.out.println("-----------------执行第" + (this.getIndex()+1 )+ "次打印-------------------");
        System.out.println("pageIndex = " + pageIndex);

        //转换成Graphics2D
        Graphics2D g2 = (Graphics2D) graphics;
        //设置打印颜色为黑色
        g2.setColor(Color.black);

        //打印起点坐标
        double x = pageFormat.getImageableX();
        double y = pageFormat.getImageableY();
        System.out.println("起点坐标x=" + x + ";y=" + y);
        switch (pageIndex) {
            case 0:
                //设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
                //Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
                Font font = new Font("新宋体", Font.PLAIN, 7);
                g2.setFont(font);//设置字体
                float[] dash1 = {2.0f};
                //设置打印线的属性。
                //1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
                g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));

                //以下为动态的文字内容
                font = new Font("新宋体", Font.PLAIN, 16);
                g2.setFont(font);
                float start = 0;
                //标题，固定不变
                g2.drawString("派单货物清单", (float) 60, start += 16);

                font = new Font("新宋体", Font.PLAIN, 10);
                g2.setFont(font);
                for (int i =0;i<dto.getList().size();i++){
                    BaseInventoryListInfo info = dto.getList().get(i);
                    g2.drawString((i+1)+"、"+info.getBrandName()+" "+info.getUserName()+info.getUserPhone(), (float) 15, start += 16);
                    g2.drawString("  "+info.getSerialNo()+" "+info.getGoodsType()+" "+info.getNums()+""+info.getUnit(), (float) 20, start += 16);
                }
                System.out.println("-----------------打印成功-------------------");
                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;
        }
    }
}
