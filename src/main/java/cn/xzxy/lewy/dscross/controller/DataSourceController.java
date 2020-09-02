package cn.xzxy.lewy.dscross.controller;

import cn.hutool.core.codec.Base64;
import cn.xzxy.lewy.dscross.common.model.JsonResponseEntity;
import cn.xzxy.lewy.dscross.dto.SaveDataSourceReq;
import cn.xzxy.lewy.dscross.service.CommonService;
import cn.xzxy.lewy.dscross.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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

        String dataSourceId = "493c46a2-1a09-4789-8930-6e48b87eae42";
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

    /**
     * 测试加解密
     * 流程：
     * 前端：生成一个密钥，通过密钥利用SM4对密码加密，将加密后的密文放在对象中，并将密钥放在httpHeader中一起传给后端
     * 后端：
     * 1.存储：将密钥+密文通过一定的形式组合起来，再用Base64加密后存储在数据库中
     * 2.解析：先Base64解析，拆分密钥和密文，通过密钥利用SM4对密码解密
     */
    @PostMapping("/save")
    public JsonResponseEntity saveDataSource(@RequestBody @Valid SaveDataSourceReq param, @RequestHeader HttpHeaders headers) {
        try {
            String secretKey = "";
            List<String> dynamicSecretKey = headers.get("dynamicSecretKey");
            if (dynamicSecretKey != null && dynamicSecretKey.size() > 0
                    && StringUtils.isNotBlank(param.getDatabasePassword())) {
                secretKey = dynamicSecretKey.get(0);
                String afterEncode = secretKey + param.getDatabasePassword();
                param.setDatabasePassword(Base64.encode(afterEncode));
            }
            dataSourceService.saveDataSource(param);
        } catch (Exception e) {
            return JsonResponseEntity.buildBusinessError(e.getMessage());
        }
        return JsonResponseEntity.buildOK("保存成功");
    }
}
