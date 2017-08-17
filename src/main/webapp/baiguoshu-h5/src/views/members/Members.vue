<style lang="less">
.members{
  position: relative;
  left:0;
  top:0;
  bottom: 0;
  right: 0;
  padding: 10 11px;
  .img-box{
    overflow: hidden;
    clear: both;
    padding: 22px 0 10px;
    .img{
      width: 80px;
      height:80px;
      display: block;
      margin: 0 auto;
    }
    .name{
      text-align: center;
      line-height: 50px;
    }
  }
  .txt{
    padding:18px 11px 0;
  }
  .address{
    .weui-cell__ft{
      width: 80%
    }
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

</style>

<template lang="html">

<div class="members">
    <div class="img-box">
      <img class="img" src="../../assets/images/members.png" alt="">
      <p class="name">汪汪汪</p>
    </div>
    <div class="">
      <group>
        <cell title="姓名" :value="userName"></cell>
        <cell title="电话" :value="phone"></cell>
        <cell title="性别" :value="userSex"></cell>
        <cell title="收货地址" class="address">
          <div slot="value">
            <span style="padding-left:20px;">{{address}}</span>
          </div>
        </cell>
      </group>
    </div>
    <div class="btn-box">
        <span>我的订单</span>
    </div>
</div>

</template>

<script>
import utils from '../../assets/js/urlConfig.js'
import {
  Group,
  Cell,
  Toast
}
from 'vux'
export default {
  components: {
      Group, Cell,Toast
  },
    data() {
        return {
            userName:'',
            phone:'',
            userSex:'',
            addres:''
        }
    },
    created(){
        document.title="会员"
    },
    mounted(){
    },
    methods:{
        getUserMessage(){
          var datas = {
              "openId":utils.LocalStorage.getStore('openId')
          }
          this.$http.post(utils.UrlConfig.getUserMessage,datas)
                        .then(res => {
                          var res = res.data
                            if(res.code=='1'){// 成功
                                this.userName = res.data.userName
                                this.phone = res.data.phone
                                this.userSex = res.data.userSex
                                this.address = res.data.address
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
