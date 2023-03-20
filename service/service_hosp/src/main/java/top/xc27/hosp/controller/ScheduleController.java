package top.xc27.hosp.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xc27.common.result.Result;
import top.xc27.hosp.service.ScheduleService;
import top.xc27.model.hosp.Schedule;
import top.xc27.vo.ScheduleQueryVo;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hosp/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    //根据医院编号 和 科室编号 ，查询排班规则数据
    @ApiOperation(value ="查询排班规则数据")
    @PostMapping("getScheduleRule")
    public Result getScheduleRule(@RequestBody ScheduleQueryVo scheduleQueryVo) {
        Map<String,Object> map = scheduleService.getRuleSchedule(scheduleQueryVo);
        return Result.success(map);
    }

    //根据医院编号 、科室编号和工作日期，查询排班详细信息
    @ApiOperation(value = "查询排班详细信息")
    @PostMapping("getScheduleDetail")
    public Result getScheduleDetail(@RequestBody ScheduleQueryVo scheduleQueryVo) {
        List<Schedule> list = scheduleService.getDetailSchedule(scheduleQueryVo);
        return Result.success(list);
    }
}
