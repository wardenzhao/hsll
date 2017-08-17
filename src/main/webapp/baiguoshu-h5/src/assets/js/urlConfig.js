const LocalStorage = {
  /**
   * 存储localStorage
   */
  setStore: (name, content) => {
    if (!name) return;
    if (typeof content !== 'string') {
      content = JSON.stringify(content);
    }
    window.localStorage.setItem(name, content);
  },
  /**
   * 获取localStorage
   */
  getStore: (name) => {
    if (!name) return;
    return window.localStorage.getItem(name);
  },
  /**
   * 删除localStorage
   */
  removeStore: (name) => {
    if (!name) return;
    window.localStorage.removeItem(name);
  }
}


let hostname = window.location.hostname,
  BASE_URL = ''

const UrlConfig = {
  isuser: BASE_URL + '/hsll/check/isuser', // 是否是会员
  register: BASE_URL + '/hsll/user/register', // 申请会员接口
  myorder: BASE_URL + '/hsll/myorder', // 我的订单
  takeGood: BASE_URL + '/hsll/take/good', // 提货接口
  getUserMessage: BASE_URL + '/hsll/getUserMessage', // 获取会员基本信息
  takeGood: BASE_URL + '/hsll/confirm/takeGood', // 确认提货
  inviteCode: BASE_URL + '/hsll/check/inviteCode', // 校验邀请码接口
  selAddress: BASE_URL + '/hsll/edit/address', // 新建/修改收货地址

};

export default {
  LocalStorage,
  UrlConfig
};
