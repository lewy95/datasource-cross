package cn.xzxy.lewy.dscross.service;

import cn.xzxy.lewy.dscross.pojo.TtShardingClub;

import java.util.List;

/**
 * @author lewy95
 */
public interface ShardingClubService {

    int insertBatch(List<TtShardingClub> shardingList);

    int insert(TtShardingClub sharding);

    List<TtShardingClub> selectAll();

    int insertUpdateBatch(List<TtShardingClub> shardingList);
}
