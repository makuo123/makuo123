package com.stock.util;

import com.deepoove.poi.data.Pictures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public static List<Map<String, Object>> buildListValue(List<Map<String, Object>> paramList) {
        paramList.forEach(map -> {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            entries.forEach(entry -> {
                map.put(entry.getKey(), JudgeParamType.buildValue(entry.getValue()));
            });
        });
        return paramList;
    }

    public static Object buildValue(Object param) {
        if (judgeImage(param)) return Pictures.ofLocal(String.valueOf(param));
        return param;
    }

    public static Boolean judgeImage(Object param) {
        try {
            File file = new File(String.valueOf(param));
            BufferedImage read = ImageIO.read(file);
            if (read == null) {
                return false;
            }
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }
}
