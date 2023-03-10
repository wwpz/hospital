package top.xc27.hosp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xc27.hosp.repository.HospitalRepository;
import top.xc27.hosp.service.HospitalService;
import top.xc27.model.hosp.Hospital;

import java.util.List;

@Service("hospiatlService")
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public List<Hospital> getAllHosp() {
        Hospital hospital = new Hospital();
        hospital.setAddress("ss");
        hospitalRepository.insert(hospital);
        return hospitalRepository.findAll();
    }
}
