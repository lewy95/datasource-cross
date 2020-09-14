package cn.xzxy.lewy.dscross.service.impl;

import cn.xzxy.lewy.dscross.common.annotation.DataSource;
import cn.xzxy.lewy.dscross.common.datasource.DataSourceType;
import cn.xzxy.lewy.dscross.mapper.TtShardingMapper;
import cn.xzxy.lewy.dscross.mapper.TtShardingPlayerMapper;
import cn.xzxy.lewy.dscross.pojo.TtSharding;
import cn.xzxy.lewy.dscross.pojo.TtShardingPlayer;
import cn.xzxy.lewy.dscross.service.ShardingPlayerService;
import cn.xzxy.lewy.dscross.service.ShardingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lewy95
 */
@Service
@Slf4j
public class ShardingPlayerServiceImpl implements ShardingPlayerService {

    @Resource
    private TtShardingPlayerMapper shardingPlayerMapper;

    @Override
    @DataSource(DataSourceType.SHARDING)
    public int insertBatch(List<TtShardingPlayer> shardingList) {
        return shardingPlayerMapper.insertBatch(shardingList);
    }

    @Override
    @DataSource(DataSourceType.SHARDING)
    public int insert(TtShardingPlayer sharding) {
        return shardingPlayerMapper.insert(sharding);
    }

    @Override
    @DataSource(DataSourceType.SHARDING)
    public List<TtShardingPlayer> selectAll() {
        return shardingPlayerMapper.selectAll();
    }
}
