package com.yao.printer;

import lombok.Data;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

@Data
public class BasePrinter implements Printable {
    public static double img_width;
    public  static double img_height;
    private int index;

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }
}
