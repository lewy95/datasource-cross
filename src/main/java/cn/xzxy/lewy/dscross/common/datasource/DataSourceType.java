package cn.xzxy.lewy.dscross.common.datasource;

/**
 * 数据源类型
 * @author lewy95
 */
public enum DataSourceType
{
    /**
     * 主库
     */
    MASTER,

    /**
     * 从库1，测试分库分库，读写分离
     */
    SHARDING_1,

    /**
     * presto
     */
    PRESTO,

    /**
     * 动态库，根据传入的数据源ID切换
     */
    DYNAMIC
}