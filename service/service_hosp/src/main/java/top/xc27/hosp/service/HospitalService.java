package top.xc27.hosp.service;

import top.xc27.common.result.Result;
import top.xc27.model.hosp.Hospital;
import top.xc27.vo.HospitalQueryVo;

import java.util.List;
import java.util.Map;

public interface HospitalService {

    List<Hospital> getAllHosp();

    Result<String> saveHosp(Map<String, Object> map);

    Hospital queryShow(Map<String, Object> map);

    Result<String> saveDepartment(Map<String, Object> map);

    Result queryPage(HospitalQueryVo hospitalQueryVo);

    Result<String> updataHosp(HospitalQueryVo hospitalQueryVo);

    Map<String, Object> getHospById(String id);

    String getHospName(String hoscode);

    List<Hospital> findByHosname(String hosname);
}
