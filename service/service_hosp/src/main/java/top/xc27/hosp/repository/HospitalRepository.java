package top.xc27.hosp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.xc27.model.hosp.Hospital;
import top.xc27.model.hosp.HospitalSetEntity;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {
}
