package top.xc27.hosp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xc27.common.helper.HttpRequestHelper;
import top.xc27.common.result.Result;
import top.xc27.hosp.service.HospitalService;
import top.xc27.model.hosp.Hospital;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/saveHospital")
    public Result<String> saveHosp(HttpServletRequest request){
        Result<String> result = null;
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);
        return hospitalService.saveHosp(map);
    }

    @PostMapping("/hospital/show")
    public Result<String> showHosp(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        return Result.success();
    }

    @GetMapping("/all")
    public List<Hospital> allHospital(){
        return hospitalService.getAllHosp();
    }
}
