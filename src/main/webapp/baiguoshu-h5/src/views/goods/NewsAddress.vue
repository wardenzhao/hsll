<style lang="less" scoped>
body{
  background: #e8e8e8;
}
.news-address{
  position: relative;
  left:0;
  top:0;
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
      <group>
        <x-input title="联系人" v-model="username" name="username" placeholder="请输入姓名" is-type="china-name"></x-input>
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
        }
    },
    methods:{
      selAddress(){
        let datas = {
          "openId":'123456',
          "id":'0',
          "person":this.username,
          "phone":this.phone,
          "address":this.address

        }
        this.$http.post(this.HttpUrl.UrlConfig.selAddress,datas)
                      .then(res => {
                        var res = res.data
                          if(res.code=='1'){// 会员
                            this.$vux.toast.show({
                                text: '保存成功',
                                type: 'text',
                                width: '80%'
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
