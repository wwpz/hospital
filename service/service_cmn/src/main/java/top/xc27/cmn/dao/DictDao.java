package top.xc27.cmn.dao;

import top.xc27.model.dict.DictEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 医院字典管理
 * 
 * @author Pcling
 * @email lingcglib@163.com
 * @date 2023-03-02 16:29:30
 */
@Mapper
public interface DictDao extends BaseMapper<DictEntity> {
	
}
