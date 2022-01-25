package com.stock.controller.poitl;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.Pictures;
import com.stock.entity.poitl.PoiTemplate;
import com.stock.service.poitl.PoitlService;
import com.stock.util.DateUtil;
import com.stock.util.PoitlConfigUtil;
import com.stock.util.PoitlUtil;
import org.apache.commons.lang3.StringUtils;
import org.hc.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 单文本渲染
     * @param taskId
     * @return
     * @throws IOException
     */
    @GetMapping("/export/template")
    public String exportWord(@RequestParam("taskId") String taskId) throws IOException {

        List<PoiTemplate> list = poitlService.queryByTaskId(taskId);
        XWPFTemplate xwpfTemplate = null;
        for (PoiTemplate poiTemplate : list) {

            // 1、查询当前模板对象信息
            //PoiTemplate poiTemplate = poitlService.queryTempById(id);

            if (poiTemplate == null || StringUtils.isBlank(poiTemplate.getTempSql())) {
                throw new ExportException(" 数据有误！");
            }

            HashMap<Object, Object> data = new HashMap<>();

            // 2、查询模板定义的数据sql
            List<Map<String, Object>> resList = poitlService.excute(poiTemplate.getTempSql());

            data.put("urlImg", Pictures.ofLocal("src/test/resources/earth.png")
                    .size(100, 100).create());

            // 3、组装模板预填数据 TODO 注： 这里应该返回 Configure 对象，和封装data数据
            xwpfTemplate = PoitlUtil.buildData(poiTemplate, data, resList);
            if (xwpfTemplate == null) return "false";
        }
        // region
        //objectObjectHashMap.put("urlImg", Pictures.ofUrl("https://img1.baidu.com/it/u=1407750889,3441968730&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=799",PictureType.JPEG).create
        /*XWPFTemplate template = XWPFTemplate.compile("D:\\\\poi\\\\1.docx").render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                }});*/

        // endregion
        // 4、导出word文档
        xwpfTemplate.writeAndClose(new FileOutputStream("./doc/" + DateUtil.getNowDate() + "_" + System.currentTimeMillis() + "output.docx"));
        return "success";
    }

    /**
     * 复合文本渲染
     * @param taskId
     * @return
     * @throws IOException
     */
    @GetMapping("/export/template/{taskId}")
    public String export2Word(@PathVariable("taskId") String taskId) throws IOException {

        List<PoiTemplate> list = poitlService.queryByTaskId(taskId);
        Map<String, Object> data = new HashMap<>();
        Configure configure = null;
        for (PoiTemplate poiTemplate : list) {

            if (poiTemplate == null || StringUtils.isBlank(poiTemplate.getTempSql())) {
                throw new ExportException(" 数据有误！");
            }

            // 2、查询模板定义的数据sql
            List<Map<String, Object>> resList = poitlService.excute(poiTemplate.getTempSql());

            // 3、组装模板预填数据 返回 Configure 对象，和封装data数据 // todo 存在config覆盖问题
            Configure configureSub = PoitlConfigUtil.buildData(poiTemplate, data, resList);
            if (configureSub != null) {
                configure = configureSub;
            }
        }
        // region
        //objectObjectHashMap.put("urlImg", Pictures.ofUrl("https://img1.baidu.com/it/u=1407750889,3441968730&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=799",PictureType.JPEG).create
        /*XWPFTemplate template = XWPFTemplate.compile("D:\\\\poi\\\\1.docx").render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                }});*/

        // endregion
        XWPFTemplate xwpfTemplate = configure == null ? XWPFTemplate.compile(list.get(0).getTempPath()).render(data) : XWPFTemplate.compile(list.get(0).getTempPath(),configure).render(data);
        // 4、导出word文档
        xwpfTemplate.writeAndClose(new FileOutputStream("./doc/" + DateUtil.getNowDate() + "_" + System.currentTimeMillis() + "output.docx"));
        return "success";
    }

    /**
     * 通过加载jar的方式封装模板数据
     *
     * @param taskId 任务id
     */
    @GetMapping(value = "/loadjar/export")
    public String exportByLoadJar(String taskId, String templatePath){

        templatePath = "D:\\poi\\remix.docx";

        Result result = poitlService.loadJar(taskId);
        if (result == null || result.getData() == null){
            return "failure";
        }
        Map<String, Object> data = result.getData();
        Configure configure = (Configure) result.getConfigure();
        XWPFTemplate xwpfTemplate = result.getConfigure() == null ? XWPFTemplate.compile(templatePath).render(data) : XWPFTemplate.compile(templatePath,configure).render(data);
        // 4、导出word文档
        try {
            xwpfTemplate.writeAndClose(new FileOutputStream("./doc/" + DateUtil.getNowDate() + "_" + System.currentTimeMillis() + "output.docx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
