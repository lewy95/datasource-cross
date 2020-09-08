package cn.xzxy.lewy.dscross.service.impl;

import cn.xzxy.lewy.dscross.common.annotation.DataSource;
import cn.xzxy.lewy.dscross.common.datasource.DataSourceType;
import cn.xzxy.lewy.dscross.mapper.TbDatasourceMapper;
import cn.xzxy.lewy.dscross.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Resource
    TbDatasourceMapper datasourceMapper;

    @Override
    @DataSource(DataSourceType.SHARDING_1)
    public List<Map> selectBySharding1(String sql) {
        return datasourceMapper.selectBySql(sql);
    }

    @Override
    @DataSource(DataSourceType.PRESTO)
    public List<Map> selectByPresto(String sql) {
        return datasourceMapper.selectBySql(sql);
    }

    @Override
    @DataSource(DataSourceType.DYNAMIC)
    public List<Map> selectByDataSource(String datasourceId, String sql) {
        return datasourceMapper.selectBySql(sql);
    }

    @Override
    public List<Map> selectBySql(String sql) {
        return datasourceMapper.selectBySql(sql);
    }

    @Override
    public Integer executeBySql(String sql) {
        return datasourceMapper.executeBySql(sql);
    }
}
