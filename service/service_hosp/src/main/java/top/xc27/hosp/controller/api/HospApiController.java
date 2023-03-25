package top.xc27.hosp.controller.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xc27.common.result.Result;
import top.xc27.hosp.service.HospitalService;
import top.xc27.model.hosp.Hospital;
import top.xc27.vo.HospitalQueryVo;

import java.util.List;

@RestController
@RequestMapping("/api/hosp/hospital")
public class HospApiController {

    @Autowired
    private HospitalService hospitalService;

    @ApiOperation(value = "查询医院列表")
    @PostMapping("findHospList")
    public Result findHospList(HospitalQueryVo hospitalQueryVo) {
        return hospitalService.queryPage(hospitalQueryVo);
    }

    @ApiOperation(value = "根据医院名称查询")
    @GetMapping("findByHosName/{hosname}")
    public Result findByHosName(@PathVariable String hosname) {
        List<Hospital> list = hospitalService.findByHosname(hosname);
        return Result.success(list);
    }
}
