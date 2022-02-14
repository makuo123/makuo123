package com.stock.mapper.poitl;

import com.stock.entity.poitl.PoiTemplate;
import com.stock.entity.poitl.PoiTemplateRef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PoitlMapper {
    List<Map<String, Object>> excute(String sql);

    String querySqlById(@Param("id") String id);

    PoiTemplate queryTempById(@Param("id") String id);

    List<PoiTemplate> queryByTaskId(String taskId);

    List<PoiTemplateRef> queryByRefPrimaryId(String id);
}
