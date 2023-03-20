package top.xc27.hosp.service;

import org.springframework.data.domain.Page;
import top.xc27.common.result.Result;
import top.xc27.model.hosp.Schedule;
import top.xc27.vo.ScheduleQueryVo;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    //上传排班接口
    Result<String> save(Map<String, Object> paramMap);

    //查询排班接口
    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    //删除排班
    Result<String> remove(Map<String, Object> paramMap);

    Map<String, Object> getRuleSchedule(ScheduleQueryVo scheduleQueryVo);

    List<Schedule> getDetailSchedule(ScheduleQueryVo scheduleQueryVo);
}
