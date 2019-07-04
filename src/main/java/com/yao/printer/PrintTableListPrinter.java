package com.yao.printer;

import com.yao.dto.PrintTableListDto;
import com.yao.utils.DrawUtils;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

public class PrintTableListPrinter extends BasePrinter {

    private PrintTableListDto dto;
    // 0---无票数清单发票 1---有票数清单发票
    private int flag;

    public PrintTableListPrinter(PrintTableListDto dto,int flag){
        this.dto = dto;
        this.flag = flag;
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
        Font font = new Font("新宋体", Font.PLAIN, 7);
        g2.setFont(font);//设置字体
        float[] dash1 = {2.0f};
        g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));

        font = new Font("新宋体", Font.PLAIN, 16);
        g2.setFont(font);
        float start = 0;
        //标题，固定不变
        g2.drawString(dto.getTitle(), (float) 60, start += 16);
        switch (flag) {
            case 0:
                font = new Font("新宋体", Font.PLAIN, 12);
                g2.setFont(font);
                g2.drawString("日期："+dto.getDate(), (float) 10, start += 16);
                g2.drawString("总件数："+dto.getTotalNums(), (float) 120, start);

                g2.drawString("发站："+dto.getStartStation(), (float) 10, start += 16);
                g2.drawString("到站："+dto.getEndStation(), (float) 120, start);

                font = new Font("新宋体", Font.PLAIN, 10);
                g2.setFont(font);
                int rowHeight = 25,rowWidth = 50;
                int rows = dto.getList().size()<10?11:dto.getList().size()+1,columns = 4;
                double[] rate = {0.5d,1.1d,1.1d,0.5d};
                int tableX = 10,tableY = (int) (start+10);
                DrawUtils.drawTable(g2,tableX,tableY,rows,columns,rowWidth,rowHeight,rate);

                int y1 = tableY;

                font = new Font("新宋体", Font.PLAIN, 10);
                g2.setFont(font);
                int listNum = dto.getList().size()+1;
                for (int i = 0;i<listNum;i++){
                    int x1 = tableX;
                    y1 += rowHeight;
                    for(int j =0;j<columns;j++){
                        String msg = "";
                        if (i == 0){
                            switch (j){
                                case 0:
                                    msg = "序号";break;
                                case 1:
                                    msg = "运单号";break;
                                case 2:
                                    msg = "货物";break;
                                case 3:
                                    msg = "件数";break;
                            }
                        }else {
                            switch (j){
                                case 0:
                                    msg = i+"";break;
                                case 1:
                                    msg = dto.getList().get(i-1).getSerialNo();break;
                                case 2:
                                    msg = dto.getList().get(i-1).getGoodsType();
                                    break;
                                case 3:
                                    msg = dto.getList().get(i-1).getNums()+"";break;
                            }
                        }
                        int diff = 15;
                        if (j > 0){
                            diff = tableX+(j*(int)(rowWidth*rate[j-1]))+5;
                        }
                        g2.drawString(msg,diff,y1-10);
                    }
                }

                System.out.println("-----------------打印成功-------------------");
                return PAGE_EXISTS;
            case 1:
                font = new Font("新宋体", Font.PLAIN, 12);
                g2.setFont(font);
                g2.drawString("日期："+dto.getDate(), (float) 10, start += 16);
                g2.drawString("总票数："+dto.getTotalTicketNums(), (float) 120, start);

                g2.drawString("发站："+dto.getStartStation(), (float) 10, start += 16);
                g2.drawString("总件数："+dto.getTotalNums(), (float) 120, start);

                rowHeight = 25;rowWidth = 40;
                rows = dto.getList().size()<10?11:dto.getList().size()+1;columns = 5;
                g2.setFont(new Font("新宋体", Font.PLAIN, 10));
                tableX = 10;tableY = (int) (start+10);
                double[] rate1 = {0.5d,1.4d,1.2d,1.1d,1d};
                DrawUtils.drawTable(g2,tableX,tableY,rows,columns,rowWidth,rowHeight,rate1);

                y1 = tableY;
                listNum = dto.getList().size()+1;
                for (int i = 0;i<listNum;i++){
                    int x1 = tableX;
                    y1 += rowHeight;
                    for(int j =0;j<columns;j++){
                        String msg = "";
                        if (i == 0){
                            switch (j){
                                case 0:
                                    msg = "序号";break;
                                case 1:
                                    msg = "到站";break;
                                case 2:
                                    msg = "票数";break;
                                case 3:
                                    msg = "件数";break;
                                case 4:
                                    msg = "运费";break;
                            }
                        }else {
                            switch (j){
                                case 0:
                                    msg = i+"";break;
                                case 1:
                                    msg = dto.getList().get(i-1).getEndStation();break;
                                case 2:
                                    msg = dto.getList().get(i-1).getTicketNums()+"";break;
                                case 3:
                                    msg = dto.getList().get(i-1).getNums()+"";break;
                                case 4:
                                    msg = dto.getList().get(i-1).getFreight()+"";break;
                            }
                        }
                        int diff = 15;
                        if (j > 0){
                            diff = tableX+(j*(int)(rowWidth*rate1[j-1]))+5;
                        }
                        g2.drawString(msg+"",diff,y1-10);
                    }
                }

                System.out.println("-----------------打印成功-------------------");
                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;
        }
    }
}
