package com.stock.util;

import cn.hutool.core.lang.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Base64;

public class FileUtil {

    /**
     * 网络url下载
     *
     * @param request
     * @param response
     */
    public void downloadFileURL(HttpServletRequest request, HttpServletResponse response) {
        String fileUrl = "http://oss.aliyun.cn/pro/2021/06/30/2635a51b-3338-478c-9e46-0e7834afabbb.pdf";
        try {
            URL url = new URL(fileUrl);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            //对文件名进行编码防止中文乱码
            String filename = this.encodeDownloadFilename("文件.pdf", request);
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);

            OutputStream out = response.getOutputStream();

            byte[] buffer = new byte[8192];
            int length;
            while ((length = inStream.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            inStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 下载文件时，针对不同浏览器，进行附件名的编码
     *
     * @param filename 载文件名
     * @param request  请求request
     * @return 编码后的下载附件名
     * @throws IOException
     */
    public static String encodeDownloadFilename(String filename, HttpServletRequest request)
            throws IOException {
        String agent = request.getHeader("user-agent");//获得游览器
        if (agent.contains("Firefox")) { // 火狐浏览器
            filename = "=?UTF-8?B?"
                    + Base64.getEncoder().encode(filename.getBytes("utf-8"))
                    + "?=";
            filename = filename.replaceAll("\r\n", "");
        } else { // IE及其他浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        return filename;
    }


    /**
     * @param file base64编码字符串
     * @param path 图片路径-具体到文件
     * @return
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     */
    public static String generateImage(String file, String path, HttpServletRequest request) {
        // 解密
        try {
            // 项目绝对路径
            String savePath = request.getSession().getServletContext().getRealPath("upload");
            // 图片分类路径+图片名+图片后缀
            String imgClassPath = path.concat(UUID.randomUUID().toString()).concat(".jpg");
            // 解密
            Base64.Decoder decoder = Base64.getDecoder();
            // 去掉base64前缀 data:image/jpeg;base64,
            file = file.substring(file.indexOf(",", 1) + 1, file.length());
            byte[] b = decoder.decode(file);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            // 保存图片
            OutputStream out = new FileOutputStream(savePath.concat(imgClassPath));
            out.write(b);
            out.flush();
            out.close();
            // 返回图片的相对路径 = 图片分类路径+图片名+图片后缀
            return imgClassPath;
        } catch (IOException e) {
            return null;
        }
    }

}
