<style lang="less" scoped>

.address-list-box{
  padding-top: 20px;
  .address-list,.add-address{
      display: flex;
      display:-webkit-box;display:-moz-box;display:-ms-box;
      border-bottom:10px solid #f1f1f1;
      padding: 10px 0;
    .address-icon{
      width: 40px;
      text-align: center;
      img{
        position: relative;
        top: 15px;
        width: 20px;
        height:100%;
      }
    }
    .address-info{
    }
  }
  .add-address{
    .address-icon{
      width: 40px;
      text-align: center;
      img{
        position: relative;
        top: 4px;
        width: 20px;
        height:100%;
      }
}}
}
</style>

<template lang="html">

<div class="address-list-box">
      <div class="address-list" v-for="(item,index) in items" :key="index" @click="imgShowClick(item)">
        <div class="address-icon"><img v-show="item.imgShow" src="../../assets/images/address-icon.png" alt=""></div>
        <div class="address-info">
          <div>{{item.person}}({{item.phone}})</div>
          <div>{{item.address}}</div>
        </div>
      </div>
      <router-link to="/news-address">
        <div class="add-address">
          <div class="address-icon"><img src="../../assets/images/add.png" alt=""></div>
          <div class="address-info">
            新增地址
          </div>
        </div>
      </router-link>
    <toast v-model="toastShow" type="text">{{toastTxt}}</toast>
</div>

</template>

<script>

import utils from '../../assets/js/urlConfig.js'
import {
    Toast
}
from 'vux'
export default {
  components: {
        Toast
    },
    data() {
        return {
          toastTxt: '',
          toastShow: false,
          imgShow:false,
          items:[
            {                //类型：Object  必有字段  备注：无
                "id":"123",                //类型：String  必有字段  备注：地址id
                "person":"张三2",                //类型：String  必有字段  备注：收货人
                "phone":"18888888888",                //类型：String  必有字段  备注：收获电话号码
                "address":"陕西西安",                //类型：String  必有字段  备注：收货地址
                "imgShow":false
            },
            {                //类型：Object  必有字段  备注：无
                "id":"123",                //类型：String  必有字段  备注：地址id
                "person":"张三1",                //类型：String  必有字段  备注：收货人
                "phone":"18888888888",                //类型：String  必有字段  备注：收获电话号码
                "address":"陕西西安",                //类型：String  必有字段  备注：收货地址
                  "imgShow":false
            }
          ],
        }
    },
    created(){
      document.title= "我的地址"
    },
    methods:{
      imgShowClick(item){
        console.log(item)
        for(var i in this.items){
          this.items[i].imgShow = false
        }
        item.imgShow = true
        let datas = {
          "openId":utils.LocalStorage.getStore('openId'),
          "id":item.address,
          "person":item.person,
          "phone":item.phone,
          "address":item.address

        }
        this.$http.post(utils.UrlConfig.selAddress,datas)
                      .then(res => {
                        var res = res.data
                          if(res.code=='1'){// 会员
                            this.toastShow = true
                            this.toastTxt = "选择成功"
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
