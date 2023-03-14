package top.xc27.hosp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import top.xc27.model.hosp.Department;
import top.xc27.model.hosp.Hospital;

public interface DepartmentRepository extends MongoRepository<Department,String> {
    //上传科室接口
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}
