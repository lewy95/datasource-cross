package cn.xzxy.lewy.dscross.common.datasource.sharding;

import cn.hutool.core.util.HashUtil;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 数据库映射计算
 *
 * @author lewy95
 */
public class DataSourceShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    private static Logger LOG = LoggerFactory.getLogger(DataSourceShardingAlgorithm.class);

    @Override
    public String doSharding(Collection<String> names, PreciseShardingValue<String> value) {
        LOG.debug("分库算法参数 {},{}", names, value);
        int hash = HashUtil.rsHash(String.valueOf(value.getValue()));
        return "ds_" + ((hash % 2) + 2);
    }
}
