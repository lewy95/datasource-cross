package cn.xzxy.lewy.dscross.mapper;

import cn.xzxy.lewy.dscross.pojo.TbDatasource;

public interface TbDatasourceMapper {
    int deleteByPrimaryKey(String datasourceId);

    int insert(TbDatasource record);

    int insertSelective(TbDatasource record);

    TbDatasource selectByPrimaryKey(String datasourceId);

    int updateByPrimaryKeySelective(TbDatasource record);

    int updateByPrimaryKey(TbDatasource record);
}