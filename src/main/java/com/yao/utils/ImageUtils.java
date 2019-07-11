package com.yao.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ImageUtils {

    /**
     * 通过图片的url获取图片的base64字符串
     * @param imgUrl    图片url
     * @return    返回图片base64的字符串
     */
    public static String imageForURL(String imgUrl) {
        String savePath = "test.png";
        try {
            /* 将网络资源地址传给,即赋值给url */
            URL url = new URL(imgUrl);

            /* 此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流 */
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            DataInputStream in = new DataInputStream(connection.getInputStream());

            /* 此处也可用BufferedInputStream与BufferedOutputStream  需要保存的路径*/
            DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));


            /* 将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址 */
            byte[] buffer = new byte[4096];
            int count = 0;
            while ((count = in.read(buffer)) > 0)/* 将输入流以字节的形式读取并写入buffer中 */
            {
                out.write(buffer, 0, count);
            }
            out.close();/* 后面三行为关闭输入输出流以及网络资源的固定格式 */
            in.close();
            connection.disconnect();
            return savePath;/* 网络资源截取并存储本地成功返回true */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrl;
    }
}