package top.xc27.cmn.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import top.xc27.cmn.dao.DictDao;
import top.xc27.model.dict.DictEntity;
import top.xc27.cmn.service.DictService;

import java.util.List;

@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictDao, DictEntity> implements DictService {

    @Override
    public List<DictEntity> queryByParentId(Integer id) {
        List<DictEntity> entities = baseMapper.selectList(new LambdaQueryWrapper<DictEntity>().eq(DictEntity::getParentId, id));
        for (DictEntity dict : entities) {
            boolean isChild = isChildren(dict.getId());
            dict.setHasChildren(isChild);
        }
        return entities;
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