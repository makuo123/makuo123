package com.stock.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author mk
 * @Date 2020/10/21 17:25
 * @Version 1.0
 */
public class DateUtil {

    //实现日期加一天的方法
    public static String addDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.DATE, n);//增加一天
            //cd.add(Calendar.MONTH, n);//增加一个月

            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Integer getNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String format = formatter.format(date);
        return Integer.parseInt(format);
    }

    public static void main(String[] args) {

        // 解析xml
        Document document = DocumentHelper.createDocument();
        Element param = document.addElement("param");
        Element name = param.addElement("name");
        name.addText("张三");

        Element age = param.addElement("age");
        age.addText("12");
        System.out.println(document);

        // 4、格式化模板
        //OutputFormat format = OutputFormat.createCompactFormat();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");

        String fileName = "xml_test";
        // 5、生成xml文件
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            System.out.println("生成xml文件失败。文件名【" + fileName + "】");
        }

        // 6、生成的XML文件
        // 7、利用文件输出流输出到文件， 文件输出到了您的项目根目录下了
        try (FileOutputStream fos = new FileOutputStream(fileName + ".xml")) {
            fos.write(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }


//        System.out.println("iO3H4AG7d83w7dnlrrLlh5PYVAEDtcLO".length());
        byte[] bytes = "smartnest_lims_ability".getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));
    }
}
