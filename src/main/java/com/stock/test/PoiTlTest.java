package com.stock.test;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Pictures;

import java.io.FileOutputStream;
import java.util.HashMap;

public class PoiTlTest {

    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\makuo\\Desktop\\模板.docx";
        String image = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.netkou.net%2Fuploads%2F180224%2F1-1P224123411532.jpg&refer=http%3A%2F%2Fwww.netkou.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1664093806&t=13d711c2b26a132d8f4d44f2eb8880d0";
        XWPFTemplate template = XWPFTemplate.compile(path).render(
                new HashMap<String, Object>(){{
                    put("pic", Pictures.ofUrl(image).size(20, 50).create());
                }});
        template.writeAndClose(new FileOutputStream("output.docx"));
    }
}
