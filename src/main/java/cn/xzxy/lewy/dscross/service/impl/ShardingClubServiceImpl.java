package cn.xzxy.lewy.dscross.service.impl;

import cn.xzxy.lewy.dscross.common.annotation.DataSource;
import cn.xzxy.lewy.dscross.common.datasource.DataSourceType;
import cn.xzxy.lewy.dscross.mapper.TtShardingClubMapper;
import cn.xzxy.lewy.dscross.pojo.TtShardingClub;
import cn.xzxy.lewy.dscross.service.ShardingClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lewy95
 */
@Service
@Slf4j
public class ShardingClubServiceImpl implements ShardingClubService {

    @Resource
    private TtShardingClubMapper shardingClubMapper;

    @Override
    @DataSource(DataSourceType.SHARDING)
    public int insertBatch(List<TtShardingClub> shardingList) {
        return shardingClubMapper.insertBatch(shardingList);
    }

    @Override
    @DataSource(DataSourceType.SHARDING)
    public int insert(TtShardingClub sharding) {
        return shardingClubMapper.insert(sharding);
    }

    @Override
    @DataSource(DataSourceType.SHARDING)
    public List<TtShardingClub> selectAll() {
        return shardingClubMapper.selectAll();
    }
}
