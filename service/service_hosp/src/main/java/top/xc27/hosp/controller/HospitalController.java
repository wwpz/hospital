package top.xc27.hosp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xc27.common.result.Result;
import top.xc27.common.result.ResultList;
import top.xc27.hosp.service.HospitalService;
import top.xc27.vo.HospitalQueryVo;

/**
 * 医院管理
 *
 * @author Pcling
 * @email lingcglib@163.com
 * @date 2023-03-15 11:03:17
 */
@RestController
@RequestMapping("/hosp/hospital")
@Api(value = "hosp_医院管理", tags = {"hosp_医院管理"})
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    /**
     * pageInfo 分页查询
     */
    @PostMapping("/queryPage")
    @ApiOperation(value = "hosp_医院分页接口")
    public Result queryPage(@RequestBody HospitalQueryVo hospitalQueryVo){
        return Result.success(hospitalService.queryPage(hospitalQueryVo));
    }
}
