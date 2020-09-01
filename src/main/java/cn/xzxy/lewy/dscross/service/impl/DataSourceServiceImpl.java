package cn.xzxy.lewy.dscross.service.impl;


import cn.xzxy.lewy.dscross.mapper.TbDatasourceMapper;
import cn.xzxy.lewy.dscross.pojo.TbDatasource;
import cn.xzxy.lewy.dscross.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class DataSourceServiceImpl implements DataSourceService {

    @Resource
    TbDatasourceMapper datasourceMapper;

    @Override
    public List<TbDatasource> getDataSourceList() {
        return datasourceMapper.selectAll();
    }
}
