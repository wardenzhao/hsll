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
        <x-input title="联系人" v-model="person" name="username" placeholder="请输入姓名" is-type="china-name"></x-input>
        <x-input title="手机号码"  v-model="person" name="phone" placeholder="请输入手机号" keyboard="number" is-type="china-mobile"></x-input>
        <x-input title="选择地区" name="area" placeholder="地区信息"></x-input>
        <x-input title="详细地址" v-model="address" name="area" placeholder="街道门牌信息"></x-input>
      </group>
      <div class="wechat-btn">
        <x-button type="primary" link="">保存</x-button>
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
          person:'',
          phone:'',
          address:'',
        }
    },
    methods:{
      selAddress(){
        let datas = {
          "openId":utils.LocalStorage.getStore('openId'),
          "id":'0',
          "person":this.person,
          "phone":this.phone,
          "address":this.address

        }
        this.$http.post(utils.UrlConfig.selAddress,datas)
                      .then(res => {
                        var res = res.data
                          if(res.code=='1'){// 会员
                            this.toastShow = true
                            this.toastTxt = "保存成功"
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
