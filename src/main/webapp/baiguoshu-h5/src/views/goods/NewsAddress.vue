<style lang="less" scoped>
body{
  background: #e8e8e8;
}
.news-address{
  position: relative;
  left:0;
  top:70px;
  bottom: 0;
  right: 0;
  width: 100%;
  height: 100%;
}
.wechat-btn{
  margin: 30px 30px 60px;
}

</style>

<template lang="html">

<div class="news-address">
    <div class="">
      <group title="地址信息">
        <x-input title="联系人" v-model="username" name="username" placeholder="请输入姓名"

        ></x-input>
        <x-input title="手机号码"  v-model="phone" name="phone" placeholder="请输入手机号" keyboard="number" is-type="china-mobile"></x-input>
        <!-- <x-input title="选择地区" name="area" placeholder="地区信息"></x-input> -->
        <x-input title="收货地址" v-model="address" name="area" placeholder="街道门牌信息"></x-input>
      </group>
      <div class="wechat-btn">
        <x-button type="primary" @click.native="selAddress">保存</x-button>
      </div>
    </div>
</div>

</template>

<script>
import {
    setStore, getStore ,getUrlKey
}
from '../../config/mUtils'
import {
  XInput, Group,
    XHeader,
    XButton
}
from 'vux'
export default {
    components: {
        XHeader,
        XInput, Group,
        XButton
    },
    data() {
        return {
          username:'',
          phone:'',
          address:'',
          id:'0',
          msg:'保存成功'
        }
    },
    mounted(){
      if(getStore('updateAddress')){
        let updateAddress = JSON.parse(getStore('updateAddress'))
        this.username = updateAddress.person
        this.phone = updateAddress.phone
        this.address = updateAddress.address
        this.id = updateAddress.id
        this.msg = '修改成功'
      }
    },
    methods:{

      selAddress(){
        if(this.username==''){
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
        if (/^1[3|4|5|6|7|8|9][0-9]{1}[0-9]{8}$/.test(this.phone) == false) {
          this.$vux.toast.show({
              text: '手机号格式不正确',
              type: 'text',
              width: '80%'
          })
          return false
        }

        if(!this.address){
          this.$vux.toast.show({
              text: '请填写收货地址',
              type: 'text',
          })
          return false
        }


        let datas = {
          "openId":getStore('openId'),
          "id":this.id,
          "person":this.username,
          "phone":this.phone,
          "address":this.address

        }
        this.$http.post(this.HttpUrl.UrlConfig.selAddress,datas)
                      .then(res => {
                        let that = this;
                        var res = res.data
                          if(res.code=='1'){// 会员
                            this.$vux.toast.show({
                                position:'top',
                                text: this.msg,
                                type: 'text',
                                width: '80%',
                                time:'2000',
                                onHide(){
                                  that.$router.push({
                                    path:'/address-list',
                                    query:{
                                      orderNo:getUrlKey('orderNo')
                                    }
                                  })
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

</script>
