package com.stock.service.poitl.impl;

import com.stock.mapper.poitl.PoitlMapper;
import com.stock.service.poitl.PoitlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PoitlServiceImpl implements PoitlService {

    @Autowired
    private PoitlMapper poitlMapper;

    @Override
    public List<Map<String, Object>> excute(String sql) {
        return poitlMapper.excute(sql);
    }

    @Override
    public String querySqlById(String id) {
        return poitlMapper.querySqlById(id);
    }


}
