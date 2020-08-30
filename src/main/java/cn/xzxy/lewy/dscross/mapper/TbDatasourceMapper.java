package cn.xzxy.lewy.dscross.mapper;

import cn.xzxy.lewy.dscross.pojo.TbDatasource;

import java.util.List;

public interface TbDatasourceMapper {
    int deleteByPrimaryKey(String datasourceId);

    int insert(TbDatasource record);

    int insertSelective(TbDatasource record);

    TbDatasource selectByPrimaryKey(String datasourceId);

    int updateByPrimaryKeySelective(TbDatasource record);

    int updateByPrimaryKey(TbDatasource record);

    List<TbDatasource> selectAll();
}