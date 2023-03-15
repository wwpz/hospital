package top.xc27.cmn.client;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.xc27.common.result.Result;
import top.xc27.model.dict.DictEntity;

@FeignClient("service-cmn")
public interface DictFeignClient {

    @PostMapping("/cmn/dict/getDict")
    public DictEntity getDict(@RequestBody DictEntity dictEntity);
}
