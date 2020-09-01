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
     * presto
     */
    PRESTO,

    /**
     * 动态库，根据传入的数据源ID切换
     */
    DYNAMIC
}