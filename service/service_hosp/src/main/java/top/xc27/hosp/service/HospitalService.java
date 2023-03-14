package top.xc27.hosp.service;

import top.xc27.common.result.Result;
import top.xc27.model.hosp.Hospital;

import java.util.List;
import java.util.Map;

public interface HospitalService {

    List<Hospital> getAllHosp();

    Result<String> saveHosp(Map<String, Object> map);

    Hospital queryShow(Map<String, Object> map);

    Result<String> saveDepartment(Map<String, Object> map);
}
