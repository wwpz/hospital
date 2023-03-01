package top.xc27.hosp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.xc27.common.result.Result;
import top.xc27.common.result.ResultList;
import top.xc27.hosp.service.HospitalSetService;
import top.xc27.model.hosp.HospitalSetEntity;

import java.util.List;

/**
 * 医院设置管理
 *
 * @author Pcling
 * @email lingcglib@163.com
 * @date 2023-02-23 11:03:17
 */
@RestController
@RequestMapping("/hosp/hospitalset")
@Api(value = "hosp_医院设置管理", tags = {"hosp_医院设置管理"})
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    /**
     * 新增 HospitalSetEntity
     */
    @PostMapping("/add")
    @ApiOperation(value = "hosp_医院设置管理新增接口")
    public Result<String> hospitalSetAdd(@RequestBody HospitalSetEntity hospitalSet){
        return hospitalSetService.saveHospitalSet(hospitalSet);
    }

    /**
     * 修改 hosp_医院设置管理
     */
    @PutMapping("/update")
    @ApiOperation(value = "hosp_医院设置管理修改接口")
    public Result<String> hospitalSetUpdate(@RequestBody HospitalSetEntity hospitalSet){
        return hospitalSetService.hospitalSetUpdate(hospitalSet);
    }

    /**
     * 删除 hosp_医院设置管理
     */
    @DeleteMapping("/deletes")
    @ApiOperation(value = "hosp_医院设置管理删除接口")
    public Result<String> hospitalSetDelete(@RequestBody List<Long> ids){
        return hospitalSetService.hospitalSetDelete(ids);
    }

    /**
     * 根据id查询 hosp_医院设置管理
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "hosp_医院设置管理ById查询")
    public Result<HospitalSetEntity> hospitalSetById(@PathVariable Long id){
        return Result.success(hospitalSetService.hospitalSetById(id));
    }

    /**
     * 查询ALL hosp_医院设置管理
     */
    @GetMapping("/all")
    @ApiOperation(value = "hosp_医院设置管理查询ALL")
    public Result<List<HospitalSetEntity>> hospitalSetAll(){
        return Result.success(hospitalSetService.list(null));
    }

    /**
     * pageInfo 分页查询
     */
    @PostMapping("/queryPage")
    @ApiOperation(value = "hosp_医院设置管理分页接口")
    public ResultList<HospitalSetEntity> queryPage(@RequestBody HospitalSetEntity hospitalSet){
        return ResultList.success(hospitalSetService.queryPage(hospitalSet));
    }

    /**
     * 根据id查询 hosp_医院设置管理并发送密钥短信
     */
    @GetMapping("/send/{id}")
    @ApiOperation(value = "hosp_医院设置管理ById查询并发送密钥短信")
    public Result<HospitalSetEntity> hospitalSetSend(@PathVariable Long id){
        return Result.success(hospitalSetService.hospitalSetSend(id));
    }

    /**
     * 测试
     */
    @GetMapping("/test")
    @ApiOperation(value = "hosp_医院设置管理Test")
    public Result<HospitalSetEntity> hospitalTest(){
        return Result.success();
    }
}
