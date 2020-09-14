package cn.xzxy.lewy.dscross.service;

import cn.xzxy.lewy.dscross.pojo.TtShardingPlayer;

import java.util.List;

/**
 * @author lewy95
 */
public interface ShardingPlayerService {

    int insertBatch(List<TtShardingPlayer> shardingList);

    int insert(TtShardingPlayer sharding);

    List<TtShardingPlayer> selectAll();
}
