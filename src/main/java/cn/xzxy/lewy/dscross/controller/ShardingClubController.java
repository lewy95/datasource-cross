package cn.xzxy.lewy.dscross.controller;

import cn.xzxy.lewy.dscross.common.model.JsonResponseEntity;
import cn.xzxy.lewy.dscross.pojo.TtShardingClub;
import cn.xzxy.lewy.dscross.service.ShardingClubService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
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
@RequestMapping("/rest/sharding/club")
@Slf4j
public class ShardingClubController {

    @Resource
    private ShardingClubService shardingClubService;

    @Resource
    SnowflakeShardingKeyGenerator clubKeyGenerator;

    @PostMapping("/insertBatch")
    public JsonResponseEntity insertBatch() {

        List<TtShardingClub> shardingList = new ArrayList<>();
        shardingList.add(new TtShardingClub("C" + clubKeyGenerator.generateKey(), "Bayern Munchen", "GER", "MUNCHEN", 6));
        shardingList.add(new TtShardingClub("C" + clubKeyGenerator.generateKey(), "Liverpool", "ENG", "LIVERPOOL", 6));
        shardingList.add(new TtShardingClub("C" + clubKeyGenerator.generateKey(), "Hamburger", "GER", "Hamburger", 1));
        shardingList.add(new TtShardingClub("C" + clubKeyGenerator.generateKey(), "AC Milan", "ITY", "MILAN", 7));

        return JsonResponseEntity.buildOK(shardingClubService.insertBatch(shardingList));
    }

    /**
     * insert into on duplicate key
     * 注意：update后更新字段不能包括sharing-jdbc的分片键
     */
    @PostMapping("/insertUpdateBatch")
    public JsonResponseEntity insertDuplicateBatch() {

        List<TtShardingClub> shardingList = new ArrayList<>();
        shardingList.add(new TtShardingClub("C1", "Bayern Munchen", "GER", "MUNCHEN", 6));
        shardingList.add(new TtShardingClub("C2", "Liverpool", "ENG", "LIVERPOOL", 6));
        shardingList.add(new TtShardingClub("C3", "Hamburger", "GER", "Hamburger", 1));
        shardingList.add(new TtShardingClub("C4", "AC Milan", "ITY", "MILAN", 7));

        return JsonResponseEntity.buildOK(shardingClubService.insertUpdateBatch(shardingList));
    }

    @PostMapping("/insert")
    public JsonResponseEntity insert() {

        TtShardingClub shardingClub = new TtShardingClub("C" + clubKeyGenerator.generateKey(), "Dortmund", "GER", "Dortmund", 1);

        return JsonResponseEntity.buildOK(shardingClubService.insert(shardingClub));
    }

    @PostMapping("/list")
    public JsonResponseEntity list() {

        return JsonResponseEntity.buildOK(shardingClubService.selectAll());
    }
}
