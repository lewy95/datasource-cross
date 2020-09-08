package cn.xzxy.lewy.dscross.service.impl;

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
    public int insertBatch(List<TtSharding> shardingList) {
        return shardingMapper.insertBatch(shardingList);
    }

    @Override
    public int insert(TtSharding sharding) {
        return shardingMapper.insert(sharding);
    }

    @Override
    public List<TtSharding> selectAll() {
        return shardingMapper.selectAll();
    }
}
