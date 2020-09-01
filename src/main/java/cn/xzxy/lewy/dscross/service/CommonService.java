package cn.xzxy.lewy.dscross.service;

import java.util.List;
import java.util.Map;

/**
 * 通过服务类
 */
public interface CommonService {

    /**
     * 从presto中查询
     */
    public List<Map> selectByPresto(String sql);

    /**
     * 从指定数据源查询
     */
    public List<Map> selectByDataSource(String datasourceId, String sql);

    /**
     * 通用sql查询
     */
    public List<Map> selectBySql(String sql);

    /**
     * 通用sql执行
     */
    public Integer executeBySql(String sql);

}
