package top.xc27.hosp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xc27.common.exception.HospitalException;
import top.xc27.common.result.Result;
import top.xc27.common.utils.MD5;
import top.xc27.hosp.repository.HospitalRepository;
import top.xc27.hosp.service.DepartmentService;
import top.xc27.hosp.service.HospitalService;
import top.xc27.hosp.service.HospitalSetService;
import top.xc27.model.hosp.Hospital;
import top.xc27.model.hosp.HospitalSetEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("hospiatlService")
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalSetService hospitalSetService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<Hospital> getAllHosp() {
        Hospital hospital = new Hospital();
        hospital.setAddress("ss");
        hospitalRepository.insert(hospital);
        return hospitalRepository.findAll();
    }

    @Override
    public Result<String> saveHosp(Map<String, Object> map) {

        cheak(map);
        String logoData = (String)map.get("logoData");
        logoData = logoData.replaceAll(" ","+");
        map.put("logoData",logoData);

        String json = JSONObject.toJSONString(map);
        Hospital hospital = JSONObject.parseObject(json, Hospital.class);
        String hoscode = hospital.getHoscode();
        Hospital hospitalExist =  hospitalRepository.getHospitalByHoscode(hoscode);
        if(null != hospitalExist){
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }else {
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
        return Result.success();
    }

    @Override
    public Hospital queryShow(Map<String, Object> map) {
        String hoscode = cheak(map);
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }

    @Override
    public Result<String> saveDepartment(Map<String, Object> map) {
        cheak(map);
        //调用service的方法
        departmentService.save(map);
        return Result.success();
    }

    private String cheak(Map<String, Object> map) {
        String sign = (String) map.get("sign");
        String hoscode = (String) map.get("hoscode");
        HospitalSetEntity hospitalSet = hospitalSetService.getOne(new LambdaQueryWrapper<HospitalSetEntity>().eq(HospitalSetEntity::getHosCode, hoscode));
        String encrypt = MD5.encrypt(hospitalSet.getSignKey());
        if(!StrUtil.equals(sign,encrypt)) {
            throw new HospitalException("签名校验失败!");
        }
        return hoscode;
    }
}
