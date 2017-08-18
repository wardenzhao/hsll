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
      <div class="address-list" v-for="(item,index) in items" :key="index" @click="imgShowClick(index)">
        <div class="address-icon"><img v-show="index==i" src="../../assets/images/address-icon.png" alt=""></div>
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
</div>

</template>

<script>
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
          items:[],
          i: -1
        }
    },
    created(){
      document.title= "我的地址"

    },
    mounted(){
        this.addressList()
    },
    methods:{
      addressList(){
        let datas = {
          "openId":'123456'
        }
        this.$http.post(this.HttpUrl.UrlConfig.address,datas)
                      .then(res => {
                        var res = res.data
                          if(res.code=='1'){// 会员
                            this.items = res.data
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
      },
      imgShowClick(index){
        console.log(index)
        this.i = index


        // let datas = {
        //   "openId":'123456',
        //   "id":item.address,
        //   "person":item.person,
        //   "phone":item.phone,
        //   "address":item.address
        // }
        // this.$http.post(this.HttpUrl.UrlConfig.selAddress,datas)
        //               .then(res => {
        //                 var res = res.data
        //                   if(res.code=='1'){// 会员
        //                     this.toastShow = true
        //                     this.toastTxt = "选择成功"
        //                   }else{ // 异常
        //                     this.$vux.toast.show({
        //                         text: res.msg,
        //                         type: 'text'
        //                     })
        //                   }
        //               }).catch(error => {
        //                   console.log(error)
        //               })
      }
    }

}

</script>
