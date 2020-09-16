package cn.xzxy.lewy.dscross.dto;

import lombok.Data;

/**
 * @author lewy95
 */
@Data
//@ApiModel("球员列表入参")
public class PlayerListReq {

    String playerName;
    String nation;
    String position;
}
