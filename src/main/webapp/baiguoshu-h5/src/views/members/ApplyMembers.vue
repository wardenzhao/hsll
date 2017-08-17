<style lang="less" scoped>
.apply-members{
  position: relative;
  left:0;
  top:0;
  bottom: 0;
  right: 0;
  width: 100%;
  height: 100%;
  .txt{
    padding:18px 11px 0;
  }
}
.wechat-btn{
  margin: 30px 30px 60px;
}

</style>

<template lang="html">

<div class="apply-members">
    <div class="">
      <p class="txt">请填写您的申请信息，我们将进行人工审核。</p>
      <group>
        <x-input title="姓名" v-model="userName" name="username" placeholder="请填写真实姓名" is-type="china-name"></x-input>
        <x-input title="电话" name="mobile" v-model="phone" placeholder="11位手机号" keyboard="number" is-type="china-mobile"></x-input>
        <popup-picker title="性别" :data="[['男','女']]" v-model="sexVal"  placeholder="请选择"></popup-picker>
        <x-input title="我的地址" name="area" v-model="address" placeholder="填写可用于收货的地址"></x-input>
        <x-input title="推荐会员姓名" name="area" v-model="inviteName" placeholder="请填写推荐会员的真实姓名"></x-input>
      </group>
      <div class="wechat-btn">
        <x-button type="primary" @click.native="submitBtn">保存</x-button>
      </div>
    </div>
    <toast v-model="toastShow" type="text">{{toastTxt}}</toast>
</div>

</template>

<script>
import utils from '../../assets/js/urlConfig.js'
import {
  XInput, Group,
    XHeader,
    XButton,
    PopupPicker,
    Toast
}
from 'vux'
export default {
    components: {
        XHeader,
        XInput, Group,
        XButton,
        PopupPicker,
        Toast
    },
    data() {
        return {
            userName:'',
            phone:'',
            sex:'',
            address:'',
            inviteName:'',
            sexVal:[],
            toastTxt: '',
            toastShow: false,
        }
    },
    mounted(){

    },
    methods:{
      submitBtn(){

      },
      register(){
        var datas = {
          "openId":utils.LocalStorage.getStore('openId'),
          "userName":this.userName,
          "phone":this.phone,
          "sex":this.sex,
          "address":this.address,
          "inviteName":this.inviteName
        }
        this.$http.post(utils.UrlConfig.register,datas)
                      .then(res => {
                        var res = res.data
                          if(res.code=='1'){
                            this.toastShow = true
                            this.toastTxt = "申请成功"
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
