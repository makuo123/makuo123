package com.stock.util;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.ChartMultiSeriesRenderData;
import com.deepoove.poi.data.ChartSingleSeriesRenderData;
import com.deepoove.poi.data.Charts;
import com.deepoove.poi.data.Texts;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.stock.entity.poitl.ChartParam;
import com.stock.entity.poitl.PoiTemplate;
import com.stock.enums.poitl.PoiTempEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
@Slf4j
public class PoitlUtil {

    public static void readWord(String templateName, String... args) throws IOException {
        HashMap<Object, Object> temMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            temMap.put(args[i],"hello" + i);
            temMap.put("1", Texts.of("").color("").create());
        }
        XWPFTemplate xwpfTemplate = XWPFTemplate.compile(templateName).render(temMap);
        xwpfTemplate.writeAndClose(new FileOutputStream(templateName));
    }

    public static void wordOutPut() throws IOException {
        XWPFTemplate template = XWPFTemplate.compile("D:\\\\poi\\\\1.docx").render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                }});
        template.writeAndClose(new FileOutputStream("output.docx"));
    }

    // 封装模板数据
    public static XWPFTemplate buildData(PoiTemplate poiTemplate, HashMap<Object, Object> data, List<Map<String, Object>> resList) {

        // 图表数据
        if (poiTemplate.getTempType().endsWith(PoiTempEnum.GRAPH.toString()))
        {
            // 循环内的元素集合
            List<Map<String, Object>> elements = new ArrayList<>();
            // 图表对象元素
            Map<String, Object> element = new HashMap<>();
            // chart数据封装
            Map<String, Object> chartMap = new HashMap<>();
            // 图表title
            String chartTitle = "大象分布图";
            // x轴定义范围
            String[] value = {"2008", "2009", "2010" };
            // 图表x，y轴数据集合
            List<ChartParam> series = new ArrayList<>();
            try {
                resList.forEach(map ->{
                    if (map.get("name") != null) {
                        series.add(new ChartParam(String.valueOf(map.get("name")), (Number) map.get("value")));
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                log.error("图表类型为：{} 的数据有误！", PoiTempEnum.GRAPH.toString());
            }

            /*  ============ 测试数据 ============= */
            series.add(new ChartParam("幼象", 200));
            series.add(new ChartParam("幼象", 300));
            series.add(new ChartParam("幼象", 400));
            /*series.add(new ChartParam("成象", 250));
            series.add(new ChartParam("成象", 350));
            series.add(new ChartParam("成象", 450));*/
            /*  ============ 测试数据 ============= */

            // ************** 拼接chart数据 start ****************
            for (ChartParam chartParam : series) {
                Object valueObj = chartMap.get(chartParam.getName());
                List<Number> valueList = null;
                if (valueObj != null){
                    valueList = (List<Number>) valueObj;
                }else {
                    valueList = new ArrayList<>();
                }
                valueList.add(chartParam.getValue());
                chartMap.put(chartParam.getName(), valueList);
            }

            if (!poiTemplate.getTempType().startsWith(PoiTempEnum.MULTIS.toString())){
                Charts.ChartSingles chartSingles = Charts
                        .ofSingleSeries(chartTitle, value);

                for (Map.Entry<String, Object> entry : chartMap.entrySet()) {
                    String key = entry.getKey();
                    List<Number> numbers = (List<Number>)entry.getValue();
                    chartSingles.series(key, numbers.toArray(new Number[]{}));
                }

                ChartSingleSeriesRenderData chartSingleSeriesRenderData = chartSingles.create();
                element.put("name", "大象");
                element.put("chart", chartSingleSeriesRenderData);
                elements.add(element);

                HashMap<String, Object> loop = new HashMap<String, Object>() {{
                    // 图表类型每个循环体只能有一个sql，对应一个循环标签：elements
                    put("elements", elements);
                }};

                return XWPFTemplate.compile(poiTemplate.getTempPath()).render(loop);
            }

            //put("pieChart", pie);

            Charts.ChartMultis chartMultis = Charts.ofMultiSeries(chartTitle, value);
            for (Map.Entry<String, Object> entry : chartMap.entrySet()) {
                String key = entry.getKey();
                List<Number> numbers = (List<Number>)entry.getValue();
                chartMultis.addSeries(key, numbers.toArray(new Number[]{}));
            }
            ChartMultiSeriesRenderData chartMultiSeriesRenderData = chartMultis.create();
            // ********* 拼接chart数据 end *******************
            element.put("name", "大象");
            element.put("chart", chartMultiSeriesRenderData);
            elements.add(element);

            HashMap<String, Object> loop = new HashMap<String, Object>() {{
                // 图表类型每个循环体只能有一个sql，对应一个循环标签：elements
                put("elements", elements);
            }};

            return XWPFTemplate.compile(poiTemplate.getTempPath()).render(loop);

        }

        // 循环表格数据
        if (poiTemplate.getTempType().equals(PoiTempEnum.LOOP_TABLE.toString())){

            JudgeParamType.buildListValue(resList);

            data.put(poiTemplate.getLabor(), resList);

            if (PoiTemplate.isNotBlack(poiTemplate)){
                HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();

                Configure config = Configure.builder()
                        .bind(poiTemplate.getLabor(), policy).build();
                return XWPFTemplate.compile(poiTemplate.getTempPath(), config).render(data);
            }
            return null;
        }

        // 文本/普通表格数据
        if (PoiTemplate.isNotBlack(poiTemplate)
                && (poiTemplate.getTempType().equals(PoiTempEnum.TEXT.toString()))
                || poiTemplate.getTempType().equals(PoiTempEnum.TABLE.toString()))
        {
            resList.forEach(map -> {
                Set<Map.Entry<String, Object>> entries = map.entrySet();
                entries.forEach(entry -> {
                    map.put(entry.getKey(), JudgeParamType.buildValue(entry.getValue()));
                });

                data.putAll(map);
            });
        }
        return XWPFTemplate.compile(poiTemplate.getTempPath()).render(data);
    }

}
