package com.stock.service.poitl;

import java.util.List;
import java.util.Map;

public interface PoitlService {
    List<Map<String, Object>> excute(String sql);

    String querySqlById(String id);
}
