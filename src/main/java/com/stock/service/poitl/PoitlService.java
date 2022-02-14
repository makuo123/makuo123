package com.stock.service.poitl;

import com.stock.entity.poitl.PoiTemplate;
import com.stock.entity.poitl.PoiTemplateRef;
import org.hc.entity.Result;

import java.util.List;
import java.util.Map;

public interface PoitlService {
    List<Map<String, Object>> excute(String sql);

    String querySqlById(String id);

    PoiTemplate queryTempById(String id);

    List<PoiTemplate> queryByTaskId(String taskId);

    Result loadJar(String taskId);

    List<PoiTemplateRef> queryByRefPrimaryId(String id);
}
