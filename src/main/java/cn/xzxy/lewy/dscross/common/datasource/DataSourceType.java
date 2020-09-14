package cn.xzxy.lewy.dscross.common.datasource;

/**
 * 数据源类型
 *
 * @author lewy95
 */
public enum DataSourceType {

    /**
     * 主库
     */
    MASTER,

    /**
     * presto 数据源
     */
    PRESTO,

    /**
     * 动态库，根据传入的数据源ID切换
     */
    DYNAMIC,

    /**
     * sakila数据库，用于作为外部数据库测试
     */
    SAKILA,

    /**
     * 涉及分库分表时使用
     */
    SHARDING;
}