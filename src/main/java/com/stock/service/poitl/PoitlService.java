package com.stock.service.poitl;

import com.stock.entity.poitl.PoiTemplate;

import java.util.List;
import java.util.Map;

public interface PoitlService {
    List<Map<String, Object>> excute(String sql);

    String querySqlById(String id);

    PoiTemplate queryTempById(String id);

    List<PoiTemplate> queryByTaskId(String taskId);
}
