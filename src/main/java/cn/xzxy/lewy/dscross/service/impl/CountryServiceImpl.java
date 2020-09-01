package cn.xzxy.lewy.dscross.service.impl;

import cn.xzxy.lewy.dscross.common.exception.BusinessException;
import cn.xzxy.lewy.dscross.mapper.TbCountryMapper;
import cn.xzxy.lewy.dscross.pojo.TbCountry;
import cn.xzxy.lewy.dscross.service.CountryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("countryService")
public class CountryServiceImpl implements CountryService {

    @Resource
    TbCountryMapper countryMapper;

    @Override
    public TbCountry getCountry(int id) {

        TbCountry country = countryMapper.selectByPrimaryKey(id);
        if (country == null) {
            throw new BusinessException(10086, "无法查询到信息");
        }

        return country;
    }
}
