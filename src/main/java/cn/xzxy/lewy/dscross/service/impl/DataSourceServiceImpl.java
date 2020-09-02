package cn.xzxy.lewy.dscross.service.impl;

import cn.xzxy.lewy.dscross.common.exception.BusinessException;
import cn.xzxy.lewy.dscross.dto.SaveDataSourceReq;
import cn.xzxy.lewy.dscross.mapper.TbDatasourceMapper;
import cn.xzxy.lewy.dscross.pojo.TbDatasource;
import cn.xzxy.lewy.dscross.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DataSourceServiceImpl implements DataSourceService {

    @Resource
    TbDatasourceMapper datasourceMapper;

    @Override
    public List<TbDatasource> getDataSourceList() {
        return datasourceMapper.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDataSource(SaveDataSourceReq param) {

        if ("1".equals(param.getConfigMetaFlag())) {
            if (StringUtils.isBlank(param.getMetaDsId())) {
                throw new BusinessException("元数据源不能为空");
            }
        } else {
            param.setMetaDsId("");
        }

        TbDatasource datasource = null;
        if (StringUtils.isBlank(param.getDatasourceId())) { // add
            datasource = new TbDatasource();
            BeanUtils.copyProperties(param, datasource);
            String dsId = UUID.randomUUID().toString();
            datasource.setDatasourceId(dsId);
            if ("2".equals(param.getDatasourceType())) {
                datasource.setStatus(1);
            } else {
                datasource.setStatus(0);
            }
            Date date = new Date();
            datasource.setCreateTime(date);
            datasource.setUpdateTime(date);
            datasourceMapper.insert(datasource);
            // 如果是元数据源，关联其业务数据源
            if ("2".equals(param.getDatasourceType())) {
                if (StringUtils.isBlank(param.getMappingDsId())) {
                    throw new BusinessException("元数据库对应的业务数据源Id不能为空");
                }
                TbDatasource tbDatasource = new TbDatasource();
                tbDatasource.setDatasourceId(param.getMappingDsId());
                tbDatasource.setConfigMetaFlag("1");
                tbDatasource.setMetaDsId(dsId); // 此时dsId为元数据源Id
                tbDatasource.setUpdateTime(new Date());
                datasourceMapper.updateByPrimaryKeySelective(tbDatasource);
            }
        } else { // edit
            log.info("pass");
        }
    }
}
