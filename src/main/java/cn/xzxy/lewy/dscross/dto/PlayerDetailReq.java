package cn.xzxy.lewy.dscross.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lewy95
 */
@Data
//@ApiModel("球员详情入参")
public class PlayerDetailReq {

    //@ApiModelProperty("球员ID")
    @NotNull(message = "playerId不能为空")
    String playerId;
}
