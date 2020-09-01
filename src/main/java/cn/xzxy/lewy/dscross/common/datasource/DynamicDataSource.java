package cn.xzxy.lewy.dscross.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源
 * 继承 AbstractRoutingDataSource，定义自定义路由策略
 * 抽象方法 determineCurrentLookupKey() 决定使用哪个数据源
 * @author lewy95
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        // 定义路由策略
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}