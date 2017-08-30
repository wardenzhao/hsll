<template lang="html">
<div class="">
  <div class="img-box">
    <img src="../../assets/images/1.png" class="img1" alt="">
    <img src="../../assets/images/2.png" class="img2" alt="">
    <img src="../../assets/images/3.png" class="img3" alt="">
    <p class="txt">抱歉，您不是好柿练练的会员，暂时无法购买我们的流心吊柿子哦。</p>
    <div class="btn-box">
      <router-link to="/goods-password"><x-button class="btn" plain>兑换您的柿饼</x-button></router-link>
    </div>
    <router-link to="/join-members"><p class="into-members">成为会员</p></router-link>
  </div>
</div>
</template>

<script>
import {
    setStore, getStore ,getUrlKey
}
from '../../config/mUtils'
import { XButton } from 'vux'
export default {
  components:{
    XButton
  },
  created(){
    document.title="不是会员"
    if(getUrlKey('openId')){
      setStore('openId',getUrlKey('openId'))
    }
  },
}
</script>

<style lang="less">
  .img-box{
    overflow: hidden;
    position: relative;
    margin-top: 60px;
    img{
      display: block;
      margin: 0 auto;
    }
    .img1{
      width: 200px;
      height:200px;
      position: relative;
      top:0;
      left:0;
    }
    .img2{
      width: 160px;
      height:160px;
      position: relative;
      top:-40px;
      left:0;
    }
    .img3{
      width: 80%;
      height: 1px;
      position: relative;
      top:0;
      left:0;
      margin-bottom: 1.5rem;
    }
  }
  .txt{
    width: 65%;
    display: block;
    margin: 0 auto;
    color: #999;
  }
  .btn-box{
    width: 60%;
    margin: 1.5rem auto;
    .weui-btn_plain-default{
      color:rgba(223, 73, 36, 1);
      border: 1px solid rgba(223, 73, 36, 1);
    }
  }
  .into-members{
    margin-top: 60px;
    text-align: center;
    color:#0098cc;
  }

</style>
