package com.stock.util;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.ChartMultiSeriesRenderData;
import com.deepoove.poi.data.ChartSingleSeriesRenderData;
import com.deepoove.poi.data.Charts;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.stock.entity.poitl.ChartParam;
import com.stock.entity.poitl.PoiTemplate;
import com.stock.entity.poitl.TestData;
import com.stock.enums.poitl.PoiTempEnum;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class PoitlUtil {

    public static final String GRAPH_SERIES_NAME_PARAM = "name";
    public static final String GRAPH_SERIES_VALUE_PARAM = "value";

    /**
     * 封装模板数据
     * @param poiTemplate   定义的模板信息
     * @param data          封装的数据
     * @param resList       模板预填数据
     * @return
     */
    public static XWPFTemplate buildData(PoiTemplate poiTemplate, HashMap<Object, Object> data, List<Map<String, Object>> resList) {

        // 图表数据
        if (poiTemplate.getTempType().endsWith(PoiTempEnum.GRAPH.toString())) {
            // 循环内的元素集合
            final List<Map<String, Object>> elements = new ArrayList<>();

            // chart数据封装
            final Map<String, Object> chartMap = new HashMap<>();
            // 图表title
            String chartTitle = "大象分布图";
            // x轴定义范围
            String[] value = {"2008", "2009", "2010"};

            // 测试数据
            TestData.buidTestData(resList);

            // 单系列图表
            if (!poiTemplate.getTempType().startsWith(PoiTempEnum.MULTIS.toString())) {

                Map<Object, List<Map<String, Object>>> groupByType = resList.stream().collect(Collectors.groupingBy(map -> map.get("type")));
                for (Map.Entry<Object, List<Map<String, Object>>> objectListEntry : groupByType.entrySet()) {
                    // 图表x，y轴数据集合
                    List<ChartParam> series = new ArrayList<>();
                    // 图表对象元素
                    final Map<String, Object> element = new HashMap<>();
                    // chart数据封装
                    final Map<String, Object> charSubtMap = new HashMap<>();

                    List<Map<String, Object>> values = objectListEntry.getValue();

                    try {
                        values.forEach(map -> {
                            if (map.get(GRAPH_SERIES_NAME_PARAM) != null) {
                                series.add(new ChartParam(String.valueOf(map.get(GRAPH_SERIES_NAME_PARAM)), (Number) map.get(GRAPH_SERIES_VALUE_PARAM)));
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("图表类型为：{} 的数据有误！", PoiTempEnum.GRAPH.toString());
                    }

                    // region =========
                    /*  ============ 测试数据 ============= */
                    /*series.add(new ChartParam("幼象", 200));
                    series.add(new ChartParam("幼象", 300));
                    series.add(new ChartParam("幼象", 400));
                    series.add(new ChartParam("成象", 250));
                    series.add(new ChartParam("成象", 350));
                    series.add(new ChartParam("成象", 450));*/
                    /*  ============ 测试数据 ============= */
                    // endregion

                    // ************** 拼接chart数据 start ****************
                    for (ChartParam chartParam : series) {
                        Object valueObj = charSubtMap.get(chartParam.getName());
                        List<Number> valueList = null;
                        if (valueObj != null) {
                            valueList = (List<Number>) valueObj;
                        } else {
                            valueList = new ArrayList<>();
                        }
                        valueList.add(chartParam.getValue());
                        charSubtMap.put(chartParam.getName(), valueList);
                    }


                    Charts.ChartSingles chartSingles = Charts.ofSingleSeries(chartTitle, value);

                    for (Map.Entry<String, Object> entry : charSubtMap.entrySet()) {
                        String key = entry.getKey();
                        List<Number> numbers = (List<Number>) entry.getValue();
                        chartSingles.series(key, numbers.toArray(new Number[]{}));
                    }

                    ChartSingleSeriesRenderData chartSingleSeriesRenderData = chartSingles.create();
                    element.put("name", "大象");
                    element.put("chart", chartSingleSeriesRenderData);
                    elements.add(element);
                }

                // 图表类型每个循环体只能有一个sql，对应一个循环标签：elements
                data.put(poiTemplate.getLabor(), elements);

                return XWPFTemplate.compile(poiTemplate.getTempPath()).render(data);
            }

            Map<Object, List<Map<String, Object>>> groupByType = resList.stream().collect(Collectors.groupingBy(map -> map.get("type")));
            for (Map.Entry<Object, List<Map<String, Object>>> objectListEntry : groupByType.entrySet()) {
                // 图表x，y轴数据集合
                List<ChartParam> series = new ArrayList<>();
                // 图表对象元素
                final Map<String, Object> element = new HashMap<>();
                // chart数据封装
                final Map<String, Object> charSubtMap = new HashMap<>();

                List<Map<String, Object>> values = objectListEntry.getValue();

                try {
                    values.forEach(map -> {
                        if (map.get(GRAPH_SERIES_NAME_PARAM) != null) {
                            series.add(new ChartParam(String.valueOf(map.get(GRAPH_SERIES_NAME_PARAM)), (Number) map.get(GRAPH_SERIES_VALUE_PARAM)));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("图表类型为：{} 的数据有误！", PoiTempEnum.GRAPH.toString());
                }

                // region =========
                /*  ============ 测试数据 ============= */
                    /*series.add(new ChartParam("幼象", 200));
                    series.add(new ChartParam("幼象", 300));
                    series.add(new ChartParam("幼象", 400));
                    series.add(new ChartParam("成象", 250));
                    series.add(new ChartParam("成象", 350));
                    series.add(new ChartParam("成象", 450));*/
                /*  ============ 测试数据 ============= */
                // endregion

                // ************** 拼接chart数据 start ****************
                for (ChartParam chartParam : series) {
                    Object valueObj = charSubtMap.get(chartParam.getName());
                    List<Number> valueList = null;
                    if (valueObj != null) {
                        valueList = (List<Number>) valueObj;
                    } else {
                        valueList = new ArrayList<>();
                    }
                    valueList.add(chartParam.getValue());
                    charSubtMap.put(chartParam.getName(), valueList);
                }
                //多系列图表
                Charts.ChartMultis chartMultis = Charts.ofMultiSeries(chartTitle, value);
                for (Map.Entry<String, Object> entry : charSubtMap.entrySet()) {
                    String key = entry.getKey();
                    List<Number> numbers = (List<Number>) entry.getValue();
                    chartMultis.addSeries(key, numbers.toArray(new Number[]{}));
                }
                ChartMultiSeriesRenderData chartMultiSeriesRenderData = chartMultis.create();
                // ********* 拼接chart数据 end *******************
                element.put("name", "大象");
                element.put("chart", chartMultiSeriesRenderData);
                elements.add(element);
            }
            data.put(poiTemplate.getLabor(), elements);

            return XWPFTemplate.compile(poiTemplate.getTempPath()).render(data);

        }

        // 循环表格数据
        if (poiTemplate.getTempType().equals(PoiTempEnum.LOOP_TABLE.toString())) {

            JudgeParamType.buildListValue(resList);

            data.put(poiTemplate.getLabor(), resList);

            if (PoiTemplate.isNotBlack(poiTemplate)) {
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
