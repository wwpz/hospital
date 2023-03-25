import request from '@/utils/request'

export default {
  queryDictByParentId(id){
    return request({
      url: '/cmn/dict/queryByParentId/' + id,
      method: 'GET'
    })
  },
}
