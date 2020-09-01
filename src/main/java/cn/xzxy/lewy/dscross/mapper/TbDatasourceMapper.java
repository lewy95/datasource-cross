package cn.xzxy.lewy.dscross.mapper;

import cn.xzxy.lewy.dscross.pojo.TbDatasource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbDatasourceMapper {
    int deleteByPrimaryKey(String datasourceId);

    int insert(TbDatasource record);

    int insertSelective(TbDatasource record);

    TbDatasource selectByPrimaryKey(String datasourceId);

    int updateByPrimaryKeySelective(TbDatasource record);

    int updateByPrimaryKey(TbDatasource record);

    List<TbDatasource> selectAll();

    List<Map> selectBySql(@Param("sql") String sql);

    Integer executeBySql(@Param("sql") String sql);
}