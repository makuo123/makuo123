package com.stock.util.maven;

import java.io.File;

public class GenLibPath {
    public static void main(String[] args) {
        // 项目中存放lib的路径，也可以指定到外部路径
        String strPath = "D:\\project\\myproject\\makuo123\\src\\main\\lib\\ocr\\QRCode.jar";
        File dir = new File(strPath);
        String _content = "";
        // 根据自己lib存放路径进行填充lib，其中 ${basedir}代表当前项目路径
        String head = "<properties>\n\t<lib.dir>${basedir}/src/main/lib/ocr/QRCode.jar</lib.dir>\n</properties>\n\n";
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                String fileName2 = fileName.replace(".jar", "");
                _content += "   <dependency>\n";
                _content += "       <groupId>" + fileName2 + "</groupId>\n";
                _content += "       <artifactId>" + fileName2 + "</artifactId>\n";
                _content += "       <version>1.0</version>\n";
                _content += "       <scope>system</scope>\n";
                _content += "       <systemPath>${lib.dir}/" + fileName + "</systemPath>\n";
                _content += "   </dependency>\n\n";
            }
            System.out.println(head+"<dependencies>\n"+_content+"</dependencies>");
        }
    }

    // harmonics

}

