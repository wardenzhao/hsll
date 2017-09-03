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
    ¥ <span>156.00</span>
  </div>
  <div class="txt">
    <p>收款方：陕西白果树</p>
    <p>订单号：88888888</p>
    <p>柿饼批次：2017年第一批第225盒 <br>2017年第一批第225盒</p>
    <p>付款时间:2017年2月14日12：35</p>
    <br>
    <p>我的收货信息</p>
    <p>张三 197265533</p>
    <p>陕西省西安市灞桥区</p>
  </div>

</div>

</template>

<script>




import {
    setStore, getStore ,getUrlKey
}
from '../../config/mUtils'

export default {

    data() {
        return {

        }
    },
    created(){
        document.title="支付成功"
        console.log(getUrlKey('orderNo'))
    },
    mounted() {

    },
    methods: {

    }
}

</script>
