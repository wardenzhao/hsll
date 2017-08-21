<style lang="less" scoped="">

.comfirm-goods {
    position: relative;
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    height: 100%;
    .comfirm-goods-box {
        .img-box {
          background: #fff;
          padding: 0 0 10px;
          margin-bottom: 10px;
            .img {
                width: 100%;
                height: 200px;
            }
            .info {
                padding: 10px 11px;
                .t {
                    font-size: 16px;
                }
                .txt {
                    padding: 6px 0 0;
                    font-size: 14px;
                    color: rgb(223, 75, 36);
                }
            }
        }
        .user-info{
          margin-top: 10px;
          border-top: 10px solid rgb(240, 239, 245);
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
}
.wechat-btn {
    margin: 30px 30px 60px;
}

</style>

<template lang="html">
<div class="comfirm-goods">
    <div class="comfirm-goods-box">
        <div class="img-box">
            <img class="img" :src="goodImg" alt="">
            <div class="info">
                <p class="t">{{goodName}}</p>
                <p class="txt">{{goodBatch}}</p>
                <p class="t">预订会员 {{reserveMember}}</p>
            </div>
        </div>
        <div class="user-info">
            <group>
              <cell :title="date"></cell>
              <x-input title="联系人" name="person" v-model="userName" placeholder="请输入姓名" is-type="china-name"></x-input>
              <x-input title="手机号码" name="mobile" v-model="phone" placeholder="请输入手机号" keyboard="number" is-type="china-mobile"></x-input>
              <x-input title="收货地址" name="address" v-model="address" placeholder="街道门牌信息"></x-input>
            </group>
        </div>
        <div class="wechat-btn">
            <x-button type="primary" @click.native="confirmTakeGood">确认提交</x-button>
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
  XInput, Group,Cell,Toast,XButton
}
from 'vux'
export default {
  components: {
    XInput,
      Group,
      Cell,
      Toast,
      XButton
  },
  data(){
    return{
      toastMsg:'',
      toastShow:false,
      takeCode:'',
      goodName:'',
      goodImg:'',
      goodBatch:'',
      reserveMember:'',
      userName:'',
      date:'',
      phone:'',
      address:'',
    }
  },
  created(){
    document.title="确认提货"
  },
  mounted(){
    this.takeGoodInfo()
  },
  methods:{
    takeGoodInfo(){
      let datas = {
          'openId': getStore('openId'),
          'takeCode': getUrlKey('takeCode')
      }
      this.$http.post(this.HttpUrl.UrlConfig.takeGood, datas)
          .then(res => {
              var res = res.data
              if (res.code == "1") {
                this.takeCode = res.data.takeCode
                this.goodName = res.data.goodName
                this.goodImg = res.data.goodImg
                this.reserveMember = res.data.reserveMember
                this.goodBatch = res.data.goodBatch
                this.date = '预计发货时间：'+res.data.sendTime
              } else {
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
    confirmTakeGood(){
      // let result = '王杨先生已邀请您成为好柿连连会员，快去柿园看看'
      // this.$router.push(
      //   {
      //     path: '/goods-sucess',
      //     query: { result:  result}
      //   })

      var datas = {
          "openId":getStore('openId'),
          "takeCode":this.takeCode,
          "address":this.address,
          "person":this.userName,
          "phone":this.phone,
          "goodId":'',
          "reserveMember":this.reserveMember
      }
      this.$http.post(this.HttpUrl.UrlConfig.confirmTakeGood,datas)
                    .then(res => {
                      var res = res.data
                        if(res.code=='1'){// 成功
                          let result = res.result
                          let that = this;
                          // let result = '王杨先生已邀请您成为好柿连连会员，快去柿园看看'
                          this.$vux.toast.show({
                              text: '提货成功',
                              type: 'text',
                              width: '80%',
                              time:'2000',
                              onHide(){
                                that.$router.push(
                                  {
                                    path: '/goods-sucess',
                                    query: { result:  result}
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
