package com.learn.qr;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeException;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 生成二维码测试
 *
 * @author wangxl
 * @date 2021/7/20 22:18
 */
public class QRCodeTest {

    @Test
    void createQRCode2FileTest() {
        createQRCode2File("https://t.bilibili.com/?spm_id_from=333.851.b_696e7465726e6174696f6e616c486561646572.29",
                "E:\\Log\\d.jpg");
    }

    @Test
    void decodeQRCodeByFileTest(){
        String content = decodeQRCodeByFile("E:\\Log\\d.jpg");
        System.out.println("content = " + content);
    }

    // 自定义参数，这部分是 hutool 工具封装的
    private static QrConfig initQrConfig() {
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.BLACK);
        // 设置背景色（灰色）
        config.setBackColor(Color.WHITE);
        return config;
    }

    /**
     * 生成到文件
     *
     * @param content  内容
     * @param filepath 文件路径
     */
    private void createQRCode2File(String content, String filepath) {
        try {
            QrCodeUtil.generate(content, initQrConfig(), FileUtil.file(filepath));
            System.out.println("生成二维码成功, 位置在：" + filepath);
        } catch (QrCodeException e) {
            System.out.println("发生错误！");
            e.printStackTrace();
        }
    }

    /**
     * 生成到流
     *
     * @param content  内容
     * @param response 响应信息
     */
    private void createQRCode2Stream(String content, HttpServletResponse response) {
        try {
            QrCodeUtil.generate(content, initQrConfig(), "png", response.getOutputStream());
            System.out.println("生成二维码成功!");
        } catch (QrCodeException | IOException e) {
            System.out.println("发生错误！");
            e.printStackTrace();
        }
    }

    /**
     * 从文件中解析二维码
     *
     * @param filepath 文件路径
     * @return 二维码内容
     */
    private String decodeQRCodeByFile(String filepath) {
        try {
            return QrCodeUtil.decode(new FileInputStream(filepath));
        } catch (Exception e) {
            e.printStackTrace();
            return "解析失败";
        }
    }

}
