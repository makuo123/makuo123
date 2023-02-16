package com.stock.util;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.http.HttpGlobalConfig;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClient {

    static String LOGIN = "http://192.168.1.29/login";
    static String ADD_USER = "http://192.168.1.29/v1/faceset/user/add";
    static String BIND_GROUP = "http://192.168.1.29/v1/faceset/user/bindGroup";
    static String GROUP_LIST = "http://192.168.1.29/v1/faceset/group/getPageList";
    static String USERNAME = "admin";
    static String PASSWORD = "ThunderSoft@88";
    static String TOKEN = "";
    static String APPID = "Edge-FACE-DB";
    static JSONArray GROUP_ARRAY = null;
    static String USERID = "004";
    static String PICTURE = "D:\\8.jpg";

    public static void main(String[] args) {
        login(USERNAME, PASSWORD);
        groupList();
        faceAdd(PICTURE, "林志颖", USERID, true);
    }

    //添加人脸数据
    public static void faceAdd(String pic, String name, String userId, boolean isBlack){
        BufferedImage image = ImgUtil.read(pic);
        String base64Img = ImgUtil.toBase64(image, "jpg");

        HttpRequest post = HttpUtil.createPost(ADD_USER);
        JSONObject body = new JSONObject();
        body.put("appId", APPID);
        body.put("userName", name + userId);
        body.put("userId", userId);
        body.put("userPhone", null);
        JSONArray images = new JSONArray();
        JSONObject img = new JSONObject();
        img.put("image", base64Img);
        img.put("imageType", "BASE64");
        img.put("imageName", userId + ".jpg");
        img.put("contentType", "image/jpeg");
        images.add(img);
        body.put("images", images);

        String addFace = post.body(body.toJSONString()).header("Authorization", TOKEN).timeout(HttpGlobalConfig.getTimeout()).execute().body();
        JSONObject face = JSON.parseObject(addFace);
        Integer code = face.getInteger("errorCode");
        if (code.intValue() != 0){
            System.out.println("Add Face Error!");
            return;
        }

        bingGroup(userId, isBlack);
    }

    //绑定人脸到指定的组
    public static void bingGroup(String userId, boolean isBlack){
        JSONObject groupBind = new JSONObject();
        groupBind.put("appId", APPID);
        groupBind.put("userId", userId);
        List<String> groupIds = new ArrayList<>();

        GROUP_ARRAY.forEach(e -> {
            JSONObject obj = (JSONObject) e;
            if (isBlack && obj.getString("groupName").equals("黑名单")){
                groupIds.add(obj.getString("groupId"));
            }else if (!isBlack && obj.getString("groupName").equals("白名单")){
                groupIds.add(obj.getString("groupId"));
            }
        });

        groupBind.put("groupIds", groupIds);

        HttpRequest request = HttpUtil.createPost(BIND_GROUP);
        String bind = request.body(groupBind.toJSONString()).header("Authorization", TOKEN).timeout(HttpGlobalConfig.getTimeout()).execute().body();
        JSONObject bg = JSON.parseObject(bind);
        Integer bgCode = bg.getInteger("errorCode");
        if (bgCode.intValue() != 0){
            System.out.println("Bind Face Error!");
        }
    }

    //获取分组信息
    public static void groupList(){
        HttpRequest post = HttpUtil.createPost(GROUP_LIST);
        Map<String, Object> params = new HashMap<>();
        params.put("appId", APPID);
        params.put("pageNo", 1);
        params.put("pageSize", 500);
        String body = post.body(JSON.toJSONString(params)).header("Authorization", TOKEN).timeout(HttpGlobalConfig.getTimeout()).execute().body();
        JSONObject list = JSON.parseObject(body);
        JSONObject data = list.getJSONObject("data");
        JSONArray records = data.getJSONArray("records");
        GROUP_ARRAY = records;
    }

    //登录
    public static String login(String username, String password){
        Map<String, Object> loginPars = new HashMap<>();
        loginPars.put("username", username);
        String encode = getMD5(password).toLowerCase();
        loginPars.put("password", encode);
        String post = HttpUtil.post(LOGIN, JSON.toJSONString(loginPars));

        JSONObject jsonObject = JSON.parseObject(post);
        JSONObject context = jsonObject.getJSONObject("context");
        JSONObject tokenInfo = context.getJSONObject("tokenInfo");
        String token = tokenInfo.getString("token");
        TOKEN = token;
        return token;
    }

    //将明文转换成MD5
    public static String getMD5(String md5) {
        String strInfoDigest = "";
        MessageDigest ms;
        try {
            ms = MessageDigest.getInstance("MD5");
            ms.update(md5.getBytes());
            byte[] bs = ms.digest();
            strInfoDigest = byteToHex(bs);
        } catch (NoSuchAlgorithmException e) {

            System.out.println("\u975E\u6CD5\u6458\u8981\u7b97\u6CD5");
        }

        return strInfoDigest.toUpperCase();
    }

    public static String byteToHex(byte[] bs) {
        String s = "";
        String strTemp = "";
        for (int i = 0; i < bs.length; i++) {
            strTemp = Integer.toHexString(bs[i] & 255);
            if (strTemp.length() == 1) {
                s = (new StringBuilder(String.valueOf(s))).append("0").append(strTemp).toString();
            }
            else {
                s = (new StringBuilder(String.valueOf(s))).append(strTemp).toString();
            }
        }
        return s.toUpperCase();
    }
}
