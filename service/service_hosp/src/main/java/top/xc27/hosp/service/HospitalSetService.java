package top.xc27.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import top.xc27.common.result.Result;
import top.xc27.model.hosp.HospitalSetEntity;

import java.util.List;

/**
 * 医院设置表
 *
 * @author Pcling
 * @email lingcglib@163.com
 * @date 2023-02-23 11:03:17
 */
public interface HospitalSetService extends IService<HospitalSetEntity> {

    Result<String> saveHospitalSet(HospitalSetEntity hospitalSet);

    Result<String> hospitalSetUpdate(HospitalSetEntity hospitalSet);

    Result<String> hospitalSetDelete(List<Long> ids);

    IPage<HospitalSetEntity> queryPage(HospitalSetEntity hospitalSet);

    HospitalSetEntity hospitalSetById(Long id);

    HospitalSetEntity hospitalSetSend(Long id);
}

