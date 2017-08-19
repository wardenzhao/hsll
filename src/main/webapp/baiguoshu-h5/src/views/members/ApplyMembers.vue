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
        <x-button type="primary" @click.native="register">提交</x-button>
      </div>
    </div>
</div>

</template>

<script>
import {
  XInput, Group,
    XHeader,
    XButton,
    PopupPicker,
    Toast
}
from 'vux'
import {
    setStore, getStore ,getUrlKey
}
from '../../config/mUtils'
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
        }
    },
    mounted(){

    },
    methods:{
      submitBtn(){

      },
      register(){
        if(!this.userName){
          this.$vux.toast.show({
              text: '请填写姓名',
              type: 'text',
          })
          return false
        }
        if(!this.phone){
          this.$vux.toast.show({
              text: '请填写手机号',
              type: 'text',
          })
          return false
        }
        if(this.sexVal.length==0){
          this.$vux.toast.show({
              text: '请选择性别',
              type: 'text',
          })
          return false
        }
        if(!this.address){
          this.$vux.toast.show({
              text: '请填写地址',
              type: 'text',
          })
          return false
        }
        if(!this.inviteName){
          this.$vux.toast.show({
              text: '请填写推荐会员姓名',
              type: 'text',
          })
          return false
        }
        var datas = {
          "openId":getStore('openId'),
          "userName":this.userName,
          "phone":this.phone,
          "sex":this.sexVal,
          "address":this.address,
          "inviteName":this.inviteName
        }
        this.$http.post(this.HttpUrl.UrlConfig.register,datas)
                      .then(res => {
                        var res = res.data
                          if(res.code=='1'){
                            this.$router.push(
                              {
                                path: '/members'
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

</script>
