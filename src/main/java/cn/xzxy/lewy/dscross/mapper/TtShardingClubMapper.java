package cn.xzxy.lewy.dscross.mapper;

import cn.xzxy.lewy.dscross.pojo.TtShardingClub;
import cn.xzxy.lewy.dscross.pojo.TtShardingPlayer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TtShardingClubMapper {
    int deleteByPrimaryKey(String clubId);

    int insert(TtShardingClub record);

    int insertSelective(TtShardingClub record);

    TtShardingClub selectByPrimaryKey(String clubId);

    int updateByPrimaryKeySelective(TtShardingClub record);

    int updateByPrimaryKey(TtShardingClub record);

    int insertBatch(@Param("list") List<TtShardingClub> shardingList);

    List<TtShardingClub> selectAll();
}