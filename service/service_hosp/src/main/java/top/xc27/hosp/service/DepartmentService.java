package top.xc27.hosp.service;

import org.springframework.data.domain.*;
import top.xc27.model.hosp.Department;
import top.xc27.vo.DepartmentQueryVo;
import top.xc27.vo.DepartmentVo;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    //上传科室接口
    void save(Map<String, Object> paramMap);

    //查询科室接口
    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

    //删除科室接口
    void remove(String hoscode, String depcode);

    List<DepartmentVo> findDeptTree(String hoscode);

    String getDepName(String hoscode, String depcode);
}
