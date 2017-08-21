import {
  SEL_ADDRESS,
  ADD_CART
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
  // 添加商品
  [ADD_CART](state, {
    'item': item
  }) {
    let addCartListTemp = []
    addCartListTemp.push({
      'item': item
    })

    state.cartList = addCartListTemp
      //存入localStorage
    setStore('addCartList', state.cartList);
  },
}
