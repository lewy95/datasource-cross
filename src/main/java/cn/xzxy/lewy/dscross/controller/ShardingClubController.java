package cn.xzxy.lewy.dscross.controller;

import cn.xzxy.lewy.dscross.common.model.JsonResponseEntity;
import cn.xzxy.lewy.dscross.pojo.TtShardingClub;
import cn.xzxy.lewy.dscross.service.ShardingClubService;
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
@RequestMapping("/rest/sharding/club")
@Slf4j
public class ShardingClubController {

    @Resource
    private ShardingClubService shardingClubService;

    @PostMapping("/insertBatch")
    public JsonResponseEntity insertBatch() {

        List<TtShardingClub> shardingList = new ArrayList<>();
        shardingList.add(new TtShardingClub("1", "Bayern Munchen", "GER", "MUNCHEN", 6));
        shardingList.add(new TtShardingClub("2", "Livepool", "ENG", "LIVEPOOL", 6));
        shardingList.add(new TtShardingClub("3", "Hamburger", "GER", "Hamburger", 1));
        shardingList.add(new TtShardingClub("4", "AC Milan", "ITY", "MILAN", 7));

        return JsonResponseEntity.buildOK(shardingClubService.insertBatch(shardingList));
    }

    @PostMapping("/insert")
    public JsonResponseEntity insert() {

        TtShardingClub shardingClub = new TtShardingClub("5", "Dortmund", "GER", "Dortmund", 1);

        return JsonResponseEntity.buildOK(shardingClubService.insert(shardingClub));
    }

    @PostMapping("/list")
    public JsonResponseEntity list() {

        return JsonResponseEntity.buildOK(shardingClubService.selectAll());
    }
}
