import Home from '@/views/Home'
import NoMembers from '@/views/goods/NoMembers'
import BuyGoods from '@/views/goods/BuyGoods'
import NewsAddress from '@/views/goods/NewsAddress'
import AddressList from '@/views/goods/AddressList'
import ConfirmOrder from '@/views/goods/ConfirmOrder'
import PaySucess from '@/views/goods/PaySucess'
import GoodsPassword from '@/views/goods/GoodsPassword'
import ComfirmGoods from '@/views/goods/ComfirmGoods'
import GoodsSucess from '@/views/goods/GoodsSucess'
import JoinMembers from '@/views/goods/JoinMembers'
import JoinMembersSucess from '@/views/goods/JoinMembersSucess'
import SolarTerms from '@/views/solarterms/SolarTerms'
import Members from '@/views/members/Members'
import MyOrder from '@/views/members/MyOrder'
import ApplyMembers from '@/views/members/ApplyMembers'
import ApplySucess from '@/views/members/ApplySucess'


const routes = [
  // 首页 柿饼
  {
    path: '/home',
    name: '首页',
    component: Home
  },
  // 首页 柿饼
  {
    path: '/no-members',
    name: '不是会员',
    component: NoMembers
  },
  // 立即购买
  {
    path: '/buy-goods',
    name: '立即购买',
    component: BuyGoods
  },
  // 新增地址
  {
    path: '/news-address',
    name: '新增地址',
    component: NewsAddress
  },
  // 地址列表
  {
    path: '/address-list',
    name: '地址列表',
    component: AddressList
  },
  // 确认订单、
  {
    path: '/confirm-order',
    name: '确认订单、',
    component: ConfirmOrder
  },
  // 支付成功
  {
    path: '/pay-sucess',
    name: '支付成功',
    component: PaySucess
  },
  // 提货密码
  {
    path: '/goods-password',
    name: '提货密码',
    component: GoodsPassword
  },
  // 确认提货
  {
    path: '/comfirm-goods',
    name: '确认提货',
    component: ComfirmGoods
  },
  // 提货成功
  {
    path: '/goods-sucess',
    name: '提货成功',
    component: GoodsSucess
  },
  // 柿园
  {
    path: '/solar-terms',
    name: '柿园',
    component: SolarTerms
  },

  // 会员
  {
    path: '/members',
    name: '会员',
    component: Members
  },
  // 加入会员
  {
    path: '/join-members',
    name: '加入会员',
    component: JoinMembers
  },
  // 加入会员
  {
    path: '/join-members-sucess',
    name: '邀请码提交成功',
    component: JoinMembersSucess
  },
  // 申请会员
  {
    path: '/apply-members',
    name: '申请会员',
    component: ApplyMembers
  },
  // 申请会员成功
  {
    path: '/apply-sucess',
    name: '申请会员成功',
    component: ApplySucess
  },
  // 我的订单
  {
    path: '/my-order',
    name: '我的订单',
    component: MyOrder
  }, {
    path: '/',
    redirect: '/home'
  }


]
export default routes
