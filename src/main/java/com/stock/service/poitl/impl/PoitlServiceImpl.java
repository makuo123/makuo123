package com.stock.service.poitl.impl;

import com.stock.entity.poitl.PoiTemplate;
import com.stock.entity.poitl.PoiTemplateRef;
import com.stock.mapper.poitl.PoitlMapper;
import com.stock.service.poitl.PoitlService;
import com.stock.util.spi.ModuleClassLoader;
import com.stock.util.spi.classloader.ClassloaderResponsity;
import org.hc.entity.Result;
import org.hc.service.IReportDataService;
import org.hc.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PoitlServiceImpl implements PoitlService {

    @Value("${remote.jar.path}")
    private String remoteJarPath;

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

    @Override
    public PoiTemplate queryTempById(String id) {
        return poitlMapper.queryTempById(id);
    }

    @Override
    public List<PoiTemplate> queryByTaskId(String taskId) {
        return poitlMapper.queryByTaskId(taskId);
    }

    /**
     * 加载jar包，封装数据
     *
     * @param taskId
     */
    @Override
    public Result loadJar(String taskId) {
        Result result = new Result();
        // 封装模板预填数据
        Map<String, Object> data = new HashMap<>();
        String jarPath = remoteJarPath;
        File file = new File(jarPath);
        String moduleName = jarPath.substring(jarPath.lastIndexOf(File.separator) + 1, jarPath.lastIndexOf("."));
        try {

            if (ClassloaderResponsity.getInstance().containsClassLoader(moduleName)) {
                ClassloaderResponsity.getInstance().removeClassLoader(moduleName);
            }

            ModuleClassLoader moduleClassLoader = new ModuleClassLoader(new URL[]{file.toURI().toURL()}, Thread.currentThread().getContextClassLoader());
            Map<String, Class> cacheClass = moduleClassLoader.getCacheClass();

            Connection connection = JDBCUtils.getConnection();
            cacheClass.values().stream().forEach(value -> {
                try {
                    if (value != null && !value.getName().contains("$")) {
                        Object o = value.newInstance();
                        if (o instanceof IReportDataService) {
                            IReportDataService reportData = (IReportDataService) value.newInstance();
                            Result resultSub = reportData.collectData(connection, taskId);
                            System.out.println(resultSub.getData().get("name"));
                            if (resultSub != null){
                                result.setConfigure(resultSub.getConfigure());
                                data.putAll(resultSub.getData());
                            }
                        }
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // todo 关闭conn连接
        }
        result.setData(data);
        return result;
    }

    @Override
    public List<PoiTemplateRef> queryByRefPrimaryId(String id) {

        return poitlMapper.queryByRefPrimaryId(id);
    }


}
