package cn.xzxy.lewy.dscross.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lewy95
 */
@Data
//@ApiModel("国家详情入参")
public class CountryDetailReq {

    //@ApiModelProperty("国家ID")
    @NotNull(message = "countryId不能为空")
    Integer countryId;
}
