package top.xc27.cmn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.xc27.model.dict.DictEntity;
import top.xc27.cmn.service.DictService;
import top.xc27.common.result.Result;
import top.xc27.common.result.ResultList;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 医院字典管理
 *
 * @author Pcling
 * @email lingcglib@163.com
 * @date 2023-03-02 16:29:30
 */
@RestController
@RequestMapping("/cmn/dict")
@Api(value = "cmn_医院字典管理", tags = {"cmn_医院字典管理"})
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 根据上级id获取子节点数据列表
     */
    @GetMapping("/queryByParentId/{id}")
    @ApiOperation(value = "根据上级id获取子节点数据列表")
    public Result<List<DictEntity>> queryByParentId(@PathVariable Integer id){
        return Result.success(dictService.queryByParentId(id));
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出dict数据")
    public void exportDict(HttpServletResponse response){
        dictService.exportDict(response);
    }
    /**
     * 新增 cmn_组织架构表
     */
    @PostMapping("/add")
    @ApiOperation(value = "cmn_组织架构表新增接口")
    public Result<String> dictAdd(@RequestBody DictEntity dict){
		dictService.save(dict);
        return Result.success();
    }

    /**
     * 修改 cmn_组织架构表
     */
    @PutMapping("/update")
    @ApiOperation(value = "cmn_组织架构表修改接口")
    public Result<String> dictUpdate(@RequestBody DictEntity dict){
        Assert.hasText(String.valueOf(dict.getId()), "需要修改的id必传!");
		dictService.updateById(dict);
        return Result.success();
    }

    /**
     * 删除 cmn_组织架构表
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "cmn_组织架构表删除接口")
    public Result<String> dictDelete(@RequestBody List<Long> ids){
//		dictService.dictDelete(ids);
        return Result.success();
    }

    /**
     * pageInfo 分页查询
     */
    @PostMapping("/queryPage")
    @ApiOperation(value = "cmn_组织架构表分页接口")
    public ResultList<DictEntity> queryPage(@RequestBody DictEntity dict){
        return ResultList.success(dictService.queryPage(dict));
    }
}
