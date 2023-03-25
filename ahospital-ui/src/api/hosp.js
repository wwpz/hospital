import request from '@/utils/request'
import {data} from "autoprefixer";

export default {
  getHospList(data) {
    return request({
      url: '/hosp/hospital/queryPage',
      method: 'POST',
      data: data
    })
  },
  getDictByDictCode(dictCode) {
    return request({
      url: `/cmn/dict/getDictByDictCode/${dictCode}`,
      method: 'GET',
    })
  },
  queryByParentId(id) {
    return request({
      url: `/cmn/dict/queryByParentId/${id}`,
      method: 'GET',
    })
  },
  updateStatus(data){
    return request({
      url: "/hosp/hospital/updata",
      method: 'PUT',
      data: data
    })
  },
  //查看医院详情
  getHospById(id) {
    return request ({
      url: `/hosp/hospital/showHospDetail/${id}`,
      method: 'get'
    })
  },
  //查看医院科室
  getDeptByHoscode(hoscode) {
    return request ({
      url: `/hosp/department/getDeptList/${hoscode}`,
      method: 'get'
    })
  },
  //查询预约规则
  getScheduleRule(data) {
    return request ({
      url: '/hosp/schedule/getScheduleRule',
      method: 'POST',
      data : data
    })
  },
  //查询排班详情
  getScheduleDetail(data) {
    return request ({
      url: '/hosp/schedule/getScheduleDetail',
      method: 'POST',
      data : data
    })
  }
}
