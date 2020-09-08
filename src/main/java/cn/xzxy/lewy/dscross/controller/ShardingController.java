package cn.xzxy.lewy.dscross.controller;

import cn.xzxy.lewy.dscross.common.model.JsonResponseEntity;
import cn.xzxy.lewy.dscross.pojo.TtSharding;
import cn.xzxy.lewy.dscross.service.ShardingService;
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
@RequestMapping("/rest/sharding")
@Slf4j
public class ShardingController {

    @Resource
    private ShardingService shardingService;

    @PostMapping("/insertBatch")
    public JsonResponseEntity insertBatch() {

        List<TtSharding> shardingList = new ArrayList<>();
        shardingList.add(new TtSharding(1, "13814561310", "b1", "b2", "b3"));
        shardingList.add(new TtSharding(2, "13814561311", "b1", "b2", "b3"));
        shardingList.add(new TtSharding(3, "13814561312", "b1", "b2", "b3"));
        shardingList.add(new TtSharding(4, "13814561313", "b1", "b2", "b3"));
        shardingList.add(new TtSharding(5, "13814561314", "b1", "b2", "b3"));
        shardingList.add(new TtSharding(6, "13814561315", "b1", "b2", "b3"));

        return JsonResponseEntity.buildOK(shardingService.insertBatch(shardingList));
    }

    @PostMapping("/insert")
    public JsonResponseEntity insert() {

        TtSharding sharding = new TtSharding(6, "13814561315", "b1", "b2", "b3");

        return JsonResponseEntity.buildOK(shardingService.insert(sharding));
    }

    @PostMapping("/list")
    public JsonResponseEntity list() {

        return JsonResponseEntity.buildOK(shardingService.selectAll());
    }
}
