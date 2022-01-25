package com.stock.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.Pictures;
import com.stock.service.poitl.PoitlService;
import com.stock.util.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author mk
 * @Date 2021/6/8 17:20
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/a")
public class WordController {

    @Autowired
    private PoitlService poitlService;

    @GetMapping("/export")
    public void exportSellPlan(HttpServletRequest request, HttpServletResponse response) {
        //获得数据
        //List<User> users = userService.getUsers();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "我想大声告诉你，你一直在我世界里。");
        map.put("user", "建筑名称");
        map.put("hasflag", "有");
        //map.put("image",this.getImageBase(imagePath));
        try {
            WordUtils.exportMillCertificateWord(request, response, map, "方案", "1.ftl");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * poi-tl导出word模板数据
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/poi")
    public String exportWord(@RequestParam("id") String id) throws IOException{

        String sql = poitlService.querySqlById(id);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        List<Map<String, Object>> excute = poitlService.excute(sql);
        for (Map<String, Object> stringObjectMap : excute) {
            objectObjectHashMap.putAll(stringObjectMap);
        }
//        objectObjectHashMap.put("urlImg", Pictures.ofUrl("https://img1.baidu.com/it/u=1407750889,3441968730&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=799",PictureType.JPEG).create());
        /*XWPFTemplate template = XWPFTemplate.compile("D:\\\\poi\\\\1.docx").render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                }});*/
        objectObjectHashMap.put("urlImg", Pictures.ofUrl("http://deepoove.com/images/icecream.png", PictureType.JPEG)
                .size(100, 100).create());
        XWPFTemplate template = XWPFTemplate.compile("D:\\\\poi\\\\1.docx").render(objectObjectHashMap);
        template.writeAndClose(new FileOutputStream("./doc/" + System.currentTimeMillis() + "output.docx"));
        return "success";
    }

    //获得图片的base64码
    @SuppressWarnings("deprecation")
    public String getImageBase(String src) {
        if(src==null||src==""){
            return "";
        }
        File file = new File(src);
        if(!file.exists()) {
            return "";
        }
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    public static void main(String[] args) {
        try {
            int i = 2/0;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(3);
            return;
        }finally {
            System.out.println(2);
        }
        System.out.println(1);
    }


}
