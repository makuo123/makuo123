package com.stock.controller.poitl;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.stock.entity.poitl.PoiTemplate;
import com.stock.service.poitl.PoitlService;
import com.stock.util.JudgeParamType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController("poitl")
public class PoiTlContraller {

    @Autowired
    private PoitlService poitlService;

    @GetMapping("/export/template")
    public String exportWord(@RequestParam("id") String id) throws IOException {

        PoiTemplate poiTemplate = poitlService.queryTempById(id);

        if (poiTemplate == null || StringUtils.isBlank(poiTemplate.getTempSql())){
            throw new ExportException(" 数据有误！");
        }

        HashMap<Object, Object> objectHashMap = new HashMap<>();

        List<Map<String, Object>> resList = poitlService.excute(poiTemplate.getTempSql());

        // 组装模板预填数据
        buildData(poiTemplate, objectHashMap, resList);

        //objectObjectHashMap.put("urlImg", Pictures.ofUrl("https://img1.baidu.com/it/u=1407750889,3441968730&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=799",PictureType.JPEG).create());
        /*XWPFTemplate template = XWPFTemplate.compile("D:\\\\poi\\\\1.docx").render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                }});*/
        objectHashMap.put("urlImg", Pictures.ofUrl("http://deepoove.com/images/icecream.png", PictureType.JPEG)
                .size(100, 100).create());
        XWPFTemplate template = XWPFTemplate.compile("D:\\\\poi\\\\1.docx").render(objectHashMap);
        template.writeAndClose(new FileOutputStream("./doc/" + System.currentTimeMillis() + "output.docx"));
        return "success";
    }

    private void buildData(PoiTemplate poiTemplate, HashMap<Object, Object> objectHashMap, List<Map<String, Object>> resList) {

        resList.forEach(map -> {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            entries.forEach(entry -> {
                map.put(entry.getKey(), JudgeParamType.buildValue(entry.getValue()));
            });

            objectHashMap.putAll(map);
        });

        if (PoiTemplate.isNotBlack(poiTemplate)){
            HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();

            Configure config = Configure.builder()
                    .bind("goods", policy).bind("labors", policy).build();

        }

    }
}
