import {
  SEL_ADDRESS
}
from './mutations_types.js'
import {
  setStore, getStore
}
from '../config/mUtils.js'

export default {
  // 选择地址
  [SEL_ADDRESS](state, {
    'item': item,
    'index': index,
    'orderNo': orderNo
  }) {
    let selAddressTemp = []
    selAddressTemp.push({
      'item': item,
      'index': index,
      'orderNo': orderNo
    })

    state.selAddress = selAddressTemp
      //存入localStorage
    setStore('selAddress', state.selAddress);
  },
}
