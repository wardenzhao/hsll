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
        <input v-for="(n,index) in nums" maxlength="1" type="text" name="" v-model="n.num">
    </div>
  </div>
  <div class="wechat-btn">
      <x-button type="primary" @click.native="inviteCode">确认提交</x-button>
  </div>
  <!-- <div class="btn-box">
      <router-link to="/apply-members"><span>没有邀请码</span></router-link>
  </div> -->
  <toast v-model="toastShow" type="text">{{toastTxt}}</toast>
</div>

</template>

<script>

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
          nums: [{
              num: ''
          }, {
              num: ''
          }, {
              num: ''
          }, {
              num: ''
          }, {
              num: ''
          }, {
              num: ''
          }, {
              num: ''
          }, {
              num: ''
          }, {
              num: ''
          }],
        }
    },
    created(){
      document.title="加入会员"
    },
    mounted(){


    },
    methods:{
      inviteCode(){

        this.$router.push(
          {
            path: '/apply-members'
          })






        let takeCode = '',
            flag = true
        this.nums.forEach((val, index) => {
            if (!val.num) {
                this.$vux.toast.text('请正确输入邀请码')
                flag = false
                return false
            }
            takeCode += val.num
        })


        if (flag) {
          let datas = {
            "openId":'123456',
            "inviteCode":takeCode
          }
          console.log(datas)
          this.$http.post(this.HttpUrl.UrlConfig.inviteCode,datas)
                        .then(res => {
                          var res = res.data
                            if(res.code=='1'){// 会员
                              this.$router.push(
                                {
                                  path: '/apply-members'
                                })
                            }else{ // 异常
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
}

</script>
