  //
  const Home = resolve => require(['@/views/home/Home'], resolve)

  const Goods = r => require.ensure([], () => r(require(
      '@/views/goods-manage/Index')),
    'group-goods')

  const Batch = r => require.ensure([], () => r(require(
      '@/views/goods-manage/Batch')),
    'group-goods')

  const Content = resolve => require(['@/views/content-manage/Index'], resolve)

  const User = resolve => require(['@/views/user-manage/Index'], resolve)
  const ApplyFor = resolve => require(['@/views/user-manage/ApplyFor'], resolve)

  const Order = resolve => require(['@/views/order-manage/Index'], resolve)

  const Err404 = resolve => require(['@/views/error/404'], resolve);


  const routes = [
    // 商品管理
    {
      path: '/',
      component: Home,
      redirect: '/goods',
      name: '商品管理',
      iconCls: 'icon-goods',
      children: [
        //
        {
          path: '/goods',
          component: Goods,
          name: '商品管理'
        },
        //
        {
          path: '/batch',
          component: Batch,
          name: '批次管理'
        }
      ]
    },
    // 用户管理
    {
      path: '/',
      component: Home,
      iconCls: 'icon-user',
      name: '用户管理',
      // noDropdown: true, //只有一个节点
      children: [
        //
        {
          path: '/user',
          component: User,
          name: '会员管理'
        }, {
          path: '/applyfor',
          component: ApplyFor,
          name: '申请管理'
        }
      ]
    },
    // 内容管理
    {
      path: '/',
      component: Home,
      iconCls: 'icon-content',
      noDropdown: true, //只有一个节点
      children: [
        //
        {
          path: '/content',
          component: Content,
          name: '内容管理'
        }
      ]
    },
    // 订单管理
    {
      path: '/',
      component: Home,
      iconCls: 'icon-order',
      noDropdown: true, //只有一个节点
      children: [
        //
        {
          path: '/order',
          component: Order,
          name: '订单管理'
        }
      ]
    },
    // 空页面
    {
      path: '*',
      hidden: true,
      redirect: '/404'
    }
  ]


  export default routes
