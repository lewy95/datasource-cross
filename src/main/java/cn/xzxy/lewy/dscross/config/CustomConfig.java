package cn.xzxy.lewy.dscross.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义配置类
 * @author lewy95
 */
@Configuration
public class CustomConfig {

    @Value("${dataBaseDriver.driver1}")
    private String driver1;
    @Value("${dataBaseDriver.driver2}")
    private String driver2;
    @Value("${dataBaseDriver.driver3}")
    private String driver3;
    @Value("${dataBaseDriver.driver4}")
    private String driver4;

    @Value("${datasource-cross.presto-name}")
    public String prestoName;

    private Map<String, String> driverMap = new HashMap<>();

    @Bean
    public Map<String, String> getDriver(){
        driverMap.put("1", driver1);
        driverMap.put("2", driver2);
        driverMap.put("3", driver3);
        driverMap.put("4", driver4);
        return driverMap;
    }

    @Value("${db-test-sql.sql1}")
    private String sql1;
    @Value("${db-test-sql.sql2}")
    private String sql2;
    @Value("${db-test-sql.sql3}")
    private String sql3;
    @Value("${db-test-sql.sql4}")
    private String sql4;

    private Map<String, String> sqlMap = new HashMap<>();

    @Bean
    public Map<String, String> getTestSql(){
        sqlMap.put("1", sql1);
        sqlMap.put("2", sql2);
        sqlMap.put("3", sql3);
        sqlMap.put("4", sql4);
        return sqlMap;
    }

    @Value("${db-tables-sql.sql1}")
    private String tableSql1;
    @Value("${db-tables-sql.sql2}")
    private String tableSql2;
    @Value("${db-tables-sql.sql3}")
    private String tableSql3;
    @Value("${db-tables-sql.sql4}")
    private String tableSql4;

    private Map<String, String> tableSqlMap = new HashMap<>();

    @Bean
    public Map<String, String> getTableSql(){
        tableSqlMap.put("1", tableSql1);
        tableSqlMap.put("2", tableSql2);
        tableSqlMap.put("3", tableSql3);
        tableSqlMap.put("4", tableSql4);
        return tableSqlMap;
    }

    @Value("${db-fileds-sql.sql1}")
    private String filedSql1;
    @Value("${db-fileds-sql.sql2}")
    private String filedSql2;
    @Value("${db-fileds-sql.sql3}")
    private String filedSql3;
    @Value("${db-fileds-sql.sql4}")
    private String filedSql4;

    private Map<String, String> filedSqlMap = new HashMap<>();

    @Bean
    public Map<String, String> getFiledSql(){
        filedSqlMap.put("1", filedSql1);
        filedSqlMap.put("2", filedSql2);
        filedSqlMap.put("3", filedSql3);
        filedSqlMap.put("4", filedSql4);
        return filedSqlMap;
    }
}
