package cn.xzxy.lewy.dscross.common.datasource.sharding;

import cn.hutool.core.util.HashUtil;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 分表算法
 *
 * @author lewy95
 */
public class TableOneShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    private static Logger LOG = LoggerFactory.getLogger(TableOneShardingAlgorithm.class);

    /**
     * 该表每个库分2张表
     */
    @Override
    public String doSharding(Collection<String> names, PreciseShardingValue<String> value) {
        LOG.debug("分表算法参数 {},{}", names, value);
        int hash = HashUtil.rsHash(String.valueOf(value.getValue()));
        return "table_one_" + (hash % 5 + 1);
    }
}
