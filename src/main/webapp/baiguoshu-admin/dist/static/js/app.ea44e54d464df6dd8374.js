webpackJsonp([8],{117:function(n,e){},120:function(n,e){n.exports={render:function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("div",{attrs:{id:"app"}},[t("transition",{attrs:{name:"fade",mode:"out-in"}},[t("router-view")],1)],1)},staticRenderFns:[]}},37:function(n,e,t){"use strict";var o,a=t(87),d=t.n(a),i=(o={goodList:"/admin/product/list",goodAdd:"/admin/product/add",goodUpdate:"/admin/product/update",goodDel:"/admin/product/del",detail:"/admin/product/detail",batchList:"/admin/batch/list",getGoodsInfo:"/admin/batch/getGoodsInfo",getGoodSpec:"/admin/batch/getGoodSpec",memberList:"/admin/member/list",memberAdd:"/admin/member/add",memberUpdate:"/admin/member/update",memberDel:"/admin/member/del",applyList:"/admin/apply/list",upload:"/file/upload",memberDetail:"/admin/member/detail",orderList:"/admin/order/list"},d()(o,"getGoodSpec","/admin/batch/getGoodSpec"),d()(o,"batchAdd","/admin/batch/add"),d()(o,"viewGoodsCode","/admin/batch/viewGoodsCode"),d()(o,"memberBuyInfo","/admin/member/buyInfo"),d()(o,"applyDetail","/admin/apply/detail"),d()(o,"applyCheck","/admin/apply/check"),d()(o,"orderDetail","/admin/order/detail"),d()(o,"orderSend","/admin/order/send"),d()(o,"orderSearch","/admin/order/search"),o);e.a={UrlConfig:i}},38:function(n,e,t){"use strict";var o=function(n){return t.e(3).then(function(){var e=[t(129)];n.apply(null,e)}.bind(this)).catch(t.oe)},a=function(n){return t.e(0).then(function(){return n(t(128))}.bind(null,t)).catch(t.oe)},d=function(n){return t.e(0).then(function(){return n(t(127))}.bind(null,t)).catch(t.oe)},i=function(n){return t.e(5).then(function(){var e=[t(125)];n.apply(null,e)}.bind(this)).catch(t.oe)},r=function(n){return t.e(1).then(function(){var e=[t(132)];n.apply(null,e)}.bind(this)).catch(t.oe)},c=function(n){return t.e(2).then(function(){var e=[t(131)];n.apply(null,e)}.bind(this)).catch(t.oe)},p=function(n){return t.e(4).then(function(){var e=[t(130)];n.apply(null,e)}.bind(this)).catch(t.oe)},u=[{path:"/",component:o,redirect:"/goods",name:"商品管理",iconCls:"icon-goods",children:[{path:"/goods",component:a,name:"商品管理"},{path:"/batch",component:d,name:"批次管理"}]},{path:"/",component:o,iconCls:"icon-user",name:"用户管理",children:[{path:"/user",component:r,name:"会员管理"},{path:"/applyfor",component:c,name:"申请管理"}]},{path:"/",component:o,iconCls:"icon-content",noDropdown:!0,children:[{path:"/content",component:i,name:"内容管理"}]},{path:"/",component:o,iconCls:"icon-order",noDropdown:!0,children:[{path:"/order",component:p,name:"订单管理"}]},{path:"*",hidden:!0,redirect:"/404"}];e.a=u},39:function(n,e){},41:function(n,e){},42:function(n,e,t){function o(n){t(117)}var a=t(44)(t(84),t(120),o,null,null);n.exports=a.exports},84:function(n,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={name:"app"}},85:function(n,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=t(3),a=t(42),d=t.n(a),i=t(43),r=t(38),c=t(39),p=t.n(c),u=t(37),l=t(40),m=t.n(l),s=t(41),h=(t.n(s),t(36)),f=t.n(h);o.default.config.productionTip=!1,o.default.prototype.$http=f.a,o.default.prototype.HttpUrl=u.a,o.default.use(m.a),o.default.use(i.a);var b=new i.a({routes:r.a});new o.default({router:b,store:p.a,render:function(n){return n(d.a)}}).$mount("#app")}},[85]);
//# sourceMappingURL=app.ea44e54d464df6dd8374.js.map