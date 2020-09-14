package cn.xzxy.lewy.dscross.common.datasource;

import cn.xzxy.lewy.dscross.common.exception.BusinessException;
import cn.xzxy.lewy.dscross.config.CustomConfig;
import cn.xzxy.lewy.dscross.config.DataSourceConfig;
import cn.xzxy.lewy.dscross.config.DruidProperties;
import cn.xzxy.lewy.dscross.mapper.TbDatasourceMapper;
import cn.xzxy.lewy.dscross.pojo.TbDatasource;
import cn.xzxy.lewy.dscross.util.encrypt.SM4Utils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.util.JdbcConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据源初始化类
 * <br>
 * 说明：
 * 该类可有可无，其作用主要是支持从数据库中读取配置的数据源信息加载到动态数据源中。
 * 后面可以用datasourceId切换。
 *
 * @author lewy95
 */
@Component
public class DataSourceInit {

    @Resource
    private DruidProperties druidProperties;
    @Resource
    TbDatasourceMapper datasourceMapper;
    @Resource
    private DynamicDataSource dynamicDataSource;
    @Resource
    private CustomConfig customConfig;
    @Resource
    DataSource sakilaDataSource;
    @Resource
    DataSource masterDataSource;
    @Resource
    DataSource shardingOneDataSource;
    @Resource
    DataSourceConfig dataSourceConfig;


    @PostConstruct
    public void loadCustomDataSource() throws Exception {

        List<TbDatasource> datasourceList = datasourceMapper.selectAll();
        Map<Object, Object> targetDataSources = new HashMap<>();
        for (TbDatasource datasource : datasourceList) {
            DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
            String driverClassName = customConfig.getDriver().get(datasource.getDatabaseType());
            druidDataSource.setDriverClassName(driverClassName);
            if (StringUtils.isNotBlank(datasource.getDatabaseUrl())) {
                druidDataSource.setUrl(datasource.getDatabaseUrl());
            } else {
                druidDataSource.setUrl(String.format(datasource.getDatabaseUrl(), datasource.getDatabaseIp(), datasource.getDatabasePort(), datasource.getDatabaseLabel()));
            }
            //设置数据库类型
            String jdbcType = JdbcConstants.MYSQL;
            if ("1".equals(datasource.getDatabaseType())) {
                jdbcType = JdbcConstants.ORACLE;
            } else if ("2".equals(datasource.getDatabaseType())) {
                jdbcType = JdbcConstants.SQL_SERVER;
            } else if ("3".equals(datasource.getDatabaseType())) {
                jdbcType = JdbcConstants.MYSQL;
            } else if ("4".equals(datasource.getDatabaseType())) {
                jdbcType = JdbcConstants.HIVE;
            }
            druidDataSource.setDbType(jdbcType);
            druidDataSource.setUsername(datasource.getDatabaseUsername());
            druidDataSource.setPassword(datasource.getDatabasePassword());
            // 对库中密码进行解密，这里采用的国密4
            //if (StringUtils.isNotBlank(datasource.getDatabasePassword())) {
            //    passwordDecrypt(datasource.getDatabasePassword(), druidDataSource);
            //}
            DataSource ds = druidProperties.dataSource(druidDataSource);
            // 以数据源ID作为Key来用于后期切换
            targetDataSources.put(datasource.getDatasourceId(), ds);
        }
        //将presto数据源加进去
        //targetDataSources.put(DataSourceType.PRESTO.name(), prestoDataSource);
        targetDataSources.put(DataSourceType.SAKILA.name(), sakilaDataSource);
        targetDataSources.put(DataSourceType.SHARDING.name(), dataSourceConfig.getShardingDataSource(masterDataSource, shardingOneDataSource));
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.afterPropertiesSet();
    }

    /**
     * 隐私字段解密
     */
    private void passwordDecrypt(String origins, DruidDataSource druidDataSource) {
        String[] pwdRes = SM4Utils.custParseEncryptHandler(origins);
        try {
            String realPwd = SM4Utils.strDecode(pwdRes[0], pwdRes[1]);
            druidDataSource.setPassword(realPwd);
        } catch (IOException e) {
            throw new BusinessException("初始化数据源连接异常");
        }
    }
}
