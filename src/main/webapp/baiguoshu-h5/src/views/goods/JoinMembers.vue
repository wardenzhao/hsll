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
    height:230px;
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
      width: 252px;
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
    padding: 0 ;
    span{
      font-size: 16px;
      color:rgb(0,147, 211);
    }
  }
}

.wechat-btn{
  margin: 30px 30px 60px;
}
.userInfo{
  width: 80%;
  margin: 0 auto;
  clear: both;
  overflow: hidden;
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
    <div class="userInfo">
      <group>
        <x-input title="姓名" v-model="userName"></x-input>
        <x-input title="手机" v-model="userPhone"></x-input>
      </group>
    </div>
  </div>
  <div class="wechat-btn">
      <x-button type="primary" @click.native="inviteCode">确认提交</x-button>
  </div>
  <div class="btn-box">
      <router-link to="/apply-members"><span>没有邀请码</span></router-link>
  </div>
  <toast v-model="toastShow" type="text">{{toastTxt}}</toast>
</div>

</template>

<script>
import {
    setStore, getStore
}
from '../../config/mUtils'
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
          userName:'',
          userPhone:'',
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

          if(this.userName==''){
            this.$vux.toast.show({
                text: '请填写姓名',
                type: 'text',
            })
            return false
          }
          if(!this.userPhone){
            this.$vux.toast.show({
                text: '请填写手机号',
                type: 'text',
            })
            return false
          }
          if (/^1[3|4|5|6|7|8|9][0-9]{1}[0-9]{8}$/.test(this.userPhone) == false) {
            this.$vux.toast.show({
                text: '手机号格式不正确',
                type: 'text',
                width: '80%'
            })
            return false
          }



          let datas = {
            "openId":getStore('openId'),
            "inviteCode":takeCode,
            "name":this.userName,
            "phone":this.userPhone
          }
          console.log(datas)
          this.$http.post(this.HttpUrl.UrlConfig.inviteCode,datas)
                        .then(res => {
                          var res = res.data
                            if(res.code=='1'){// 会员
                              this.$router.push(
                                {
                                  path: '/join-members-sucess',
                                  query:{
                                    result:res.result
                                  }
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
