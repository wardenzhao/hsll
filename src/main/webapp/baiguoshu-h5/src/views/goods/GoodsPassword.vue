<style lang="less">
.goods-pwd{
  overflow: hidden;
  clear: both;
  .goods-pwd-content{
    width: 308px;
    height:162px;
    border:1px solid #ccc;
    margin: 144px auto 40px;
    border-radius: 10px;
    .txt{
      line-height: 50px;
      font-size: 18px;
      text-align: center;
      color:#000;
      border-bottom: 1px solid #ccc;
    }
    .txt2{
      text-align: center;
      line-height: 50px;
      font-size: 16px;
    }
    .input-list{
      margin: 0 auto;
      width: 279px;
      clear: both;
      input{
        float: left;
        border: 1px solid #ccc;
        width: 30px;
        height: 30px;
        display: inline-block;
        margin-right: -1px;
        text-align: center;
        font-size: 16px;
      }
    }
  }
}

.wechat-btn{
  margin: 30px 30px 60px;
}

</style>

<template lang="html">

<div class="goods-pwd">
  <div class="goods-pwd-content">
    <div class="txt">请输入提货密码</div>
    <p class="txt2">好柿连连</p>
    <div class="input-list">
        <input type="text" name="" value="">
        <input type="text" name="" value="">
        <input type="text" name="" value="">
        <input type="text" name="" value="">
        <input type="text" name="" value="">
        <input type="text" name="" value="">
        <input type="text" name="" value="">
        <input type="text" name="" value="">
        <input type="text" name="" value="">
    </div>
  </div>
  <div class="wechat-btn">
    <x-button type="primary" link="">保存</x-button>
  </div>
</div>

</template>

<script>
import {
    XButton
}
from 'vux'
export default {
    components: {
        XButton
    },
    data() {
        return {
        }
    },
    created(){
      document.title="提货密码"
    },
    mounted(){

    },
    methods:{

    }
}

</script>
