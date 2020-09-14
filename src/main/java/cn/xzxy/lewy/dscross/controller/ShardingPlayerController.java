package cn.xzxy.lewy.dscross.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.xzxy.lewy.dscross.common.model.JsonResponseEntity;
import cn.xzxy.lewy.dscross.config.SnowflakeConfig;
import cn.xzxy.lewy.dscross.pojo.TtShardingPlayer;
import cn.xzxy.lewy.dscross.service.ShardingPlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 分库分表 / 读写分离 测试
 */
@RestController
@RequestMapping("/rest/sharding/player")
@Slf4j
public class ShardingPlayerController {

    @Resource
    private ShardingPlayerService shardingPlayerService;

    @Resource
    private SnowflakeConfig snowflakeConfig;

    @PostMapping("/insertBatch")
    public JsonResponseEntity insertBatch() {

        List<TtShardingPlayer> shardingList = new ArrayList<>();

        shardingList.add(new TtShardingPlayer("P" + snowflakeConfig.snowflakeId(), "lewy", 32, 9, "POL", "ST"));
        shardingList.add(new TtShardingPlayer("P" + snowflakeConfig.snowflakeId(), "muller", 31, 25, "GER", "CAM"));
        shardingList.add(new TtShardingPlayer("P" + snowflakeConfig.snowflakeId(), "kimmich", 24, 32, "GER", "CDM"));
        shardingList.add(new TtShardingPlayer("P" + snowflakeConfig.snowflakeId(), "gnabry", 24, 7, "GER", "RW"));
        shardingList.add(new TtShardingPlayer("P" + snowflakeConfig.snowflakeId(), "leon", 24, 18, "GER", "CM"));

        return JsonResponseEntity.buildOK(shardingPlayerService.insertBatch(shardingList));
    }

    @PostMapping("/insert")
    public JsonResponseEntity insert() {

        TtShardingPlayer shardingPlayer = new TtShardingPlayer("P" + snowflakeConfig.snowflakeId(), "coman", 23, 29, "FRA", "LW");

        return JsonResponseEntity.buildOK(shardingPlayerService.insert(shardingPlayer));
    }

    @PostMapping("/list")
    public JsonResponseEntity list() {

        return JsonResponseEntity.buildOK(shardingPlayerService.selectAll());
    }
}
