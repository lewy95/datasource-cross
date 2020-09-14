package cn.xzxy.lewy.dscross.service.impl;

import cn.xzxy.lewy.dscross.common.annotation.DataSource;
import cn.xzxy.lewy.dscross.common.datasource.DataSourceType;
import cn.xzxy.lewy.dscross.mapper.TtShardingMapper;
import cn.xzxy.lewy.dscross.pojo.TtSharding;
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
public class ShardingServiceImpl implements ShardingService {

    @Resource
    private TtShardingMapper shardingMapper;

    @Override
    @DataSource(DataSourceType.SHARDING)
    public int insertBatch(List<TtSharding> shardingList) {
        return shardingMapper.insertBatch(shardingList);
    }

    @Override
    @DataSource(DataSourceType.SHARDING)
    public int insert(TtSharding sharding) {
        return shardingMapper.insert(sharding);
    }

    @Override
    @DataSource(DataSourceType.SHARDING)
    public List<TtSharding> selectAll() {
        return shardingMapper.selectAll();
    }
}
