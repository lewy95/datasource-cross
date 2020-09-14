package cn.xzxy.lewy.dscross.mapper;

import cn.xzxy.lewy.dscross.pojo.TtShardingPlayer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TtShardingPlayerMapper {
    int deleteByPrimaryKey(String playerId);

    int insert(TtShardingPlayer record);

    int insertSelective(TtShardingPlayer record);

    TtShardingPlayer selectByPrimaryKey(String playerId);

    int updateByPrimaryKeySelective(TtShardingPlayer record);

    int updateByPrimaryKey(TtShardingPlayer record);

    int insertBatch(@Param("list") List<TtShardingPlayer> shardingList);

    List<TtShardingPlayer> selectAll();
}