let hostname = window.location.hostname,
  BASE_URL = '/api'
  // BASE_URL = ''

const UrlConfig = {
  isuser: BASE_URL + '/hsll/check/isuser', // 是否是会员
  register: BASE_URL + '/hsll/user/register', // 申请会员接口
  myorder: BASE_URL + '/hsll/myorder', // 我的订单
  takeGood: BASE_URL + '/hsll/take/good', // 提货接口
  getUserMessage: BASE_URL + '/hsll/getUserMessage', // 获取会员基本信息
  confirmTakeGood: BASE_URL + '/hsll/confirm/takeGood', // 确认提货
  inviteCode: BASE_URL + '/hsll/check/inviteCode', // 校验邀请码接口
  selAddress: BASE_URL + '/hsll/edit/address', // 新建/修改收货地址
  getGoods: BASE_URL + '/hsll/getGoods', //
  buyGoods: BASE_URL + '/hsll/buy/goods', //  提交
  address: BASE_URL + '/hsll/get/address', //  地址列表
  getJq: BASE_URL + '/hsll/getJq', //  24节气图片
  changeOrderAddress: BASE_URL + '/hsll/changeOrderAddress', //  修改收货地址
  hsllPay: BASE_URL + '/hsll/pay', //  支付接口

};

export default {
  UrlConfig
};
