package com.stock.util;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Texts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Poitl {

    public static void readWord(String templateName, String... args) throws IOException {
        HashMap<Object, Object> temMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            temMap.put(args[i],"hello" + i);
            temMap.put("1", Texts.of("").color("").create());
        }
        XWPFTemplate xwpfTemplate = XWPFTemplate.compile(templateName).render(temMap);
        xwpfTemplate.writeAndClose(new FileOutputStream(templateName));
    }

    public static void wordOutPut() throws IOException {
        XWPFTemplate template = XWPFTemplate.compile("D:\\\\poi\\\\1.docx").render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                }});
        template.writeAndClose(new FileOutputStream("output.docx"));
    }

    public static void main(String[] args) {
        try {
//            readWord("D:\\poi\\1.docx",new String[]{"name", "age"});
            wordOutPut();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
