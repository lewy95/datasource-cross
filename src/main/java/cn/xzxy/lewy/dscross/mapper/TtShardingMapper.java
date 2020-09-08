package cn.xzxy.lewy.dscross.mapper;

import cn.xzxy.lewy.dscross.pojo.TtSharding;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TtShardingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TtSharding record);

    int insertSelective(TtSharding record);

    TtSharding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TtSharding record);

    int updateByPrimaryKey(TtSharding record);

    int insertBatch(@Param("list") List<TtSharding> shardingList);

    List<TtSharding> selectAll();
}