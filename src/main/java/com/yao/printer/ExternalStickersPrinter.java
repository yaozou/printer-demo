package com.yao.printer;

import com.yao.dto.ExternalStickersDto;
import com.yao.utils.DateUtils;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.util.Date;
import java.util.List;

public class ExternalStickersPrinter extends BasePrinter{
    private List<ExternalStickersDto> dtos;

    public ExternalStickersPrinter(List<ExternalStickersDto> dtos){
        this.dtos = dtos;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        System.out.println("-----------------执行第" + (this.getIndex()+1 )+ "次打印-------------------");
        System.out.println("pageIndex = " + pageIndex);
        ExternalStickersDto dto = dtos.get(this.getIndex());
        Component c = null;
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
                //获取需要打印的图片，若是动态生成，直接传入绝对路径即可
                Image src = Toolkit.getDefaultToolkit().getImage(dto.getBarCodePath());
                if (src == null) {
                    System.out.println("没有找到图像");
                }

                //以下为动态的文字内容
                font = new Font("新宋体", Font.PLAIN, 16);
                g2.setFont(font);
                float start = 0;
                //标题，固定不变
                g2.drawString(dto.getTitle(), (float) 60, start += 16);

                font = new Font("新宋体", Font.PLAIN, 14);
                g2.setFont(font);
                g2.drawString(dto.getDepartment(), (float) 10, start += 20);

                font = new Font("新宋体", Font.PLAIN, 12);
                g2.setFont(font);
                Date date = new Date();
                g2.drawString(DateUtils.dateToStr(date), (float) 100, start);
                g2.drawString(DateUtils.timeToStr(date), (float) 100, start + 10);

                /**
                 * 参数2：打印的x坐标起点         参数3  打印的y坐标起点
                 * 参数4：打印图片的宽度           参数5：打印图片的高度
                 */
                img_width = 10;
                img_height = (start += 20);
                g2.drawImage(src, 10, (int) img_height, 120, 30, c);

                font = new Font("新宋体", Font.PLAIN, 20);
                g2.setFont(font);
                g2.drawString(dto.getBarCode(), (float) 10, start += 45);
                g2.drawString(dto.getArea(), (float) 50, start += 18);

                font = new Font("新宋体", Font.PLAIN, 12);
                g2.setFont(font);
                g2.drawString(dto.getUserName(), (float) 10, start += 20);
                g2.drawString(dto.getAddress(), (float) 60, start);
                System.out.println("-----------------打印成功-------------------");

                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;
        }
    }
}
