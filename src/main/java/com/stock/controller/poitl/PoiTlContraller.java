package com.stock.controller.poitl;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Pictures;
import com.stock.entity.poitl.PoiTemplate;
import com.stock.service.poitl.PoitlService;
import com.stock.util.DateUtil;
import com.stock.util.PoitlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/poitl")
public class PoiTlContraller {

    @Autowired
    private PoitlService poitlService;

    @GetMapping("/export/template")
    public String exportWord(@RequestParam("id") String id) throws IOException {

        // 1、查询当前模板对象信息
        PoiTemplate poiTemplate = poitlService.queryTempById(id);

        if (poiTemplate == null || StringUtils.isBlank(poiTemplate.getTempSql())){
            throw new ExportException(" 数据有误！");
        }

        HashMap<Object, Object> objectHashMap = new HashMap<>();

        // 2、查询模板定义的数据sql
        List<Map<String, Object>> resList = poitlService.excute(poiTemplate.getTempSql());

        objectHashMap.put("urlImg", Pictures.ofLocal("src/test/resources/earth.png")
                .size(100, 100).create());

        // 3、组装模板预填数据 TODO 注： 这里应该返回 Configure 对象，和封装objectHashMap数据
        XWPFTemplate xwpfTemplate = PoitlUtil.buildData(poiTemplate, objectHashMap, resList);
        if (xwpfTemplate == null) return "false";

        // region
        //objectObjectHashMap.put("urlImg", Pictures.ofUrl("https://img1.baidu.com/it/u=1407750889,3441968730&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=799",PictureType.JPEG).create
        /*XWPFTemplate template = XWPFTemplate.compile("D:\\\\poi\\\\1.docx").render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                }});*/

        // endregion

        // 4、导出word文档
        xwpfTemplate.writeAndClose(new FileOutputStream("./doc/" + DateUtil.getNowDate() + "" + System.currentTimeMillis() + "output.docx"));
        return "success";
    }
}
