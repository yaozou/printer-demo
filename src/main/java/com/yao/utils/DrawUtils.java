package com.yao.utils;

import java.awt.*;

public class DrawUtils {
    public static void drawTable(Graphics2D g,
                                 int x, int y ,
                                 int rows, int columns,
                                 int rowWidth, int rowHeight,double[] rate){
        // 横线
        for(int i = 0;i<rows+1;i++){
            g.drawLine(x, y+(i*rowHeight), columns*rowWidth+x, y+(i*rowHeight));
        }
        //竖线
        for(int i = 1 ; i < columns;i++){
            g.drawLine(x+(i*(int)(rowWidth*rate[i-1])), y, x+(i*(int)(rowWidth*rate[i-1])), y+rowHeight*rows);
        }
    }

    public static int drawStringLn(Graphics2D g,String strContent,int loc_X, int loc_Y ,int rowWidth){
        //获取字符串 字符的总宽度
        int strWidth =getStringLength(g,strContent);
        //每一行字符串宽度 rowWidth
        System.out.println("每行字符宽度:"+rowWidth);
        //获取字符高度
        int strHeight=getStringHeight(g);
        //字符串总个数
        System.out.println("字符串总个数:"+strContent.length());
        if(strWidth>rowWidth){
            int rowstrnum=getRowStrNum(strContent.length(),rowWidth,strWidth);
            int  rows= getRows(strWidth,rowWidth);
            String temp="";
            for (int i = 0; i < rows; i++) {
                //获取各行的String
                if(i==rows-1){
                    //最后一行
                    temp=strContent.substring(i*rowstrnum,strContent.length());
                }else{
                    temp=strContent.substring(i*rowstrnum,i*rowstrnum+rowstrnum);
                }
                if(i>0){
                    //第一行不需要增加字符高度，以后的每一行在换行的时候都需要增加字符高度
                    loc_Y+=strHeight;
                }
                g.drawString(temp, loc_X, loc_Y);
            }
            return rows;
        }else{
            //直接绘制
            g.drawString(strContent, loc_X, loc_Y);
        }
        return 1;
    }

    private static   int  getRows(int strWidth,int rowWidth){
        int rows=0;
        if(strWidth%rowWidth>0){
            rows=strWidth/rowWidth+1;
        }else{
            rows=strWidth/rowWidth;
        }
        System.out.println("行数:"+rows);
        return rows;
    }

    private static int  getStringLength(Graphics g,String str) {
        char[]  strcha=str.toCharArray();
        int strWidth = g.getFontMetrics().charsWidth(strcha, 0, str.length());
        System.out.println("字符总宽度:"+strWidth);
        return strWidth;
    }



    private static int getRowStrNum(int strnum,int rowWidth,int strWidth){
        int rowstrnum=0;
        rowstrnum=(rowWidth*strnum)/strWidth;
        System.out.println("每行的字符数:"+rowstrnum);
        return rowstrnum;
    }

    private static int  getStringHeight(Graphics g) {
        int height = g.getFontMetrics().getHeight();
        System.out.println("字符高度:"+height);
        return height;
    }



}
