package cn.xzxy.lewy.dscross.config;

import cn.xzxy.lewy.dscross.common.datasource.DataSourceType;
import cn.xzxy.lewy.dscross.common.datasource.DynamicDataSource;
import cn.xzxy.lewy.dscross.common.datasource.sharding.TableShardingClubAlgorithm;
import cn.xzxy.lewy.dscross.common.datasource.sharding.TableShardingPlayerAlgorithm;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * （多）数据源配置：包含动态数据源和Sharding-jdbc数据源的配置
 * <br>
 * 两个可以配置数据源的地方：
 * 1. application-sharding.yml:
 * 用于配置分库分表相关的数据源，配置在其中的数据源可以不需要在该类中手动注入，sharding-jdbc会自动注入，
 * 但自动注入无法指定名称，后期引用会混乱。因此，推荐还是在该类中进入手动配置。
 * 2. application-druid.yml:
 * 用于配置别分库分表外的其他数据源（即与主表无主从关系），比如 Presto / Hive 等，配置在其中的数据需要在该类中手动注入，
 * 注入方式：druid-yml中添加配置 >>> 注入@bean >>> 加入到dynamicDataSource的targetDataSources中 >>> 在DataSourceInit类注入并添加
 * 可以参考 presto 的配置。
 *
 * @author lewy95
 */
@Configuration
public class DataSourceConfig {

    //---------------------------注入数据源开始-----------------------------------
    // 主库
    @Bean
    //@ConfigurationProperties("spring.datasource.druid.master")
    @ConfigurationProperties("sharding.jdbc.datasource.master")
    public DataSource masterDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    // 从库
    @Bean
    //@ConfigurationProperties("spring.datasource.druid.sharding1")
    @ConfigurationProperties("sharding.jdbc.datasource.sharding1")
    public DataSource shardingOneDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    // 其他数据源，区别于sharding中的，以sakila库为例
    @Bean
    @ConfigurationProperties("spring.datasource.druid.sakila")
    public DataSource sakilaDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    // Presto 数据源
    //@Bean
    //@ConfigurationProperties("spring.datasource.druid.presto")
    //@ConditionalOnProperty(prefix = "spring.datasource.druid.presto", name = "enabled", havingValue = "true") // 只有true才开启
    //public DataSource prestoDataSource(DruidProperties druidProperties) {
    //    DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
    //    return druidProperties.dataSource(dataSource);
    //}

    //---------------------------注入数据源结束-----------------------------------

    /**
     * 动态数据源配置
     * 需要将上面注入的数据源添加进来，参数名称必须对应
     * <br>
     *
     * @param masterDataSource      主库
     * @param shardingOneDataSource 从库1
     * @param sakilaDataSource      其他库（与主库无主从关系）
     * @return DynamicDataSource
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dynamicDataSource(@Autowired DataSource masterDataSource,
                                               @Autowired DataSource shardingOneDataSource,
                                               @Autowired DataSource sakilaDataSource) throws Exception {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
        targetDataSources.put(DataSourceType.SHARDING.name(), getShardingDataSource(masterDataSource, shardingOneDataSource));
        targetDataSources.put(DataSourceType.SAKILA.name(), sakilaDataSource);
        //targetDataSources.put(DataSourceType.PRESTO.name(), prestoDataSource);
        // 构建动态数据源
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

    /**
     * Sharding-JDBC 分库分表配置
     *
     * @param masterDataSource      主库
     * @param shardingOneDataSource 从库1
     * @return dataSource
     */
    //@Bean(name = "shardingDataSource")
    //@Qualifier("shardingDataSource")
    public DataSource getShardingDataSource(DataSource masterDataSource,
                                            DataSource shardingOneDataSource) throws Exception {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        // 添加表规则
        shardingRuleConfiguration.getTableRuleConfigs().add(getTableShardingPlayerRule()); // t_t_sharding_player
        shardingRuleConfiguration.getTableRuleConfigs().add(getTableShardingClubRule()); // t_t_sharding_club
        shardingRuleConfiguration.setDefaultDataSourceName("ds_0");
        Map<String, DataSource> dataMap = new LinkedHashMap<>();
        dataMap.put("ds_0", masterDataSource);
        //dataMap.put("ds_1", shardingOneDataSource);
        //dataMap.put("ds_2", shardingTwoDataSource);
        //dataMap.put("ds_3", shardingThreeDataSource);
        Properties prop = new Properties();
        return ShardingDataSourceFactory.createDataSource(dataMap, shardingRuleConfiguration, new ConcurrentHashMap<>(), prop);
    }

    /**
     * Shard-JDBC 分表规则配置，针对表 t_t_sharding_player
     */
    private static TableRuleConfiguration getTableShardingPlayerRule() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_t_sharding_player");
        //result.setActualDataNodes("ds_${2..3}.table_one_${1..5}"); // 分库
        result.setActualDataNodes("ds_0.t_t_sharding_player_${0..1}"); // 不分库
        // 指定分库规则
        //result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("phone", new DataSourceAlg()));
        // 指定分表规则
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("player_id", new TableShardingPlayerAlgorithm()));
        return result;
    }

    /**
     * Shard-JDBC 分表规则配置，针对表 t_t_sharding_club
     */
    private static TableRuleConfiguration getTableShardingClubRule() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_t_sharding_club");
        //result.setActualDataNodes("ds_${2..3}.table_one_${1..5}"); // 分库
        result.setActualDataNodes("ds_0.t_t_sharding_club_${0..1}"); // 不分库
        // 指定分库规则
        //result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("phone", new DataSourceAlg()));
        // 指定分表规则
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("club_id", new TableShardingClubAlgorithm()));
        return result;
    }

}
