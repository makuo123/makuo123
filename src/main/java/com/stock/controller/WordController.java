package com.stock.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.Pictures;
import com.stock.service.poitl.PoitlService;
import com.stock.util.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/c")
    public Object redirect(HttpServletRequest request, HttpServletResponse response){
        String login = "http://192.168.1.188:1088/api/blade-auth/oauth/token";
        String url = request.getParameter("url");
        HashMap<String, Object> map = new HashMap<>();
        map.put("tenantId", "000000");
        map.put("username", "admin");
        map.put("password", "e10adc3949ba59abbe56e057f20f883e");
        map.put("grant_type", "captcha");
        map.put("scope", "all");
        map.put("type", "account");
        map.put("_timestamp", 1677722931875l);

        HttpRequest httpRequest = HttpUtil.createPost(login)
                .header("Authorization", "Basic YmFzZV9zeXN0ZW06YTgwNTEzNWVmM2M4OWM4OTdjNDgxZGRmNDlhM2Q5MDk=")
                .header("Tenant-Id", "000000")
                .header("Captcha-Code", "5VERQ")
                .header("Captcha-Key", "8ebe4c08e2b9a2f58fe9e4a699fe452f");
        String body = httpRequest.form(map).execute().body();
        JSONObject user = JSONObject.parseObject(body);
        System.out.println(body);
        try {
            request.setAttribute("Authorization", "Basic YmFzZV9zeXN0ZW06Yzk3NjUwZDdkYzM0NjFiOTkwMzczNWM5NWI0YTgzZDI=");
            request.setAttribute("Blade-Auth", "bearer " + user.get("access_token"));
            response.setHeader("Authorization", "Basic YmFzZV9zeXN0ZW06Yzk3NjUwZDdkYzM0NjFiOTkwMzczNWM5NWI0YTgzZDI=");
            response.setHeader("Blade-Auth", "bearer " + user.get("access_token"));
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     *全局变量
     * @param key
     * @param value
     * @return
     */
    @GetMapping("/d")
    public Object cache(String key , String value){
        InstanceCache instanceCache = InstanceCache.getInstance();
        instanceCache.setCache(key, value);
        Map cache = instanceCache.getCache();
        return cache;
    }

    private static final String OAUTH_URL = "http://192.168.1.188:8100/oauth/token";

    @PostMapping("/login")
    public OAuth2AccessToken login(String clientId,String clientSecret, String userName, String passWorld){
        OAuth2AccessToken token = null;
        try {
            token = Oauth2Util.login(OAUTH_URL,clientId, clientSecret, userName, passWorld);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @GetMapping("dd")
    public String redirectUrl(HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect("http://baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }


}
