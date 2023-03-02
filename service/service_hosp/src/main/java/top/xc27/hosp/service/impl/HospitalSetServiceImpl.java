package top.xc27.hosp.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import top.xc27.common.exception.HospitalException;
import top.xc27.common.result.Result;
import top.xc27.common.result.ResultCode;
import top.xc27.common.utils.MD5;
import top.xc27.hosp.dao.HospitalSetDao;
import top.xc27.hosp.service.HospitalSetService;
import top.xc27.model.hosp.HospitalSetEntity;

import java.util.List;
import java.util.Random;

@Service("hospitalSetService")
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetDao, HospitalSetEntity> implements HospitalSetService {

    @Override
    public Result<String> saveHospitalSet(HospitalSetEntity hospitalSet) {
        cheak(hospitalSet);
        hospitalSet.setSignKey(MD5.encrypt(new Random().toString()));
        baseMapper.insert(hospitalSet);
        return Result.success();
    }

    @Override
    public Result<String> hospitalSetUpdate(HospitalSetEntity hospitalSet) {
        Assert.notEmpty(String.valueOf(hospitalSet.getId()), ResultCode.PRIMARY_EXCEPTION.getMessage());
        cheak(hospitalSet);
        baseMapper.updateById(hospitalSet);
        return Result.success();
    }

    private void cheak(HospitalSetEntity hospitalSet) {
        HospitalSetEntity hospByCode = baseMapper.selectByHospByCode(hospitalSet.getHosCode());
        HospitalSetEntity hospByName = baseMapper.selectByHospByName(hospitalSet.getHosName());
        if(ObjectUtil.isNotEmpty(hospByCode) && ObjectUtil.notEqual(hospByCode.getId(),hospitalSet.getId())){
            throw new HospitalException("当前医院编号已存在!");
        } else if(ObjectUtil.isNotEmpty(hospByName) && ObjectUtil.notEqual(hospByName.getId(),hospitalSet.getId())){
            throw new HospitalException("当前医院名称已存在!");
        }
    }

    @Override
    public Result<String> hospitalSetDeleteById(Long id) {
        Assert.notNull(id, ResultCode.PRIMARY_EXCEPTION.getMessage());
        baseMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result<String> hospitalSetDelete(List<Long> ids) {
        Assert.notEmpty(ids, ResultCode.PRIMARY_EXCEPTION.getMessage());
        baseMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @Override
    public IPage<HospitalSetEntity> queryPage(HospitalSetEntity hospitalSet) {
        LambdaQueryWrapper<HospitalSetEntity> wrapper = queryWrapper(hospitalSet);
        return baseMapper.selectPage(hospitalSet.getPage(Page::new),wrapper);
    }

    @Override
    public HospitalSetEntity hospitalSetById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public HospitalSetEntity hospitalSetSend(Long id) {
        HospitalSetEntity entity = baseMapper.selectById(id);
        Assert.notNull(entity,ResultCode.QUERYENTITY_EXCEPTION.getMessage());
        HospitalSetEntity hospitalSetEntity = new HospitalSetEntity();
        hospitalSetEntity.setHosCode(entity.getHosCode());
        hospitalSetEntity.setSignKey(entity.getSignKey());
        return hospitalSetEntity;
    }

    private LambdaQueryWrapper<HospitalSetEntity> queryWrapper(HospitalSetEntity hospitalSet) {
        LambdaQueryWrapper<HospitalSetEntity> query = Wrappers.lambdaQuery();
        if(StrUtil.isNotEmpty(hospitalSet.getHosName())){
            query.like(HospitalSetEntity::getHosName,hospitalSet.getHosName());
        }
        if(StrUtil.isNotEmpty(hospitalSet.getHosCode())){
            query.like(HospitalSetEntity::getHosCode,hospitalSet.getHosCode());
        }
        return query;
    }

}