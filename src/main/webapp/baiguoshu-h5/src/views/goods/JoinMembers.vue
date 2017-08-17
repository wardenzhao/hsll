<style lang="less">
.goods-pwd{
  overflow: hidden;
  clear: both;
  .tips{
    text-align: left;
    width: 80%;
    margin: 100px auto 0;
    font-size: 16px;
  }
  .goods-pwd-content{
    width: 308px;
    height:130px;
    border:1px solid #ccc;
    margin: 40px auto 0;
    border-radius: 10px;
    .txt{
      line-height: 50px;
      font-size: 18px;
      text-align: center;
      color:#000;
      border-bottom: 1px solid #ccc;
    }
    .input-list{
      margin: 20px auto 0;
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
  .btn{
    width: 168px;
    height:35px;
    line-height: 35px;
    display: block;
    margin: 20px auto 0;
    text-align: center;
    font-size: 18px;
    color:rgb(223, 75, 36);
    border:1px solid rgb(223, 75, 36);
    border-radius: 5px;
  }
  .btn-box{
    text-align: center;
    padding: 40px 0;
    span{
      font-size: 16px;
      color:rgb(0,147, 211);
    }
  }
}

.wechat-btn{
  margin: 30px 30px 60px;
}

</style>

<template lang="html">

<div class="goods-pwd">
  <p class="tips">好柿连连年产有限，现仅为老客户提共内部邀请码，方便大家在线上预订购买</p>
  <div class="goods-pwd-content">
    <div class="txt">请输入会员邀请密码</div>
    <div class="input-list">
        <input type="text" maxlength="1" name="" v-model="num1">
        <input type="text" maxlength="1" name="" v-model="num2">
        <input type="text" maxlength="1" name="" v-model="num3">
        <input type="text" maxlength="1" name="" v-model="num4">
        <input type="text" maxlength="1" name="" v-model="num5">
        <input type="text" maxlength="1" name="" v-model="num6">
        <input type="text" maxlength="1" name="" v-model="num7">
        <input type="text" maxlength="1" name="" v-model="num8">
        <input type="text" maxlength="1" name="" v-model="num9">
    </div>
  </div>
    <span @click.native="" class="btn">确认提交</span>
  <div class="btn-box">
      <span>没有邀请码</span>
  </div>
  <toast v-model="toastShow" type="text">{{toastTxt}}</toast>
</div>

</template>

<script>
import utils from '../../assets/js/urlConfig.js'

import {
    Group,
    XInput,
    XButton,
    Toast
}
from 'vux'
export default {
    components: {
        Group,
        XInput,
        XButton,
        Toast
    },
    data() {
        return {
          toastTxt: '',
          toastShow: false,
          inviteCode:null,
          num1:null,
          num2:null,
          num3:null,
          num4:null,
          num5:null,
          num6:null,
          num7:null,
          num8:null,
          num9:null
        }
    },
    created(){
      document.title="加入会员"
    },
    mounted(){
    },
    methods:{
      inviteCode(){
        this.inviteCode = this.num1 + this.num2 + this.num3 + this.num4 + this.num5 + this.num6 + this.num7 + this.num8 + this.num9
        let datas = {
          "openId":utils.LocalStorage.getStore('openId'),
          "inviteCode":this.inviteCode
        }
        this.$http.post(utils.UrlConfig.inviteCode,datas)
                      .then(res => {
                        var res = res.data
                          if(res.code=='1'){// 会员
                            this.toastShow = true
                            this.toastTxt = res.msg
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
