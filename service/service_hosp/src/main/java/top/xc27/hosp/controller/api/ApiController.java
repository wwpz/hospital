package top.xc27.hosp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xc27.common.helper.HttpRequestHelper;
import top.xc27.common.result.Result;
import top.xc27.common.result.ResultList;
import top.xc27.hosp.service.DepartmentService;
import top.xc27.hosp.service.HospitalService;
import top.xc27.model.hosp.Department;
import top.xc27.model.hosp.Hospital;
import top.xc27.vo.DepartmentQueryVo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/saveHospital")
    public Result<String> saveHosp(HttpServletRequest request){
        Result<String> result = null;
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);
        return hospitalService.saveHosp(map);
    }

    @PostMapping("/hospital/show")
    public Result<Hospital> showHosp(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);
        Hospital hospital = hospitalService.queryShow(map);
        return Result.success(hospital);
    }

    @PostMapping("/saveDepartment")
    public Result<String> saveDepartment(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);
        return hospitalService.saveDepartment(map);
    }

    //??????????????????
    @PostMapping("/department/list")
    public Result findDepartment(HttpServletRequest request) {
        //??????????????????????????????
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //????????????
        String hoscode = (String)paramMap.get("hoscode");
        //????????? ??? ???????????????
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1 : Integer.parseInt((String)paramMap.get("limit"));

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        //??????service??????
        Page<Department> pageModel = departmentService.findPageDepartment(page,limit,departmentQueryVo);
        return Result.success(pageModel);
    }

    //??????????????????
    @PostMapping("department/remove")
    public Result<String> removeDepartment(HttpServletRequest request) {
        //??????????????????????????????
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //???????????? ??? ????????????
        String hoscode = (String)paramMap.get("hoscode");
        String depcode = (String)paramMap.get("depcode");
        departmentService.remove(hoscode,depcode);
        return Result.success();
    }

    @GetMapping("/all")
    public List<Hospital> allHospital(){
        return hospitalService.getAllHosp();
    }
}
