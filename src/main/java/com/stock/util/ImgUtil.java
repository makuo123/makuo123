package com.stock.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

/**
 * @Description
 * @Author makuo
 * @Date 2023/2/21 19:04
 **/
public class ImgUtil {
    /**
     * 判断图片类型
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String imgType(InputStream inputStream) throws IOException {
        // 读取文件前几位
        byte[] fileHeader = new byte[4];
        int read = inputStream.read(fileHeader, 0, fileHeader.length);
        inputStream.close();

        // 转为十六进制字符串
        String header = bytes2Hex(fileHeader);

        if (header.contains("FFD8FF")) {
            return "jpg";
        } else if (header.contains("89504E47")) {
            return "png";
        } else if (header.contains("47494638")) {
            return "gif";
        } else if (header.contains("424D")) {
            return "bmp";
        } else if (header.contains("52494646")) {
            return "webp";
        } else if (header.contains("49492A00")) {
            return "tif";
        } else {
            return "unknown";
        }

    }

    public static String bytes2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xff);
            sb.append(hex.length() == 2 ? hex : ("0" + hex));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        String path = "C:\\Users\\makuo\\Desktop\\01.png";
        File file = new File(path);
        System.out.println(ImgUtil.imgType(new FileInputStream(file)));
    }
}
