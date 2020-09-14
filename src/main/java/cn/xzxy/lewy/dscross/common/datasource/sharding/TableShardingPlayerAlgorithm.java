package cn.xzxy.lewy.dscross.common.datasource.sharding;

import cn.hutool.core.util.HashUtil;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 分表算法 >>> 对应表 t_t_sharding_player
 *
 * @author lewy95
 */
public class TableShardingPlayerAlgorithm implements PreciseShardingAlgorithm<String> {
    private static Logger LOG = LoggerFactory.getLogger(TableShardingPlayerAlgorithm.class);

    /**
     * 该表每个库分2张表
     */
    @Override
    public String doSharding(Collection<String> names, PreciseShardingValue<String> value) {
        LOG.debug("分表算法参数 {},{}", names, value);
        int hash = HashUtil.rsHash(String.valueOf(value.getValue()));
        return "t_t_sharding_player_" + (hash % 2);
    }
}
