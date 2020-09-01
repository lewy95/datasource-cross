package cn.xzxy.lewy.dscross.controller;

import cn.xzxy.lewy.dscross.common.model.JsonResponseEntity;
import cn.xzxy.lewy.dscross.service.CommonService;
import cn.xzxy.lewy.dscross.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rest/datasource")
@Slf4j
public class DataSourceController {

    @Resource
    private CommonService commonService;

    @Resource
    private DataSourceService dataSourceService;

    @PostMapping("/list")
    public JsonResponseEntity getDsList() {

        return JsonResponseEntity.buildOK(dataSourceService.getDataSourceList());
    }

    // 测试数据源切换: Mysql
    @PostMapping("/convertMysql")
    public JsonResponseEntity convert2Mysql() {

        String dataSourceId = "211fda2a-d755-4eca-9915-524c21c54a94";
        String sql = "select * from city limit 10";
        return JsonResponseEntity.buildOK(commonService.selectByDataSource(dataSourceId, sql));
    }

    // 测试数据源切换: Oracle
    @PostMapping("/convertOracle")
    public JsonResponseEntity convert2Oracle() {

        String dataSourceId = "321fda2a-d755-4eca-9915-524c21c54a94";
        String sql = "select t.* from TB_BASE_COMMON t";
        return JsonResponseEntity.buildOK(commonService.selectByDataSource(dataSourceId, sql));
    }
}
