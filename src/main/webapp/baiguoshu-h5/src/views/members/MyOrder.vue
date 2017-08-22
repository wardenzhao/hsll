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
    background: #efefef;
    height:100%;
    .myorder-box{
      background: #fff;
      padding: 10px;
      margin-bottom: 15px;
      .order-txt{
        position: relative;
        line-height: 20px;
        font-size: 14px;
        color: #666;
      }
      .order-txt2{
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
.no-order{
  padding: 100px;
  text-align: center;
  font-size: 18px;
}

</style>

<template lang="html">

<div class="my-order">
  <!-- {{items.length}} -->
  <div class=""  v-show="orderList">
    <div class="myorder-box" v-for="(item,index) in items" :key="index">
        <div class="order-txt">订单编号:{{item.orderNo}}</div>
        <div class="order-txt order-txt2">下单时间:{{item.orderTime}}</div>
        <div class="img-box">
            <img class="img" :src="item.goodIcon" alt="">
            <div class="img-info">
                <p class="txt">{{item.goodName}} x {{item.goodNum}}</p>
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
        <group v-if="item.orderState=='等待发货'">
            <cell title="修改收货信息" :link="'/address-list?orderNo='+item.orderNo"></cell>
        </group>
    </div>
  </div>
  <div class="" v-show="!orderList">
    <p class="no-order" v-text='noOrderTxt'></p>
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
import {
    setStore, getStore ,getUrlKey
}
from '../../config/mUtils'
export default {
    components: {
        Group, Cell,Toast
    },
    data() {
        return {
            noOrderTxt:'暂无订单',
            orderList:false,
            items: [],
            toastTxt: '',
            toastShow: false,
            page:'1',
            addressInfo:'修改收货信息',
            orderNo:''
        }
    },
    created() {
        document.title = "我的订单"
    },
    mounted() {
      // let selAddress = JSON.parse(getStore('selAddress'))
      // console.log(selAddress[0])
      // if(selAddress[0]){
      //   this.addressInfo = selAddress[0].item.address
      //   this.orderNo = selAddress[0].orderNo
      // }



      this.myorder()
    },
    methods:{
      myorder(){
        var datas = {
            "openId":getStore('openId'),
            "page":this.page
        }
        this.$http.post(this.HttpUrl.UrlConfig.myorder,datas)
                      .then(res => {
                        var res = res.data
                          if(res.code=='1'){// 成功
                              this.items = res.data
                              this.orderList = true
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
