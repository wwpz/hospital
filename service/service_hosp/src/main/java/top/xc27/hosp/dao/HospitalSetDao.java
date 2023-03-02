package top.xc27.hosp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.xc27.model.hosp.HospitalSetEntity;

/**
 * 医院设置表
 * 
 * @author Pcling
 * @email lingcglib@163.com
 * @date 2023-02-23 11:03:17
 */
@Mapper
public interface HospitalSetDao extends BaseMapper<HospitalSetEntity> {

    HospitalSetEntity selectByHospByCode(String hosCode);

    HospitalSetEntity selectByHospByName(String hosName);
}
