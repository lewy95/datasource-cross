package cn.xzxy.lewy.dscross.common.datasource;

import cn.xzxy.lewy.dscross.config.CustomConfig;
import cn.xzxy.lewy.dscross.config.DruidProperties;
import cn.xzxy.lewy.dscross.mapper.TbDatasourceMapper;
import cn.xzxy.lewy.dscross.pojo.TbDatasource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.util.JdbcConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据源初始化类
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
    //@Resource
    //DataSource prestoDataSource;

    @PostConstruct
    public void loadCustomDataSource() {

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
//            if (StringUtils.isNotBlank(datasource.getDatabasePassword())) {
//                String[] pwdRes = SM4Utils.custParseEncryptHandler(datasource.getDatabasePassword());
//                try {
//                    String realPwd = SM4Utils.strDecode(pwdRes[0], pwdRes[1]);
//                    dataSource.setPassword(realPwd);
//                } catch (IOException e) {
//                    throw new BusinessException("初始化数据源连接异常");
//                }
//            }
            DataSource ds = druidProperties.dataSource(druidDataSource);
            // 以数据源ID作为Key来用于后期切换
            targetDataSources.put(datasource.getDatasourceId(), ds);
        }
        //将presto数据源加进去
        //targetDataSources.put(DataSourceType.PRESTO.name(), prestoDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.afterPropertiesSet();
    }
}
