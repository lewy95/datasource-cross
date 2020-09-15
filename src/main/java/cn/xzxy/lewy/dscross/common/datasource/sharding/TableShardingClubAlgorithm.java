package cn.xzxy.lewy.dscross.common.datasource.sharding;

import cn.hutool.core.util.HashUtil;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 分表算法 >>> 对应表 t_t_sharding_club
 * 该类在具体操作数据库时才会用到，每一条记录都会入库都会实现这个类的doSharding方法
 *
 * @author lewy95
 */
public class TableShardingClubAlgorithm implements PreciseShardingAlgorithm<String> {
    private static Logger LOG = LoggerFactory.getLogger(TableShardingClubAlgorithm.class);

    /**
     * 该表每个库分2张表
     * @param names 表名的集合
     * @param value PreciseShardingValue(
     *              logicTableName=t_t_sharding_club,  // 逻辑表名
     *              columnName=club_id,                // 分片字段
     *              value=C512631521392197632          // 分片字段值
     *              )
     */
    @Override
    public String doSharding(Collection<String> names, PreciseShardingValue<String> value) {
        LOG.info("分表算法参数 {},{}", names, value);
        int hash = HashUtil.rsHash(String.valueOf(value.getValue()));
        return "t_t_sharding_club_" + (hash % 2);
    }
}
