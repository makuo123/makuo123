package com.stock.util;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.ChartMultiSeriesRenderData;
import com.deepoove.poi.data.ChartSingleSeriesRenderData;
import com.deepoove.poi.data.Charts;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.stock.entity.poitl.*;
import com.stock.enums.poitl.PoiTempEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class PoitlConfigUtil {

    public static final String GRAPH_SERIES_NAME_PARAM = "name";
    public static final String GRAPH_SERIES_VALUE_PARAM = "value";
    public static final String GRAPH_SERIES_TYPE_PARAM = "type";

    /**
     * 封装模板数据
     * @param poiTemplate   定义的模板信息
     * @param data          封装的完整模板数据
     * @param resList       子模板预填数据
     * @param refDataList
     * @return
     */
    public static Configure buildData(PoiTemplate poiTemplate, Map<String, Object> data, List<Map<String, Object>> resList, List<PoiTemplateRefData> refDataList) {

        // 图表数据
        if (poiTemplate.getTempType().endsWith(PoiTempEnum.GRAPH.toString())) {
            // 循环内的元素集合
            final List<Map<String, Object>> elements = new ArrayList<>();
            // 图表title
            String chartTitle = "分布图";

            List<PoiTemplateRefData> xRefList = refDataList.stream().filter(refTemp -> refTemp.getDataType() == 1).collect(Collectors.toList());
            // x轴定义范围
            String[] x_value = xRefList.get(0).getData().toArray(new String[]{});
            //String[] x_value = {"2008", "2009", "2010"};

            // 测试数据 todo 正式数据删除这段代码
            TestData.buidTestData(resList);

            // 单系列图表
            if (!poiTemplate.getTempType().startsWith(PoiTempEnum.MULTIS.toString())) {

                Map<Object, List<Map<String, Object>>> groupByType = resList.stream().collect(Collectors.groupingBy(map -> map.get(GRAPH_SERIES_TYPE_PARAM)));
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


                    Charts.ChartSingles chartSingles = Charts.ofSingleSeries(chartTitle, x_value);

                    for (Map.Entry<String, Object> entry : charSubtMap.entrySet()) {
                        String key = entry.getKey();
                        List<Number> numbers = (List<Number>) entry.getValue();
                        chartSingles.series(key, numbers.toArray(new Number[]{}));
                    }

                    ChartSingleSeriesRenderData chartSingleSeriesRenderData = chartSingles.create();

                    // ********* 拼接chart数据 end *******************

                    element.put("name", String.valueOf(objectListEntry.getKey()));
                    element.put("chart", chartSingleSeriesRenderData);
                    elements.add(element);
                }

                // 图表类型每个循环体只能有一个sql，对应一个循环标签：elements
                data.put(poiTemplate.getLabor(), elements);

                return null;
            }

            // 多系列图表
            Map<Object, List<Map<String, Object>>> groupByType = resList.stream().collect(Collectors.groupingBy(map -> map.get(GRAPH_SERIES_TYPE_PARAM)));
            for (Map.Entry<Object, List<Map<String, Object>>> objectListEntry : groupByType.entrySet()) {
                // 图表x，y轴数据集合
                List<ChartParam> series = new ArrayList<>();
                // 图表对象元素
                final Map<String, Object> element = new HashMap<>();
                // chart数据封装
                final Map<String, Object> chartSubMap = new HashMap<>();

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

                // ************** 拼接chart数据 start ****************
                for (ChartParam chartParam : series) {
                    Object valueObj = chartSubMap.get(chartParam.getName());
                    List<Number> valueList = null;
                    if (valueObj != null) {
                        valueList = (List<Number>) valueObj;
                    } else {
                        valueList = new ArrayList<>();
                    }
                    valueList.add(chartParam.getValue());
                    chartSubMap.put(chartParam.getName(), valueList);
                }
                //多系列图表
                String subChartTitle = String.valueOf(objectListEntry.getKey()) + chartTitle;
                Charts.ChartMultis chartMultis = Charts.ofMultiSeries(subChartTitle, x_value);
                for (Map.Entry<String, Object> entry : chartSubMap.entrySet()) {
                    String key = entry.getKey();
                    List<Number> numbers = (List<Number>) entry.getValue();
                    chartMultis.addSeries(key, numbers.toArray(new Number[]{}));
                }
                ChartMultiSeriesRenderData chartMultiSeriesRenderData = chartMultis.create();
                // ********* 拼接chart数据 end *******************
                element.put("name", String.valueOf(objectListEntry.getKey()));
                element.put("chart", chartMultiSeriesRenderData);
                elements.add(element);
            }
            data.put(poiTemplate.getLabor(), elements);

            return null;

        }

        // 循环表格数据
        if (poiTemplate.getTempType().equals(PoiTempEnum.LOOP_TABLE.toString())) {

            JudgeParamType.buildListValue(resList);

            data.put(poiTemplate.getLabor(), resList);

            if (PoiTemplate.isNotBlack(poiTemplate)) {
                HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();

                Configure config = Configure.builder()
                        .bind(poiTemplate.getLabor(), policy).build();
                return config;
            }
            return null;
        }

        // 文本/普通表格数据
        if (PoiTemplate.isNotBlack(poiTemplate)
                && (poiTemplate.getTempType().equals(PoiTempEnum.TEXT.toString())
                || poiTemplate.getTempType().equals(PoiTempEnum.TABLE.toString())))
        {
            resList.forEach(map -> {
                Set<Map.Entry<String, Object>> entries = map.entrySet();
                entries.forEach(entry -> {
                    map.put(entry.getKey(), JudgeParamType.buildValue(entry.getValue()));
                });

                data.putAll(map);
            });
        }
        return null;
    }

}
