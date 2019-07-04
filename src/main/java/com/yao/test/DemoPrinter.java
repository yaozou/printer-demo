package com.yao.test;

import java.awt.*;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DemoPrinter implements Printable {
    private static int COUNT = 0;				   //待打印数据的条数，此变量需定义在数据集合之前
    private static java.util.List<DemoDto> DEMODTO_LIST = getDemoDto();  //待打印的文字数据
    /**
     * 封装测试数据
     *
     * @return
     */
    private static java.util.List<DemoDto> getDemoDto() {
        java.util.List<DemoDto> dtos = new ArrayList<DemoDto>();
        dtos.add(new DemoDto("戒毒所打印机01", "惠普打印机", "技术支持部", "责任人01", new Date(),"D:\\test.jpg","宝丰戒毒所"));
        dtos.add(new DemoDto("戒毒所打印机02", "惠普打印机", "技术支持部", "责任人02", new Date(),"D:\\test.jpg","宝丰戒毒所"));
         if (dtos.size()>0) {
            COUNT = dtos.size()-1;
            System.out.println("总共需打印"+(COUNT+1)+"次");
        }
        return dtos;
    }
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        System.out.println("-----------------执行第"+(COUNT+1)+"次打印-------------------");

        System.out.println("pageIndex = "+pageIndex);

        Component c = null;

        //转换成Graphics2D
        Graphics2D g2 = (Graphics2D) graphics;

        //设置打印颜色为黑色
        g2.setColor(Color.black);

        //打印起点坐标
        double x = pageFormat.getImageableX();
        double y = pageFormat.getImageableY();

        System.out.println("起点坐标x="+x+";y="+y);


        switch(pageIndex){
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
                Image src = Toolkit.getDefaultToolkit().getImage(DEMODTO_LIST.get(COUNT).getQrCodePath());
                if (src == null) {
                    System.out.println("没有找到图像");
                }

                /**
                 * 参数2：打印的x坐标起点         参数3  打印的y坐标起点
                 * 参数4：打印图片的宽度           参数5：打印图片的高度
                 */
                g2.drawImage(src,(int)80,(int)25,(int)48,(int)48,c);

                //标题，固定不变
                g2.drawString(DEMODTO_LIST.get(COUNT).getTitle(), (float)30, (float)18);

                //以下为动态的文字内容
                font = new Font("新宋体", Font.PLAIN, 5);
                g2.setFont(font);
                g2.drawString("资产名称："+DEMODTO_LIST.get(COUNT).getAssetName(), (float)10, (float)30);
                g2.drawString("型    号："+DEMODTO_LIST.get(COUNT).getAssetType(), (float)10, (float)40);
                g2.drawString("部    门："+DEMODTO_LIST.get(COUNT).getDeptName(), (float)10, (float)50);
                g2.drawString("责 任 人："+DEMODTO_LIST.get(COUNT).getResponsible(), (float)10, (float)60);
                g2.drawString("启用日期："+dateToStr(DEMODTO_LIST.get(COUNT).getUseTime()), (float)10, (float)70);

                System.out.println("-----------------打印成功-------------------");
                return PAGE_EXISTS;

            default:
                return NO_SUCH_PAGE;

        }
    }

    public static void main(String[] args) {
        //    通俗理解就是书、文档
        Book book = new Book();

        //    设置成竖打
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);

        //    通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        p.setSize(142,85);//纸张大小
        p.setImageableArea(10,10, 142,70);
        pf.setPaper(p);

        //    把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append(new DemoPrinter(), pf);

        //获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();

        // 设置打印类
        job.setPageable(book);

        try {
            //直接打印 ,不显示对话框
            if (DEMODTO_LIST.size()>0) {
                for (int i=0;i<DEMODTO_LIST.size();i++) {
                    job.print();
                    --COUNT;
                }
            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

   public static String dateToStr(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }
}
