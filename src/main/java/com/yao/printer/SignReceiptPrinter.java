package com.yao.printer;


import com.yao.dto.SignReceiptDto;
import com.yao.utils.DateUtils;
import com.yao.utils.DrawUtils;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.util.Date;

public class SignReceiptPrinter extends BasePrinter {
    private SignReceiptDto dto;
    // 0--签收联 1--客户联
    private int type;
    public SignReceiptPrinter(SignReceiptDto dto,int type){
        this.dto = dto;
        this.type = type;
    }
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        System.out.println("-----------------执行第" + (this.getIndex()+1 )+ "次打印-------------------");
        System.out.println("pageIndex = " + pageIndex);

        Component c = null;
        //转换成Graphics2D
        Graphics2D g2 = (Graphics2D) graphics;
        //设置打印颜色为黑色
        g2.setColor(Color.black);
        int rowWidth = 180;

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
                g2.drawString("余氏东风签收单", (float) 60, start += 16);

                font = new Font("新宋体", Font.PLAIN, 10);
                g2.setFont(font);
                g2.drawString("网点电话:"+dto.getNatePhone(), (float) 10, start += 15);
                g2.drawString("日期:"+dto.getDate(), (float) 120, start);

                font = new Font("新宋体", Font.PLAIN, 14);
                g2.setFont(font);
                g2.drawString(dto.getBrand()+" — "+dto.getArea(), (float) 10, start += 20);

                font = new Font("新宋体", Font.PLAIN, 12);
                g2.setFont(font);
                g2.drawString("运单号:"+dto.getTrackNo(), (float) 10, start+20);

                font = new Font("新宋体", Font.PLAIN, 14);
                g2.setFont(font);
                g2.drawString("收货人:"+dto.getReceiveUserName(), (float) 10, start+=40);
                g2.drawString("电 话:"+dto.getReceiveUserPhone(), (float) 10, start+=20);

                font = new Font("新宋体", Font.PLAIN, 12);
                g2.setFont(font);
                DrawUtils.drawStringLn(g2,dto.getAddress()+"",  10, (int) (start+=20),rowWidth);
                g2.drawString("货物:"+dto.getGoodsType(), (float) 10, start+=30);
                g2.drawString("总件数:"+dto.getNums(), (float) 10, start+=20);
                g2.drawString("运费:"+dto.getFreight(), (float) 10, start+=20);

                font = new Font("新宋体", Font.PLAIN, 14);
                g2.setFont(font);
                g2.drawString("代收款:"+dto.getCollectingFund(), (float) 10, start+=20);
                g2.drawString("应收总额:"+dto.getTotalReceiveFund(), (float) 10, start+=20);

                font = new Font("新宋体", Font.PLAIN, 12);
                g2.setFont(font);
                g2.drawString("发货人:"+dto.getShipper()+dto.getShipperPhone(), (float) 10, start+=20);
                g2.drawString("签收人:", (float) 10, start+=20);
                g2.drawString("身份证:", (float) 10, start+=20);

                /**
                 * 参数2：打印的x坐标起点         参数3  打印的y坐标起点
                 * 参数4：打印图片的宽度           参数5：打印图片的高度
                 */
                img_width = 40;
                img_height = (start += 30);
                //获取需要打印的图片，若是动态生成，直接传入绝对路径即可
                Image src = Toolkit.getDefaultToolkit().getImage(dto.getBarCodePath());
                if (src == null) {
                    System.out.println("没有找到图像");
                }
                g2.drawImage(src, 10, (int) img_height, 140, 40, c);


                img_height = (start += 50);
                //获取需要打印的图片，若是动态生成，直接传入绝对路径即可
                src = Toolkit.getDefaultToolkit().getImage(dto.getQrCodePath());
                if (src == null) {
                    System.out.println("没有找到图像");
                }
                g2.drawImage(src, 60, (int) img_height, 60, 60, c);

                font = new Font("新宋体", Font.PLAIN, 14);
                g2.setFont(font);
                g2.drawString("关注并注册，查您想查的，问您想问的", (float) 10, start+=80);
                font = new Font("新宋体", Font.PLAIN, 12);
                g2.setFont(font);
                g2.drawString("查询地址:www.962508.cn", (float) 10, start+=20);
                g2.drawString("打印时间:"+DateUtils.dateToStr(new Date()), (float) 10, start+=20);
                g2.drawString("送货人:", (float) 10, start+=20);

                font = new Font("新宋体", Font.PLAIN, 14);
                g2.setFont(font);
                // 0--签收联 1--客户联
                String typeStr = "";
                switch (type){
                    case 0:
                        typeStr = "签收联";break;
                    case 1:
                        typeStr = "客户联";break;
                }
                g2.drawString(typeStr, (float) 60, start+=30);

                DrawUtils.drawStringLn(g2,"余氏东风开通遂宁河东新区专线，敬请惠顾！",  10, (int) (start+=20),rowWidth);
                System.out.println("-----------------打印成功-------------------");

                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;
        }
    }
}
