package cn.xzxy.lewy.dscross.service;

import cn.xzxy.lewy.dscross.pojo.TtSharding;

import java.util.List;

/**
 * @author lewy95
 */
public interface ShardingService {
    int insertBatch(List<TtSharding> shardingList);

    int insert(TtSharding sharding);

    List<TtSharding> selectAll();
}
