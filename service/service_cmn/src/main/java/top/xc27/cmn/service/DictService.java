package top.xc27.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import top.xc27.model.dict.DictEntity;

import java.util.List;

/**
 * 医院设置管理
 *
 * @author Pcling
 * @email lingcglib@163.com
 * @date 2023-03-02 16:29:30
 */
public interface DictService extends IService<DictEntity> {

    IPage<DictEntity> queryPage(DictEntity dict);

    List<DictEntity> queryByParentId(Integer id);
}

