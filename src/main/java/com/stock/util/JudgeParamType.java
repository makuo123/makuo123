package com.stock.util;

import com.deepoove.poi.data.Pictures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JudgeParamType {

    public static Boolean judgeInteger(Object param) {
        if (param instanceof Integer) {
            return true;
        }
        return false;
    }

    public static Boolean judgeDouble(Object param) {
        if (param instanceof Double) {
            return true;
        }
        return false;
    }

    public static Boolean judgeString(Object param) {
        if (param instanceof String) {
            return true;
        }
        return false;
    }

    public static Object buildValue(Object param) {
        if (judgeImage(param)) return Pictures.ofUrl(String.valueOf(param), null);
        return param;
    }

    public static Boolean judgeImage(Object param) {
        File file = new File(String.valueOf(param));
        try {
            BufferedImage read = ImageIO.read(file);
            if (read == null){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        JudgeParamType judgeParamType = new JudgeParamType();
        Boolean aBoolean = judgeParamType.judgeInteger("1");
        System.out.println(aBoolean);
        System.out.println(judgeParamType.judgeDouble(2));
    }
}
