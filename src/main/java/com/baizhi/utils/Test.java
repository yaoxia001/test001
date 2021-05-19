package com.baizhi.utils;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

import java.awt.*;
import java.io.File;

/**
 * @author 姚天下
 */
public class Test {
    public static void main(String[] args) {
       /*pressText(
               "13989471497",
               new File("D:\\zhuomian\\photo\\JPEG\\基础图片.jpg"),
               new File("D:\\zhuomian\\photo\\JPEG\\合成电话.jpg"));

        pressImage(
                new File("D:\\zhuomian\\photo\\JPEG\\合成电话.jpg"),
                new File("D:\\zhuomian\\photo\\JPEG\\最终图片2.jpg"),
                new File("D:\\zhuomian\\photo\\rwm\\2.jpg"));*/

        QRCodeUtil("https://www.csdn.net/",new File("D:\\zhuomian\\photo\\rwm\\1.jpg"));
        //QRCodeUtil2("https://www.csdn.net/",new File("D:\\zhuomian\\photo\\rwm\\2.jpg"));




    }

    /**
     *  最普通的二维码生成
     * @param s
     * @param QRCode
     */
    public static void QRCodeUtil(String s,File QRCode){
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        QrCodeUtil.generate(s, 500, 500, FileUtil.file(QRCode));
    }

    /**
     * 二维码生成带颜色
     * @param s
     * @param QRCode
     */
    public static void QRCodeUtil2(String s,File QRCode){
        QrConfig config = new QrConfig(500, 500);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.CYAN.getRGB());
        // 设置背景色（白）
        config.setBackColor(Color.WHITE.getRGB());
        QrCodeUtil.generate(s, config, FileUtil.file(QRCode));

    }

    /**
     * 文字水印
     * @param phone 电话号码
     * @param basePhoto 原始图片
     * @param finallyPhoto  添加电话号码之后的图片
     */
    public static void pressText(String phone, File basePhoto,File finallyPhoto){
        ImgUtil.pressText(
                FileUtil.file(basePhoto),
                FileUtil.file(finallyPhoto),
                phone, Color.WHITE,
                new Font("黑体", Font.BOLD, 50),0,800,1f);

    }

    /**
     *
     * @param basePhoto 原始图片
     * @param finallyPhoto  合成二维码之后的图片
     * @param QRCode    要被合成的二维码
     */
    public static void pressImage(File basePhoto,File finallyPhoto,File QRCode){
        ImgUtil.pressImage(
                FileUtil.file(basePhoto),
                FileUtil.file(finallyPhoto),
                // QRCode 水印图片
                ImgUtil.read(FileUtil.file(QRCode)),0,-600,1f);
    }
}
