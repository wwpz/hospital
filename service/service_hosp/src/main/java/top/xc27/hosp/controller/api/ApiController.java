package top.xc27.hosp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xc27.hosp.service.HospitalService;
import top.xc27.model.hosp.Hospital;

import java.util.List;

@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/all")
    public List<Hospital> allHospital(){
        return hospitalService.getAllHosp();
    }
}
