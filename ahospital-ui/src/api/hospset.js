import request from '@/utils/request'

export default {
  queryHospById(id){
    return request({
      url: '/hosp/hospitalset/' + id,
      method: 'GET'
    })
  },
  getHospsetList(data) {
    return request({
      url: '/hosp/hospitalset/queryPage',
      method: 'POST',
      data: data
    })
  },
  updateHospset(data){
    return request({
      url: '/hosp/hospitalset/update',
      method: 'PUT',
      data: data
    })
  },
  removeHospsetById(val){
    return request({
      url: '/hosp/hospitalset/deletes/' + val,
      method: 'DELETE',
    })
  },
  removeHospsetByIds(data){
    return request({
      url: '/hosp/hospitalset/deletes',
      method: 'DELETE',
      data: data
    })
  },
  addHospset(data){
    return request({
      url: '/hosp/hospitalset/add',
      method: 'POST',
      data: data
    })
  }
}
