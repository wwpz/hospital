package top.xc27.cmn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import org.springframework.web.multipart.MultipartFile;
import top.xc27.cmn.dao.DictDao;
import top.xc27.cmn.listener.DictListener;
import top.xc27.common.exception.HospitalException;
import top.xc27.common.result.Result;
import top.xc27.common.result.ResultCode;
import top.xc27.model.dict.DictEntity;
import top.xc27.cmn.service.DictService;
import top.xc27.vo.DictEeVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictDao, DictEntity> implements DictService {

    @Override
    @Cacheable(value = "dict",keyGenerator = "keyGenerator")
    public List<DictEntity> queryByParentId(Integer id) {
        List<DictEntity> entities = baseMapper.selectList(new LambdaQueryWrapper<DictEntity>().eq(DictEntity::getParentId, id));
        for (DictEntity dict : entities) {
            boolean isChild = isChildren(dict.getId());
            dict.setHasChildren(isChild);
        }
        return entities;
    }

    @Override
    public void exportDict(HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = "dict";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<DictEntity> dictEntities = baseMapper.selectList(null);
        List<DictEeVo> dictEeVos = BeanUtil.copyToList(dictEntities, DictEeVo.class);
        try {
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("dict").doWrite(dictEeVos);
        } catch (IOException e) {
            throw new HospitalException("导出异常!");
        }
    }

    @Override
    public Result<String> importDict(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            throw new HospitalException(ResultCode.ERROR);
        }
        return Result.success();
    }

    private boolean isChildren(Integer id) {
        Long count = baseMapper.selectCount(new LambdaQueryWrapper<DictEntity>().eq(DictEntity::getParentId, id));
        return count > 0;
    }

    @Override
    public IPage<DictEntity> queryPage(DictEntity dict) {
        LambdaQueryWrapper<DictEntity> wrapper = queryWrapper(dict);
        return baseMapper.selectPage(dict.getPage(Page::new),wrapper);
    }

    private LambdaQueryWrapper<DictEntity> queryWrapper(DictEntity dict) {
        LambdaQueryWrapper<DictEntity> query = Wrappers.lambdaQuery();

        return query;
    }

}