package cn.xzxy.lewy.dscross.controller;

import cn.xzxy.lewy.dscross.common.model.JsonResponseEntity;
import cn.xzxy.lewy.dscross.dto.CountryDetailReq;
import cn.xzxy.lewy.dscross.service.CommonService;
import cn.xzxy.lewy.dscross.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/rest/country")
@Slf4j
public class CountryController {

    @Resource
    private CountryService countryService;

    @Resource
    private CommonService commonService;

    // 从主库查询
    @PostMapping("/detail/ds1")
    //@ApiOperation(value = "查询国家详情", notes = "查询国家详情")
    //@OpLog(methodCode = "getCountry", methodName = "获取国家信息")
    public JsonResponseEntity getCountry(@RequestBody @Valid CountryDetailReq countryDetailReq) {

        return JsonResponseEntity.buildOK(countryService.getCountry(countryDetailReq.getCountryId()));
    }

    // 从sakila库查询
    @PostMapping("/detail/ds2")
    //@ApiOperation(value = "查询国家详情", notes = "查询国家详情")
    //@OpLog(methodCode = "getCountry", methodName = "获取国家信息")
    public JsonResponseEntity getCountry2(@RequestBody @Valid CountryDetailReq countryDetailReq) {

        String sql = "select * from country where country_id = " + countryDetailReq.getCountryId();
        return JsonResponseEntity.buildOK(commonService.selectBySakila(sql));
    }
}
