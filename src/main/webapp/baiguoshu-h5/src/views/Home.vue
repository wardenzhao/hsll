<template lang="html">
<div class="">
  <toast v-model="toastShow" type="text">{{toastTxt}}</toast>
</div>
</template>

<script>
import utils from '../assets/js/urlConfig.js'
import {
    Toast
}
from 'vux'
export default {
  components: {
        Toast
    },
  data(){
    return {
      toastTxt: '',
      toastShow: false,
    }
  },
  mounted(){
    this.isuser()
  },
  methods:{
    isuser(){
      this.$http.post('/launcher'+this.HttpUrl.UrlConfig.isuser)
                    .then(res => {
                      var res = res.data
                        if(res.code=='1'){// 会员
                          utils.LocalStorage.setStore('openId', res.openId)
                          this.$router.push('/buy-goods');
                        }else if(res.code=='0'){ // 非会员
                          this.$router.push('/no-members');
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

<style lang="css">
</style>
