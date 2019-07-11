package com.yao.utils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class ImageUtils {
    public static  Image imageForURL(String imgUrl) {
        Image src = null;
        try{
            URL url = new URL(imgUrl);
            ImageIcon imageIcon = new ImageIcon(url);
            src = imageIcon.getImage();
        }catch (Exception e){
            src = Toolkit.getDefaultToolkit().getImage(imgUrl);
            e.printStackTrace();
        }
        return src;
    }
}