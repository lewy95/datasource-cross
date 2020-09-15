package cn.xzxy.lewy.dscross.common.datasource.sharding;

import cn.hutool.core.util.HashUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 分表算法 >>> 对应表 t_t_sharding_club >>>
 * <p>
 * 复合分表策略: 可用于多个分片键
 * 指定方式：
 *
 * @author lewy95
 */
public class ComplexTableShardingClubAlgorithm implements ComplexKeysShardingAlgorithm {

    private static Logger LOG = LoggerFactory.getLogger(ComplexTableShardingClubAlgorithm.class);

    /**
     * 该库下分两张表
     *
     * @param collection               表名的集合
     * @param complexKeysShardingValue ComplexKeysShardingValue(
     *                                 logicTableName=t_t_sharding_club, // 逻辑表名
     *                                 columnNameAndShardingValuesMap={name=[Bayern Munchen],
     *                                                                 nation=[GER]}, // 分片字段map
     *                                 columnNameAndRangeValuesMap={})
     */
    @Override
    public Collection<String> doSharding(Collection collection, ComplexKeysShardingValue complexKeysShardingValue) {

        LOG.info("分表算法参数 {},{}", collection, complexKeysShardingValue);

        List<String> physicsTables = new ArrayList<>();

        String physicsTable = complexKeysShardingValue.getLogicTableName() + "_";
        Map cvm = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
        if (cvm != null && cvm.get("name") != null && cvm.get("nation") != null) {
            List nameValueList = (List) cvm.get("name");
            List nationValueList = (List) cvm.get("nation");
            if (StringUtils.isNotBlank(nameValueList.get(0) + "")
                    && StringUtils.isNotBlank(nationValueList.get(0) + "")) {
                String uniqueKey = nameValueList.get(0) + "@@" + nationValueList.get(0);
                int bkdrHash = HashUtil.bkdrHash(uniqueKey);
                physicsTable += bkdrHash % 2;
            } else {
                physicsTable += "0";
            }
        } else {
            // 给一个默认的出错
            physicsTable += "0";
        }
        physicsTables.add(physicsTable);
        return physicsTables;
    }

}
