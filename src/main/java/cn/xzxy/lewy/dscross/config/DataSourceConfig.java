package cn.xzxy.lewy.dscross.config;

import cn.xzxy.lewy.dscross.common.datasource.DataSourceType;
import cn.xzxy.lewy.dscross.common.datasource.DynamicDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * （多）数据源配置
 * <p>
 * 步骤：yml中添加配置 >>> 注入@bean >>> 加入到targetDataSources中
 * 参考 presto 的配置
 *
 * @author lewy95
 */
@Configuration
public class DataSourceConfig {

    @Bean
    //@ConfigurationProperties("spring.datasource.druid.master") // v1 也可以配在application-druid.yml中
    @ConfigurationProperties("sharding.jdbc.datasource.master")
    public DataSource masterDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

//    @Bean
//    @ConfigurationProperties("sharding.jdbc.datasource.sharding1")
//    public DataSource shardingOneDataSource(DruidProperties druidProperties) {
//        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
//        return druidProperties.dataSource(dataSource);
//    }

    // Presto 数据源
    //@Bean
    //@ConfigurationProperties("spring.datasource.druid.presto")
    //@ConditionalOnProperty(prefix = "spring.datasource.druid.presto", name = "enabled", havingValue = "true") // 只有true才开启
    //public DataSource prestoDataSource(DruidProperties druidProperties) {
    //    DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
    //    return druidProperties.dataSource(dataSource);
    //}

    /**
     * 动态数据源配置
     * 需要将上面注入的数据源添加进来，参数名称必须对应
     *
     * @param masterDataSource 主库
     * @return DynamicDataSource
     */
//    @Bean(name = "dynamicDataSource")
//    @Primary
//    public DynamicDataSource dataSource(@Autowired DataSource masterDataSource) {
//                                        //@Autowired DataSource shardingOneDataSource) {
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
//        //targetDataSources.put(DataSourceType.SHARDING_1.name(), shardingOneDataSource);
//        //targetDataSources.put(DataSourceType.PRESTO.name(), prestoDataSource);
//        // 构建动态数据源
//        return new DynamicDataSource(masterDataSource, targetDataSources);
//    }

}
