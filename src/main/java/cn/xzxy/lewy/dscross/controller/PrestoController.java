package cn.xzxy.lewy.dscross.controller;

import cn.xzxy.lewy.dscross.common.model.JsonResponseEntity;
import cn.xzxy.lewy.dscross.service.CommonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/presto")
public class PrestoController {

    @Resource
    private CommonService commonService;

    @GetMapping("/sqlTest")
    public JsonResponseEntity test() {
        String sql = "select 1";

        List<Map> resList = commonService.selectByPresto(sql);

        Map<String, Object> map = new HashMap<>();
        map.put("total",resList);

        return JsonResponseEntity.buildOK(map);
    }
}
