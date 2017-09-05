// let BASE_URL = '/api';
let BASE_URL = '';


const UrlConfig = {
  // 商品列表
  goodList: BASE_URL + '/admin/product/list',
  // 商品新增
  goodAdd: BASE_URL + '/admin/product/add',
  // 商品修改
  goodUpdate: BASE_URL + '/admin/product/update',
  // 商品删除
  goodDel: BASE_URL + '/admin/product/del',
  // 商品详情
  detail: BASE_URL + '/admin/product/detail',
  // 批次新增
  batchList: BASE_URL + '/admin/batch/list',
  // 批次商品列表
  getGoodsInfo: BASE_URL + '/admin/batch/getGoodsInfo',
  // 获取商品规格
  getGoodSpec: BASE_URL + '/admin/batch/getGoodSpec',
  // 获取会员列表
  memberList: BASE_URL + '/admin/member/list',
  // 添加
  memberAdd: BASE_URL + '/admin/member/add',
  // 添加
  memberUpdate: BASE_URL + '/admin/member/update',
  // 删除
  memberDel: BASE_URL + '/admin/member/del',
  // 删除
  applyList: BASE_URL + '/admin/apply/list',
  // 上传
  upload: BASE_URL + '/file/upload',
  // 会员详情
  memberDetail: BASE_URL + '/admin/member/detail',
  // 订单列表
  orderList: BASE_URL + '/admin/order/list',
  // 规格列表
  getGoodSpec: BASE_URL + '/admin/batch/getGoodSpec',
  // 规格列表
  batchAdd: BASE_URL + '/admin/batch/add',
  // 提货码
  viewGoodsCode: BASE_URL + '/admin/batch/viewGoodsCode',
  // 会员购买记录
  memberBuyInfo: BASE_URL + '/admin/member/buyInfo',
  // 申请详情
  applyDetail: BASE_URL + '/admin/apply/detail',
  // 申请审核
  applyCheck: BASE_URL + '/admin/apply/check',
  // 订单详情
  orderDetail: BASE_URL + '/admin/order/detail',
  // 确认发货
  orderSend: BASE_URL + '/admin/order/send',
  // 搜索订单
  orderSearch: BASE_URL + '/admin/order/search',
}

export default {
  UrlConfig
}
