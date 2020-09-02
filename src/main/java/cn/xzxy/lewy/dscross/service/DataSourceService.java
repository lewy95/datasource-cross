package cn.xzxy.lewy.dscross.service;

import cn.xzxy.lewy.dscross.dto.SaveDataSourceReq;
import cn.xzxy.lewy.dscross.pojo.TbDatasource;

import java.util.List;

public interface DataSourceService {

    public List<TbDatasource> getDataSourceList();

    public void saveDataSource(SaveDataSourceReq param);
}
