<style lang="less" scoped>
@import "../../../node_modules/vux/src/styles/weui/base/mixin/setOnepx.less";
body{
  background: #ccc
}
.my-order {
    position: relative;
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    padding: 10 11px;
    .myorder-box{
      background: #fff;
      padding: 10px;
      margin-bottom: 15px;
      .order-txt{
        position: relative;
        line-height: 40px;
        font-size: 14px;
        color: #666;
        &:after {
            .setBottomLine(#ccc);
        }
      }
      .img-box{
        padding: 10px 0;
        position: relative;
        overflow: hidden;
        padding-left: 90px;
        &:after {
            .setBottomLine(#ccc);
        }
        .img{
          width: 80px;
          height: 80px;
          display: block;
          float: left;
          margin-left: -90px;
        }
        .img-info{
          width: 100%;
          text-align: right;
          .txt{
            margin-top: 10px;
            font-size: 16px;
          }
          .total{
            margin-top: 10px;
            font-size: 16px;
            color:rgb(223, 75, 36);
          }
        }
      }
      .user-info{
        padding: 15px 0;
        font-size: 13px;
        color:#888;
      }
    }
}

</style>

<template lang="html">

<div class="my-order">

  <div class="myorder-box" v-for="(item,index) in items" :key="index">
      <div class="order-txt">订单编号:{{item.orderNo}} &nbsp;&nbsp;&nbsp;&nbsp;下单时间:{{item.orderTime}} &nbsp;&nbsp;</div>
      <div class="img-box">
          <img class="img" :src="item.goodIcon" alt="">
          <div class="img-info">
              <p class="txt">{{item.goodSpec}} x {{itsm.goodNum}}</p>
              <p class="total">合计¥{{item.orderPrice}}</p>
          </div>
      </div>
      <div class="user-info">
          <p>收货地址：{{item.address}}</p>
          <p>收货人:{{item.person}}</p>
          <p>电话号码：{{item.phone}}</p>
          <p>订单状态：{{item.orderState}}</p>
          <p>预计发货时间：{{item.sendTime}}</p>
      </div>
      <group>
          <cell title="修改收货信息" is-link value=""></cell>
      </group>
  </div>

</div>

</template>

<script>
import utils from '../../assets/js/urlConfig.js'
import {
    Group,
    Cell,
    Toast
}
from 'vux'
export default {
    components: {
        Group, Cell,Toast
    },
    data() {
        return {
            items: [],
            toastTxt: '',
            toastShow: false,
            page:'1'
        }
    },
    created() {
        document.title = "我的订单"
    },
    mounted() {

    },
    methods:{
      myorder(){
        var datas = {
            "openId":utils.LocalStorage.getStore('openId'),
            "page":this.page
        }
        this.$http.post(utils.UrlConfig.myorder,datas)
                      .then(res => {
                        var res = res.data
                          if(res.code=='1'){// 成功
                              this.items = res.list
                          }else{ // 异常
                            this.toastShow = true
                            this.toastTxt = res.msg
                          }

                      }).catch(error => {
                          console.log(error)
                      })
      }
    }
}

</script>
