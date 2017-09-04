<style lang="less">
.pay-sucess {
  padding: 80px 11px;
  .img{
    width: 60px;
    height: auto;
    display: block;
    margin: 0 auto;
  }
  .sucess-txt{
    text-align: center;
    line-height: 40px;
    font-size: 20px;
  }
  .price{
    padding: 64px 0 0;
    text-align: center;
    font-size: 18px;
    span{
      font-size: 28px;
    }
  }
  .txt{
    padding: 40px 0 0;
    text-align: center;
    font-size: 16px;
    color:rgb(193, 193, 193);
  }
}


</style>

<template lang="html">

<div class="pay-sucess">
  <img class="img" src="../../assets/images/sucess.png" alt="">
  <p class="sucess-txt">支付成功</p>
  <div class="price">
    ¥ <span>{{orderPrice}}</span>
  </div>
  <div class="txt">
    <p>收款方：{{receiver}}</p>
    <p>订单号：{{orderNo}}</p>
    <p>柿饼批次：{{batch}}</p>
    <p>付款时间:{{payTime}}</p>
    <br>
    <p>我的收货信息</p>
    <p>{{person}} {{phone}}</p>
    <p>{{address}}</p>
  </div>

</div>

</template>

<script>



import qs from 'qs'
import {
    setStore, getStore ,getUrlKey
}
from '../../config/mUtils'

export default {

    data() {
        return {
          orderPrice:'',
          orderNo:'',
          payTime:'',
          person:'',
          phone:'',
          address:'',
          receiver:'',
          batch:''
        }
    },
    created(){
        document.title="支付成功"
    },
    mounted() {
      this.orderNo = getStore('orderNo')
      this.payGoodsSuccess()
    },
    methods: {
      payGoodsSuccess(){
        let datas = {
            'openId':getStore('openId') ,
            'orderNo':this.orderNo
        }
          this.$http.post(this.HttpUrl.UrlConfig.payGoodsSuccess,datas)
              .then(res => {
                  var res = res.data
                  if (res.code == "1") {
                    this.orderPrice = res.orderPrice
                    this.payTime = res.payTime
                    this.person = res.person
                    this.phone = res.phone
                    this.address = res.address
                    this.receiver = res.receiver
                    this.batch = res.batch
                  } else {
                      this.$vux.toast.show({
                          text: res.msg,
                          type: 'text',
                          width: '80%'
                      })
                  }
              }).catch(error => {
                  console.log(error)
              })
      }
    }
}

</script>
