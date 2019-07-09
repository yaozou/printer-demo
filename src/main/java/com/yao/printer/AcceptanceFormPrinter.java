package com.yao.printer;

import com.yao.dto.AcceptanceFormDto;
import com.yao.utils.DateUtils;
import com.yao.utils.DrawUtils;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.util.Date;

public class AcceptanceFormPrinter extends BasePrinter {
    private AcceptanceFormDto dto;
    public AcceptanceFormPrinter(AcceptanceFormDto dto){
        this.dto = dto;
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
                g2.drawString("发站:"+dto.getStartStationPhone(), (float) 10, start += 15);
                g2.drawString("到站:"+dto.getEndStationPhone(), (float) 120, start);
                g2.drawString("线路:"+dto.getStartStation()+"—"+dto.getEndStation(), (float) 10, start += 15);

                font = new Font("新宋体", Font.PLAIN, 10);
                g2.setFont(font);
                g2.drawString("日期:"+dto.getDate(), (float) 10, start += 15);
                if (dto.getReturnOrderNums() > 0){
                    g2.drawString("回单:"+dto.getReturnOrderNums()+"份", (float) 100, start);
                }

               g2.drawLine(10,(int)(start+=15),10+rowWidth,(int)start);

                if (dto.getCardNo() != null && !"".equals(dto.getCardNo())){
                    font = new Font("新宋体", Font.PLAIN, 14);
                    g2.setFont(font);
                    g2.drawString("卡号:"+dto.getCardNo(), (float) 10, start += 15);
                }

                font = new Font("新宋体", Font.PLAIN, 10);
                g2.setFont(font);
                g2.drawString("托运人:"+dto.getShipper(), (float) 10, start += 15);
                g2.drawString("电话:"+dto.getShipperPhone(), (float) 10, start += 15);
                g2.drawString("收货人:"+dto.getReceiveUserName(), (float) 10, start += 15);
                g2.drawString("电话:"+dto.getReceiveUserPhone(), (float) 10, start += 15);

                /**
                 * 参数2：打印的x坐标起点         参数3  打印的y坐标起点
                 * 参数4：打印图片的宽度           参数5：打印图片的高度
                 */
                img_width = 20;
                img_height = (start += 10);
                //获取需要打印的图片，若是动态生成，直接传入绝对路径即可
                Image src = Toolkit.getDefaultToolkit().getImage(dto.getBarCodePath());
                if (src == null) {
                    System.out.println("没有找到图像");
                }
                g2.drawImage(src, 10, (int) img_height, 140, 40, c);

                drawTable(g2,(int) start );

                start+=20*(dto.getList().size()+2)+80;
                font = new Font("新宋体", Font.PLAIN, 14);
                g2.setFont(font);
                int type = dto.getType();
                String typeName = "客户联";
                if (type == 2){
                    typeName = "存根联";
                }
                g2.drawString(typeName+" ("+type+") "+dto.getSerialNo(), (float) 10, start);

                font = new Font("新宋体", Font.PLAIN, 10);
                g2.setFont(font);
                g2.drawLine(10,(int)(start+=10),10+rowWidth,(int)start);
                // 1--提付 2--已付
                int freightType = dto.getFreightType();
                String freightTypeName = "提付";
                if (freightType == 2){
                    freightTypeName = "已付";
                }
                g2.drawString(freightTypeName+":￥"+dto.getFreight(), (float) 10, start+=20);
                g2.drawLine(10,(int)(start+=10),10+rowWidth,(int)start);

                g2.drawString("代收款:"+dto.getCollectingFund(), (float) 10, start+=20);

                int startx = 10;
                g2.drawString("总运费:"+dto.getTotalFreight(), (float) startx, start+=12);
                if (freightType == 2){
                    startx += 50;
                    g2.drawString("实付款:"+dto.getActualPayment(), (float) startx, start);
                }
                startx += 50;
                g2.drawString("总费用:"+dto.getTotalFund(), (float) startx, start);

                g2.drawString("承运人:"+dto.getDeliver(), (float) 10, start+=12);
                g2.drawString("电话:"+dto.getDeliverPhone(), (float) 80, start);

                img_height = (start += 20);
                //获取需要打印的图片，若是动态生成，直接传入绝对路径即可
                src = Toolkit.getDefaultToolkit().getImage(dto.getQrCodePath());
                if (src == null) {
                    System.out.println("没有找到图像");
                }
                g2.drawImage(src, 60, (int) img_height, 60, 60, c);

                font = new Font("新宋体", Font.PLAIN, 14);
                g2.setFont(font);
                g2.drawString("关注并注册，查您想查的，问您想问的", (float) 10, start+=80);
                font = new Font("新宋体", Font.PLAIN, 10);
                g2.setFont(font);
                g2.drawString("查询地址:www.962508.cn", (float) 10, start+=20);

                font = new Font("新宋体", Font.PLAIN, 10);
                g2.setFont(font);
                g2.drawString("余氏东风首个地产作品——佳欣.时代天汇", (float) 10, start+=30);
                g2.drawString("4月20日    奢艳亮相", (float) 60, start+=15);

                int nums = DrawUtils.drawStringLn(g2,"余氏东风深耕细作21载 为致敬广大合作商及员工特推出210套内部房源 开盘优惠基础之上 额外再享7%专属优惠",  10, (int) (start+=20),rowWidth);
                g2.drawString("席位有限  限时特供", (float) 60, start+=nums*20);
                g2.drawString("（仅限4.30日前报名认购客户）", (float) 60, start+=20);

                g2.drawString("建面约46-67M2巨屏幻变LOFT  即将入市", (float) 10, start+=40);
                g2.drawString("内部认购专线028-8335577", (float) 10, start+=10);

                g2.drawString("补充条款", (float) 80, start+=30);
                g2.drawString("特别约定：", (float) 10, start+=15);
                nums = DrawUtils.drawStringLn(g2,"托运人托运货物，保价货物灭失按保价金额的80%赔偿，" +
                        "申明价值货物按申明价值的60%以内赔偿；代收款货物按代收款金额的60%赔偿。" +
                        "未保价货物灭失的，江苏、浙江发往四川、重庆按运费的2倍赔偿，其它的按运费10-70倍赔偿。" +
                        "托运人确认：承运人已提前对本托运单条款内容，尤其是对承运人限额赔偿责任履行了说明义务。" +
                        "托运人已充分理解托运单所有条款具体内容和法律后果，托运人取得本托运单（即使未签字），" +
                        "即为托运人对托运单所有条款认可，并视为托运人已认可交货处公示的《委托运输合同条款》" +
                        "对托运人具有完全的法律约束力",10, (int) (start+=20),rowWidth);

                g2.drawString("托运人(托运人的委托人签字)", (float) 10, start+=nums*20);
                g2.drawLine(60,(int)(start+=15),180,(int)start);

                g2.drawString("打印时间:"+ DateUtils.dateToStr(new Date()), (float) 10, start+=20);

                font = new Font("新宋体", Font.PLAIN, 12);
                g2.setFont(font);
                DrawUtils.drawStringLn(g2,"余氏东风开通遂宁河东新区专线，敬请惠顾！",  10, (int) (start+=20),rowWidth);
                System.out.println("-----------------打印成功-------------------");

                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;
        }

    }

    private void drawTable(Graphics2D g2,int start){
        int rowWidth =  30;
        int rowHeight = 20;
        int rows = dto.getList().size();
        int columns = 6;
        int totalNums = 0;
        double totalWeight = 0,totalVolume = 0,totalFreight = 0;
        start += 60;
        for(int i = 0;i<rows+2;i++){
            if (i > 0){
                start += rowHeight;
            }
            g2.drawLine(10, start, columns*rowWidth+10, start);
            if (i <= dto.getList().size()){
                for (int j = 0;j<columns;j++){
                    String msg = "";
                    if (i == 0){
                        switch (j){
                            case 0:
                                msg = "货物";break;
                            case 1:
                                msg = "包装";break;
                            case 2:
                                msg = "件数";break;
                            case 3:
                                msg = "重量";break;
                            case 4:
                                msg = "体积";break;
                            case 5:
                                msg = "运费";break;
                        }
                    }else{
                        switch (j){
                            case 0:
                                msg = dto.getList().get(i-1).getGoodsType();break;
                            case 1:
                                msg = dto.getList().get(i-1).getPackageType();break;
                            case 2:
                                double nums = dto.getList().get(i-1).getNums();
                                msg = nums+"";
                                totalNums += nums;
                                break;
                            case 3:
                                double weight  = dto.getList().get(i-1).getWeight();
                                msg = weight+"";
                                totalWeight += weight;
                                break;
                            case 4:
                                double volume = dto.getList().get(i-1).getVolume();
                                msg = volume+"";
                                totalVolume += volume;
                                break;
                            case 5:
                                double freight = dto.getList().get(i-1).getFreight().doubleValue();
                                msg = freight+"";
                                totalFreight += freight;
                                break;
                        }
                    } // end of if
                    int x1 = 10+j*rowWidth;
                    g2.drawLine(x1, start, x1, start+rowHeight);
                    g2.drawString(msg,x1+5,start+rowHeight/2);
                }
            }
        }

        int startX = 10;
        for (int i = 0;i<columns;i++){
            String msg = "";
            switch (i){
                case 0:
                    msg = "合计";break;
                case 1:
                    msg = totalNums+"";break;
                case 2:
                    msg = totalWeight+"";break;
                case 3:
                    msg = totalVolume+"";break;
                case 4:
                    msg = totalFreight+"";break;
            }
            int n = 1;
            if (i == 1) {
                n = 2;
            }
            if (i > 0){
                startX += rowWidth*n;
            }
            g2.drawLine(startX, start, startX, start+rowHeight);
            g2.drawString(msg,startX+5,start+10);
        }
        int y1 = start+rowHeight;
        g2.drawLine(10, y1, columns*rowWidth+10, y1);
    }
}
