package com.stock.controller.ocr;

import com.baidu.aip.ocr.AipOcr;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OcrController {

    @PostMapping(value = "/ocr")
    public Map<Object, Object> ocr(MultipartFile file) throws Exception {
        AipOcr client = new AipOcr("25083349", "m9ohfuhRlEpDqCvEeWooIErw", "CkbLybj06hBMrZGGzZ5UjsSfZtyA5gXg");
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>(4);
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        // 参数为二进制数组
        byte[] buf = file.getBytes();
        JSONObject res = client.basicGeneral(buf, options);

        Map map = JsonChange.json2map(res.toString());
        return map;
    }


    public static class JsonChange {

        /**
         * json字符串转换为map
         */
        public static <T> Map<String, Object> json2map(String jsonString) throws Exception {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.readValue(jsonString, Map.class);
        }

    }

}

