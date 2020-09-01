package cn.xzxy.lewy.dscross.mapper;

import cn.xzxy.lewy.dscross.pojo.TbCountry;

public interface TbCountryMapper {
    int deleteByPrimaryKey(Short countryId);

    int insert(TbCountry record);

    int insertSelective(TbCountry record);

    TbCountry selectByPrimaryKey(Integer countryId);

    int updateByPrimaryKeySelective(TbCountry record);

    int updateByPrimaryKey(TbCountry record);
}